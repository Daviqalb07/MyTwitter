package exceptions;

public class SIException extends Exception{
    public SIException(){
        super("Seguidor inválido! Não é possível seguir a si mesmo!");
    }
}
