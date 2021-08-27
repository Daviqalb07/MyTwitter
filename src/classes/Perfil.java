package classes;

import java.util.Vector;

public abstract class Perfil {
    private String usuario;
    private Vector<Perfil> seguidos;
    private Vector<Perfil> seguidores;
    private Vector<Tweet> timeline;
    private boolean ativo;

    public Perfil(String user){
        this.usuario = user;
        this.seguidores = new Vector<>();
        this.seguidos = new Vector<>();
        this.timeline = new Vector<>();
        this.ativo = true;
    }

    public void addSeguido(Perfil seguido){
        this.seguidos.add(seguido);
    }

    public void addSeguidor(Perfil seguidor){
        this.seguidores.add(seguidor);
    }

    public void addTweet(Tweet tweet){
        this.timeline.add(tweet);
    }

    public void setUsuario(String user){
        this.usuario = user;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public Vector<Perfil> getSeguidos(){
        return this.seguidos;
    }

    public Vector<Perfil> getSeguidores(){
        return this.seguidores;
    }

    public Vector<Tweet> getTimeline(){
        return this.timeline;
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }

    public boolean isAtivo(){
        return this.ativo;
    }
}
