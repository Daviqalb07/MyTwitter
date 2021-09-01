package Controllers;


import classes.*;
import exceptions.*;
import interfaces.ITwitter;

import java.util.Scanner;
import java.util.Vector;

public class MainController {
    private ITwitter myTwitter;
    private Scanner scanner;

    public MainController(ITwitter myTwitter, Scanner scanner) {
        this.myTwitter = myTwitter;
        this.scanner = scanner;
    }

    public int menu(){
        System.out.println("1 - Criar perfil (Pessoa Física)");
        System.out.println("2 - Criar perfil (Pessoa Jurídica)");
        System.out.println("3 - Tweetar");
        System.out.println("4 - Seguir");
        System.out.println("5 - Timeline de um perfil");
        System.out.println("6 - Tweets de um perfil");
        System.out.println("7 - Seguidores");
        System.out.println("8 - Perfis seguidos");
        System.out.println("9 - Desativar perfil");
        System.out.println("0 - Encerrar programa");
        System.out.println();
        System.out.print("Digite a opção desejada: ");

        int option;
        // Trata a exceção de não digitar um inteiro na opção.
        try{
            option = Integer.parseInt(this.scanner.next());
        }
        catch(NumberFormatException exception){
            this.scanner.nextLine();
            option = -1;
        }

        return option;
    }

    public void cadastrarPF(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            System.out.print("CPF: ");
            long cpf = Long.parseLong(this.scanner.next());
            System.out.println();

            Perfil perfil = new PessoaFisica(usuario, cpf);

            myTwitter.criarPerfil(perfil);
            System.out.println("Que bom ter " + usuario + " aqui!");
        }
        catch(NumberFormatException exception){
            System.out.println("CPF inválido!");
        }
        catch(PEException pee){
            System.out.println(pee.getMessage());
        }
    }

    public void cadastrarPJ(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            System.out.print("CNPJ: ");
            long cnpj = Long.parseLong(this.scanner.next());
            System.out.println();

            Perfil perfil = new PessoaJuridica(usuario, cnpj);

            myTwitter.criarPerfil(perfil);
            System.out.println("Que bom ter " + usuario + " aqui!");
        }
        catch(NumberFormatException exception){
            this.scanner.nextLine();
            System.out.println("CNPJ inválido!");
        }
        catch(PEException pee){
            System.out.println(pee.getMessage());
        }
    }

    public void tweetar(){
        this.scanner.nextLine(); //Lê o enter da linha anterior
        System.out.println("O que você está pensando? (de 1 a 140 caracteres):");
        String mensagem = this.scanner.nextLine();

        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            myTwitter.tweetar(usuario, mensagem);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void seguir(){
        String seguidor, seguido;

        System.out.print("Seguidor: @");
        seguidor = "@" + this.scanner.next();

        System.out.println();

        System.out.print("Seguido: @");
        seguido = "@" + this.scanner.next();

        System.out.println();
        try {
            myTwitter.seguir(seguidor, seguido);
            System.out.println(seguidor + " agora segue " + seguido + "!");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void timeline(){
        System.out.print("Digite o nome de usuário: @");
        String usuario = "@" + this.scanner.next();
        try {
            Vector<Tweet> timeline = myTwitter.timeline(usuario);

            for(Tweet tweet : timeline)
                tweet.getTweet();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void tweets(){
        System.out.print("Digite o nome de usuário: @");
        String usuario = "@" + this.scanner.next();
        try {
            Vector<Tweet> tweets = myTwitter.tweets(usuario);

            for(Tweet tweet : tweets)
                tweet.getTweet();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void seguidores(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            Vector<Perfil> seguidores = myTwitter.seguidores(usuario);
            int numeroSeguidores = myTwitter.numeroSeguidores(usuario);

            System.out.println("Número de seguidores: " + numeroSeguidores);
            int index = 1;
            for(Perfil follower : seguidores){
                System.out.println(index + ". " + follower.getUsuario());
                index++;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void seguidos(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try {
            Vector<Perfil> seguidos = myTwitter.seguidos(usuario);
            int index = 1;

            for(Perfil following : seguidos){
                System.out.println(index + ". " + following.getUsuario());
                index++;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void desativarConta(){
        System.out.println("Quer desativar? Que triste :(");
        System.out.print("Nome do usuário a ser desativado: @");

        String usuario = "@" + this.scanner.next();

        try {
            myTwitter.cancelarPerfil(usuario);

            System.out.println("O perfil de usuário " + usuario + " foi desativado :(");
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
