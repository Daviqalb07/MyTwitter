package interfaces;

import classes.Perfil;
import exceptions.*;


public interface IRepositorioUsuario {
    void cadastrar(Perfil perfil) throws UJCException;
    Perfil buscar(String usuario);
    void atualizar(Perfil perfil) throws UNCException;
}
