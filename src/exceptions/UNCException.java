package exceptions;

public class UNCException extends Exception{

    public UNCException(String usuario){
        super("Usuário não cadastrado!");
    }

}
