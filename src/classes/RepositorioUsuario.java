package classes;

import exceptions.*;
import interfaces.IRepositorioUsuario;
import java.util.ArrayList;

public class RepositorioUsuario implements IRepositorioUsuario{
    private ArrayList<Perfil> usuarios;

    public RepositorioUsuario(){
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void cadastrar(Perfil perfil) throws UJCException {
        String username = perfil.getUsuario();

        if(this.buscar(username) == null){
            this.usuarios.add(perfil);
        }
        else{
            throw new UJCException(username);
        }

    }

    @Override
    public Perfil buscar(String usuario){
        for(Perfil p : this.usuarios){
            if(p.getUsuario().equals(usuario)){
                return p;
            }
        }

        return null;
    }

    @Override
    public void atualizar(Perfil perfil) throws UNCException{
        String username = perfil.getUsuario();
        Perfil p = this.buscar(username);

        if(p == null){
            throw new UNCException(username);
        }

        int index = this.usuarios.indexOf(p);
        this.usuarios.set(index, perfil);
    }

    public void listPerfis(){
        int i = 1;
        for(Perfil p : this.usuarios){
            System.out.println(i + ". " + p.getUsuario() + " / " + ((PessoaFisica)p).getCpf());
            i++;
        }
    }
}
