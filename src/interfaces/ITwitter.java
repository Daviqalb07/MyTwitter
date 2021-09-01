package interfaces;

import classes.*;
import exceptions.*;
import java.util.Vector;

public interface ITwitter{
    void criarPerfil(Perfil perfil) throws PEException;
    void cancelarPerfil(String usuario) throws PIException, PDException;
    void tweetar(String usuario, String texto) throws PIException, PDException, MFPException;
    Vector<Tweet> timeline(String usuario) throws PIException, PDException;
    Vector<Tweet> tweets(String usuario) throws PIException, PDException;
    void seguir(String seguidor, String seguindo) throws PIException, PDException, SIException, JSException;
    int numeroSeguidores(String usuario) throws PIException, PDException;
    Vector<Perfil> seguidores(String usuario) throws PIException, PDException;
    Vector<Perfil> seguidos(String usuario) throws PIException, PDException;
}
