import classes.*;
import exceptions.*;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Perfil p1 = new PessoaFisica("@daviqalb07", 1);
        Perfil p2 = new PessoaFisica("@yuno", 2);
        Perfil p3 = new PessoaFisica("@cath", 3);
        RepositorioUsuario repo = new RepositorioUsuario();
        try{
            repo.cadastrar(p1);
            //System.out.println("Usuário cadastrado!");
        }
        catch(UJCException ujc){
            System.out.println(ujc.getMessage());
        }
        try{
            repo.cadastrar(p2);
        }
        catch(UJCException ujc){
            System.out.println(ujc.getMessage());
        }
        try{
            repo.cadastrar(p3);
        }
        catch(UJCException ujc){
            System.out.println(ujc.getMessage());
        }
        repo.listPerfis();


        p1 = new PessoaFisica("@caio", 5555);

        System.out.println();
        try{
            repo.atualizar(p1);
        }
        catch(UNCException unc){
            System.out.println(unc.getMessage() + " " + unc.getUsuario());
        }

        System.out.println();
        repo.listPerfis();
    }
}
