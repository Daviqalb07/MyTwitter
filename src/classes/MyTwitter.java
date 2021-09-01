package classes;

import interfaces.*;
import exceptions.*;

import java.util.Vector;



public class MyTwitter implements ITwitter{
    private IRepositorioUsuario repositorio;

    public MyTwitter(IRepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public void criarPerfil(Perfil perfil) throws PEException {
        try{
            this.repositorio.cadastrar(perfil);
        }
        catch(UJCException ujc){
            throw new PEException(perfil.getUsuario());
        }
    }

    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException {
        Perfil busca = this.repositorio.buscar(usuario);

        if(busca == null){
            throw new PIException(usuario);
        }

        if(busca.isAtivo()){
            busca.setAtivo(false);
        }
        else
            throw new PDException(usuario);

        try {
            this.repositorio.atualizar(busca);
        }catch(UNCException unc){
            System.out.println(unc.getMessage());
        }
    }

    @Override
    public void tweetar(String usuario, String texto) throws PIException, PDException, MFPException {
        int size = texto.length();
        if(size < 1 || size > 140){
            throw new MFPException();
        }

        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null){
            throw new PIException(usuario);
        }

        if(!perfil.isAtivo()){
            throw new PDException(usuario);
        }

        Tweet tweet = new Tweet(usuario, texto);
        perfil.addTweet(tweet);
        try{
            this.repositorio.atualizar(perfil);
        }
        catch(UNCException unc){
            System.out.println(unc.getMessage());
        }
    }

    @Override
    public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null)
            throw new PIException(usuario);

        if(!perfil.isAtivo())
            throw new PDException(usuario);

        Vector<Tweet> timeline = new Vector<>(perfil.getTimeline());
        Vector<String> seguidosUsuarios = new Vector<>(perfil.getSeguidos());
        Vector<Perfil> seguidos = new Vector<>();

        for(String seguidoUsuario : seguidosUsuarios){
            seguidos.add(this.repositorio.buscar(seguidoUsuario));
        }

        seguidos.removeIf(seguido-> !seguido.isAtivo());

        for(Perfil p : seguidos){
            timeline.addAll(p.getTimeline());
        }

        return timeline;
    }

    @Override
    public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null)
            throw new PIException(usuario);

        if(!perfil.isAtivo())
            throw new PDException(usuario);

        return perfil.getTimeline();
    }

    @Override
    public void seguir(String seguidor, String seguindo) throws PIException, PDException, SIException, JSException {
        Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
        Perfil perfilSeguido = this.repositorio.buscar(seguindo);

        if(perfilSeguidor == null)
            throw new PIException(seguidor);
        if(perfilSeguido == null)
            throw new PIException(seguindo);

        if(!perfilSeguidor.isAtivo())
            throw new PDException(seguidor);
        if(!perfilSeguido.isAtivo())
            throw new PDException(seguindo);

        if(perfilSeguidor.equals(perfilSeguido))
            throw new SIException();

        if(perfilSeguido.getSeguidores().contains(perfilSeguidor.getUsuario()))
            throw new JSException(seguidor, seguindo);

        perfilSeguidor.addSeguido(seguindo);
        perfilSeguido.addSeguidor(seguidor);

        try{
            this.repositorio.atualizar(perfilSeguidor);
            this.repositorio.atualizar(perfilSeguido);
        }
        catch(UNCException unc){
            System.out.println(unc.getMessage());
        }
    }

    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException {
        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null)
            throw new PIException(usuario);

        if(!perfil.isAtivo())
            throw new PDException(usuario);

        Vector<String> seguidoresUsuario = new Vector<>(perfil.getSeguidores());
        Vector<Perfil> seguidores = new Vector<>();

        for(String seguidorUsuario : seguidoresUsuario){
            seguidores.add(this.repositorio.buscar(seguidorUsuario));
        }

        seguidores.removeIf(seguidor-> !seguidor.isAtivo());

        return seguidores.size();
    }

    @Override
    public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null)
            throw new PIException(usuario);

        if(!perfil.isAtivo())
            throw new PDException(usuario);

        Vector<String> seguidoresUsuario = new Vector<>(perfil.getSeguidores());
        Vector<Perfil> seguidores = new Vector<>();

        for(String seguidorUsuario : seguidoresUsuario){
            seguidores.add(this.repositorio.buscar(seguidorUsuario));
        }

        seguidores.removeIf(seguidor-> !seguidor.isAtivo());

        return seguidores;
    }

    @Override
    public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
        Perfil perfil = this.repositorio.buscar(usuario);

        if(perfil == null)
            throw new PIException(usuario);

        if(!perfil.isAtivo())
            throw new PDException(usuario);

        Vector<String> seguidosUsuario = new Vector<>(perfil.getSeguidos());
        Vector<Perfil> seguidos = new Vector<>();

        for(String seguidoUsuario : seguidosUsuario){
            seguidos.add(this.repositorio.buscar(seguidoUsuario));
        }

        seguidos.removeIf(seguido-> !seguido.isAtivo());

        return seguidos;
    }
}
