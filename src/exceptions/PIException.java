package exceptions;

public class PIException extends Exception{

    public PIException(String usuario){
        super("Perfil com nome de usuário " + usuario + " não existe!");
    }
}
