package classes;

public class Tweet {
    private String usuario;
    private String mensagem;

    public Tweet(String usuario, String mensagem){
        this.usuario = usuario;
        this.mensagem = mensagem;
    }
    public void setUsuario(String user){
        this.usuario = user;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return this.mensagem;
    }
}
