package exceptions;

public class PIException extends Exception{
    private String usuario;

    public PIException(String usuario){
        super("Perfil com nome de usuário " + usuario + " não existe!");
        this.usuario = usuario;
    }
}
