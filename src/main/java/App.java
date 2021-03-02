import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static Connection CONNECTION = null;
    // A minha Connection vai receber a minha Factory toda vez que acessar o meu main

    public static void main(String[] args) throws SQLException{

        //crio uma instância scanner para poder ler o que o usuário digita
        Scanner scanner = new Scanner(System.in); //System.in serve para capturar os inputs(o que ele digita)

        // Para trabalhar com a classe ConnectionFactory vamos iniciar uma nova conexão
        CONNECTION = new ConnectionFactory().getConnection();
        /* automaticamente já vou ter minha conexão pronta pra ser utilizada através
         * da variável con
         */

        //aqui vou criar o meu MovieDAO
        MovieDAO dao = new MovieDAO();

        System.out.println("---------- MENU ----------");
        System.out.println("1 - Listar filmes");
        System.out.println("2 - Criar novo filme");
        System.out.println("3 - Alterar filme");
        System.out.println("4 - Deletar filme");
        System.out.println("---------- MENU ----------");

        //variável para guardar as opções que vou dar para meu usuário
        int choice = scanner.nextInt();

        //switch para manipular a escolha do usuário
        switch (choice) {
            case 1:
                System.out.println("Opção 1 selecionada");
                //tem que fazer um cast indicando que vou utilizar de fato uma lista
                ArrayList<Movie> data = (ArrayList<Movie>) dao.findAll();

                //adiciono aqui a minha model data e assim consigo usar meus getters e setters e exibir o que for necessário
                for (Movie movie : data){
                    System.out.println("Id: " + movie.getId());
                    System.out.println("Nome: " + movie.getName());
                }

                break;

            case 2:
                System.out.println("Insira um novo nome: ");
                Scanner scannerString = new Scanner(System.in);
                String name = scannerString.nextLine(); //recebo esse novo nome

                //aqui faço a inserção
                dao.insert(new Movie(name));
                break;

            case 3:
                System.out.println("Insira o id do filme: ");
                Scanner scannerId = new Scanner(System.in);
                Integer id = scannerId.nextInt(); //recebo esse id
                break;
            case 4:
                System.out.println("Opção 4 selecionada");
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }
}
