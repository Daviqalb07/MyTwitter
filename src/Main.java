import java.util.Scanner;
import classes.MyTwitter;
import classes.RepositorioUsuario;
import Controllers.MainController;


public class Main{

    public static void main(String[] args){
        MyTwitter myTwitter = new MyTwitter(new RepositorioUsuario());
        MainController mainController = new MainController(myTwitter, new Scanner(System.in));

        int option = 100;

        while(option != 0){

            option = mainController.menu();

            System.out.println();
            if(option == 1){
                mainController.cadastrarPF();
            }

            else if(option == 2){
                mainController.cadastrarPJ();
            }

            else if(option == 3){
                mainController.tweetar();
            }

            else if(option == 4){
                mainController.seguir();
            }

            else if(option == 5){
                mainController.timeline();
            }

            else if(option == 6){
                mainController.tweets();
            }

            else if(option == 7){
                mainController.seguidores();
            }

            else if(option == 8){
                mainController.seguidos();
            }

            else if(option == 9){
                mainController.desativarConta();
            }

            else if(option == 0)
                continue;

            else {
                System.out.println("Opção inexistente");
            }

            mainController.limparConsole();
        }

        System.out.println("--------------- Tchau, até a próxima! ---------------");

    }
}
