import classes.*;
import exceptions.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;


public class Main{

    public static void main(String[] args){
        MyTwitter myTwitter = new MyTwitter(new RepositorioUsuario());
        Scanner scanner = new Scanner(System.in);
        int option = 100;

        String usuario;
        Vector<Tweet> timeline, tweets;
        Vector<Perfil> seguidores, seguidos;

        System.out.println("----------- Bem-vindo(a) ao MyTwitter! -----------");
        while(option != 0){
            option = menu(scanner);

            System.out.println();
            if(option == 1){
                System.out.print("Nome de usuário: @");
                usuario = "@" + scanner.next();

                System.out.print("CPF: ");
                long cpf = scanner.nextLong();

                System.out.println();
                Perfil perfil = new PessoaFisica(usuario, cpf);
                try{
                    myTwitter.criarPerfil(perfil);
                    System.out.println("Perfil cadastrado com sucesso!");
                }
                catch(PEException pee){
                    System.out.println(pee.getMessage());
                }
            }

            else if(option == 2){
                System.out.print("Nome de usuário: @");
                usuario = "@" + scanner.next();

                System.out.print("CNPJ: ");
                long cnpj = scanner.nextLong();
                System.out.println();
                Perfil perfil = new PessoaJuridica(usuario, cnpj);
                try{
                    myTwitter.criarPerfil(perfil);
                    System.out.println("Perfil cadastrado com sucesso!");
                }
                catch(PEException pee){
                    System.out.println(pee.getMessage());
                }
            }

            else if(option == 3){
                scanner.nextLine(); //Lê o enter da linha anterior
                System.out.println("Mensagem (de 1 a 140 caracteres):");
                String mensagem = scanner.nextLine();

                System.out.print("Nome de usuário: @");
                usuario = "@" + scanner.next();

                try{
                    myTwitter.tweetar(usuario, mensagem);
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
            }

            else if(option == 4){
                String seguidor, seguido;

                System.out.print("Seguidor: @");
                seguidor = "@" + scanner.next();

                System.out.println();

                System.out.print("Seguido: @");
                seguido = "@" + scanner.next();

                System.out.println();
                try {
                    myTwitter.seguir(seguidor, seguido);
                    System.out.println(seguidor + " agora segue " + seguido + "!");
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }

            else if(option == 5){
                System.out.print("Digite o nome de usuário: @");
                usuario = "@" + scanner.next();
                try {
                    timeline = myTwitter.timeline(usuario);

                    for(Tweet tweet : timeline)
                        tweet.getTweet();
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }

            else if(option == 6){
                System.out.print("Digite o nome de usuário: @");
                usuario = "@" + scanner.next();
                try {
                    tweets = myTwitter.tweets(usuario);

                    for(Tweet tweet : tweets)
                        tweet.getTweet();
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }

            else if(option == 7){
                System.out.print("Nome de usuário: @");
                usuario = "@" + scanner.next();

                try{
                    seguidores = myTwitter.seguidores(usuario);
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

            else if(option == 8){
                System.out.print("Nome de usuário: @");
                usuario = "@" + scanner.next();

                try {
                    seguidos = myTwitter.seguidos(usuario);
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

            else if(option == 9){
                System.out.println("Quer desativar? Que triste :(");
                System.out.print("Nome do usuário a ser desativado: ");

                usuario = "@" + scanner.next();

                try {
                    myTwitter.cancelarPerfil(usuario);

                    System.out.println("O perfil de usuário " + usuario + " foi desativado :(");
                } 
                catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

            else if(option == 0)
                System.out.println("------------- Tchau, até a próxima! -------------");

            else {
                System.out.println("Opção inexistente");
            }

            System.out.println();
        }
    }


    // Função que imprime as operações possíveis do programa
    public static int menu(Scanner scanner){
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
            option = Integer.parseInt(scanner.next());
        }
        catch(NumberFormatException exception){
            scanner.nextLine();
            option = -1;
        }

        return option;
    }



}
