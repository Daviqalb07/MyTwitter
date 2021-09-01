package Controllers;


import classes.*;
import exceptions.*;
import interfaces.ITwitter;

import java.util.Scanner;
import java.util.Vector;

/* class MainController
*  "Conexão" entre o usuário do programa e a objeto com interface MyTwitter.
* */
public class MainController {
    private ITwitter myTwitter;
    private Scanner scanner;

    // Construtor da classe.
    public MainController(ITwitter myTwitter, Scanner scanner) {
        this.myTwitter = myTwitter;
        this.scanner = scanner;
    }

    /* limparConsole() -> void
    * Espera o usuário pressionar Enter e "limpa" o console. */
    public void limparConsole(){
        try {
            System.out.println("Pressione Enter para continuar");
            this.scanner.nextLine();
            this.scanner.nextLine();
            for(int i = 0 ; i < 250 ; i++)
                System.out.println();
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    /* menu() -> int
    * Função que apresenta o menu de opções e lê a ação desejada
    * RETORNO: opção desejada pelo usuário. -1 caso não seja digitado um número inteiro.
    * Trata a exceção: entrada de dados não é um número inteiro.
    * */
    public int menu(){
        System.out.println("------------- Este é o MyTwitter! -------------");
        System.out.println();

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

        System.out.print("O que deseja fazer? ");

        int option;
        // Trata a exceção de não digitar um inteiro na opção.
        try{
            option = Integer.parseInt(this.scanner.next());
        }
        catch(NumberFormatException exception){
            option = -1;
        }

        return option;
    }

    /* cadastrarPF() -> void
    *  Cria um perfil do tipo PessoaFisica e adiciona ao repositório.
    *  Trata as exceções: não ser digitado um número no campo CPF; perfil já existe.
    * */
    public void cadastrarPF(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            System.out.print("CPF: ");
            long cpf = Long.parseLong(this.scanner.next());
            System.out.println();


            myTwitter.criarPerfil(new PessoaFisica(usuario, cpf));
            System.out.println("Que bom ter " + usuario + " aqui!");
        }
        catch(NumberFormatException exception){
            System.out.println("CPF inválido!");
        }
        catch(PEException pee){
            System.out.println(pee.getMessage());
        }
    }

    /* cadastrarPJ() -> void
     *  Cria um perfil do tipo PessoaJuridica e adiciona ao repositório.
     *  Trata as exceções: não ser digitado um número no campo CNPJ; perfil já existe.
     * */
    public void cadastrarPJ(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            System.out.print("CNPJ: ");
            long cnpj = Long.parseLong(this.scanner.next());
            System.out.println();


            myTwitter.criarPerfil(new PessoaJuridica(usuario, cnpj));
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

    /* tweetar() -> void
     *  Cria um Tweet com a mensagem digitada e faz a ligação do mesmo
     *   com o usuário indicado.
     *  Trata as exceções: mensagem não está nos padrões de um tweet;
     *   perfil não existe; perfil existe, mas está desativado.
     * */
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

    /* seguir() -> void
    *  Recebe os nomes de usuário dos perfis seguidor e seguido, colocando este na
    *   lista de seguidos do primeiro e aquele na lista de seguidores do segundo.
    *  Trata as exceções: algum dos perfis não existe; algum dos perfis existe mas
    *   está desativado; os perfis são idênticos; seguidor já segue o seguido.
    * */
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

    /* timeline() -> void
    *  Recupera os tweets de um perfil e de seus seguidos e imprime na tela.
    *  Trata as exceções: perfil não existe; perfil existe, mas está desativado.
    * */
    public void timeline(){
        System.out.print("Digite o nome de usuário: @");
        String usuario = "@" + this.scanner.next();
        try {
            Vector<Tweet> timeline = myTwitter.timeline(usuario);

            System.out.println("Esta é a timeline de " + usuario + "!");
            System.out.println();
            for(Tweet tweet : timeline)
                tweet.getTweet();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    /* timeline() -> void
     *  Recupera os tweets de um perfil e imprime na tela.
     *  Trata as exceções: perfil não existe; perfil existe, mas está desativado.
     * */
    public void tweets(){
        System.out.print("Digite o nome de usuário: @");
        String usuario = "@" + this.scanner.next();
        try {
            Vector<Tweet> tweets = myTwitter.tweets(usuario);

            System.out.println("Estes são os tweets de " + usuario + "!");
            for(Tweet tweet : tweets)
                tweet.getTweet();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    /* seguidores() -> void
    *  Recupera a lista de seguidores de um perfil e imprime os nomes de usuários
    *   dos mesmos.
    *  Trata as exceções: perfil não existe; perfil existe, mas está desativado.
    * */
    public void seguidores(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try{
            Vector<Perfil> seguidores = myTwitter.seguidores(usuario);
            int numeroSeguidores = myTwitter.numeroSeguidores(usuario);

            System.out.println(usuario + " é seguido por " + numeroSeguidores + " perfis!");
            System.out.println();
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

    /* seguidos() -> void
     *  Recupera a lista de seguidos de um perfil e imprime os nomes de usuários
     *   dos mesmos.
     *  Trata as exceções: perfil não existe; perfil existe, mas está desativado.
     * */
    public void seguidos(){
        System.out.print("Nome de usuário: @");
        String usuario = "@" + this.scanner.next();

        try {
            Vector<Perfil> seguidos = myTwitter.seguidos(usuario);
            int index = 1;

            System.out.println(usuario + " segue:");
            System.out.println();
            for(Perfil following : seguidos){
                System.out.println(index + ". " + following.getUsuario());
                index++;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    /* desativarConta() -> void
    *  Recebe o nome de usuário do perfil a ser desativado e realiza a operação.
    *  Trata as exceções: perfil não existe; perfil existe, mas está desativado.
    * */
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
