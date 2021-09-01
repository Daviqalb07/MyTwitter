package exceptions;


/* Exceção avisa que o usuário seguidor já segue o seguido.
*/
public class JSException extends Exception{
    public JSException(String seguidor, String seguido){
        super(seguidor + " já segue " + seguido + "!");
    }
}
