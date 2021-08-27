package exceptions;

public class MFPException extends Exception{
    public MFPException(){
        super("A mensagem deve ter entre 1 e 140 caracteres!");
    }
}
