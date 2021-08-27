package exceptions;

public class PDException extends Exception{
    private String usuario;

    public PDException(String usuario){
        super("Perfil com nome de usuário " + usuario + " já está desativado!");
        this.usuario = usuario;
    }
}
