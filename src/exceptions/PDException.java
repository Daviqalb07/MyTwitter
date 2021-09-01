package exceptions;

public class PDException extends Exception{

    public PDException(String usuario){
        super("Perfil com nome de usuário " + usuario + " já está desativado!");
    }
}
