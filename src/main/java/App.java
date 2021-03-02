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
        exibirMenu();
        System.out.println("Insira sua opção: ");

        //variável para guardar as opções que vou dar para meu usuário
        int choice = scanner.nextInt();

        do {

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
                    System.out.println("Filme inserido com sucesso");
                    break;

                case 3:
                    System.out.println("Insira o id do filme: ");
                    Scanner scannerId = new Scanner(System.in);
                    Integer id = scannerId.nextInt(); //recebo esse id
                    /* feito isso eu tenho que fazer uma busca dentro do meu banco de dados e implementar
                     * mais um método em MovieDAO: public movie findById(Integer id).
                     * Porque precisa desse método? Para que através do id que o usuário inserir eu vou
                     * dentro do banco de dados, identifico se realmente existe e com base nisso consigo
                     * saber se meu id é válido ou não
                     */

                    //eu vou buscar o id que o usuário já inseriu
                    Movie movieExists = dao.findById(id);

                    //verifico se o retorno é diferende de nulo
                    if (movieExists != null){
                        System.out.println("Insira o novo nome do filme: ");
                        /* só pra lembrar, criei uma nova instância scanner porque estava dando erro ao usar
                         * uma instância que já tinha sido usada anteriormente.
                         */
                        Scanner scanNewNameMovie = new Scanner(System.in);
                        String newNameMovie = scanNewNameMovie.nextLine();

                        /* o meu dao.update espera dois argumentos, o primeiro é o antigo dado(movieExists)
                         * e o segundo é o novo dado (uma instância nova)
                         */
                        Movie movieNew = new Movie(movieExists.getId(), newNameMovie); //criei a instância do objeto passando o nome que o usuário acabou de inserir
                        dao.update(movieExists, movieNew);
                        System.out.println("Filme alterado com sucesso");
                    } else {
                        System.out.println("Não existe nenhum filme com esse id!");
                    }
                    break;
                case 4:
                    System.out.println("Opção 4 selecionada");

                    System.out.println("Insira o id do filme: ");
                    Scanner scannerIdDelete = new Scanner(System.in);
                    Integer idDelete = scannerIdDelete.nextInt(); //recebo esse id

                    //eu vou buscar o id que o usuário já inseriu
                    Movie movieExistsDelete = dao.findById(idDelete);

                    //verifico se o retorno é diferende de nulo
                    if (movieExistsDelete != null){
                        dao.remove(movieExistsDelete);
                        System.out.println("Filme removido com sucesso");
                    } else {
                        System.out.println("Não existe nenhum filme com esse id!");
                    }
                    break;
                case 5:
                    System.out.println("Você inseriu o valor 5, o programa será encerrado.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida");
            }

            exibirMenu();
            System.out.printf("Insira sua opção: ");
            choice = scanner.nextInt();
        } while (choice >= 1 || choice <= 5);
    }

    public static void exibirMenu(){
        System.out.println("---------- MENU ----------");
        System.out.println("1 - Listar filmes");
        System.out.println("2 - Criar novo filme");
        System.out.println("3 - Alterar filme");
        System.out.println("4 - Deletar filme");
        System.out.println("5 - Sair do programa");
        System.out.println("---------- MENU ----------");

    }
}
