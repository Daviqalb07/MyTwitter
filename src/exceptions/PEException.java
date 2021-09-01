package exceptions;

public class PEException extends Exception{
    public PEException(String usuario){
        super("Perfil com nome de usuário " + usuario + " já existe!");
    }
}
