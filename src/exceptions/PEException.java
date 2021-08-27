package exceptions;

public class PEException extends Exception{
    private String usuario;

    public PEException(String usuario){
        super("Perfil com nome de usuário " + usuario + " já existe!");
        this.usuario = usuario;
    }
}
