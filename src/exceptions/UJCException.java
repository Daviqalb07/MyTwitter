package exceptions;

public class UJCException extends Exception{

    public UJCException(String usuario){
        super("Usuário já cadastrado!");
    }

}
