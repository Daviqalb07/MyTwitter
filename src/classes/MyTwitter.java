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
            throw new PEException(ujc.getUsuario());
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

        Vector<Tweet> timeline = perfil.getTimeline();
        Vector<Perfil> seguidos = perfil.getSeguidos();

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
    public void seguir(String seguidor, String seguindo) throws PIException, PDException, SIException {
        Perfil follower = this.repositorio.buscar(seguidor);
        Perfil following = this.repositorio.buscar(seguindo);

        if(follower == null)
            throw new PIException(seguidor);
        if(following == null)
            throw new PIException(seguindo);

        if(!follower.isAtivo())
            throw new PDException(seguidor);
        if(!following.isAtivo())
            throw new PDException(seguindo);

        if(follower.equals(following))
            throw new SIException();

        follower.addSeguido(following);
        following.addSeguidor(follower);

        try{
            this.repositorio.atualizar(follower);
            this.repositorio.atualizar(following);
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

        Vector<Perfil> seguidores = perfil.getSeguidores();

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

        Vector<Perfil> seguidores = perfil.getSeguidores();

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

        Vector<Perfil> seguidos = perfil.getSeguidos();

        seguidos.removeIf(seguido-> !seguido.isAtivo());

        return seguidos;
    }
}
