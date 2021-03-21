package Views;

import Database.ConnectionFactory;
import Models.Aluno;
import Modules.Controllers.AlunoController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewAluno {


    public ViewAluno() throws SQLException {
        verTelaAluno();
    }

    public static void verTelaAluno() throws SQLException {

        //crio uma instância scanner para poder ler o que o usuário digita
        Scanner scanner = new Scanner(System.in); //System.in serve para capturar os inputs(o que ele digita)

        //crio uma instância cliController para chamar meus metodos da classe AlunoController
        AlunoController cliController = new AlunoController();



        int escolha;

        do {
            exibirMenu();
            System.out.println("Insira sua opção: ");
            //variável para guardar as opções que vou dar para meu usuário
            escolha = scanner.nextInt();

            //switch para manipular a escolha do usuário
            switch (escolha) {
                case 1:
                    System.out.println("Opção 1 selecionada");
                    cliController.listarClientes();
                    break;

                case 2:
                    System.out.println("Insira um novo nome: ");
                    Scanner scannerString = new Scanner(System.in);
                    String nome = scannerString.nextLine(); //recebo esse novo nome
                    cliController.adicionarCliente(nome);
                    break;

                case 3:
                    System.out.println("Insira o id do aluno: ");
                    Scanner scannerId = new Scanner(System.in);
                    Integer id = scannerId.nextInt(); //recebo esse id
                    /* feito isso eu tenho que fazer uma busca dentro do meu banco de dados e implementar
                     * mais um método em ModelsDAO.AlunoDAO: public movie findById(Integer id).
                     * Porque precisa desse método? Para que através do id que o usuário inserir eu vou
                     * dentro do banco de dados, identifico se realmente existe e com base nisso consigo
                     * saber se meu id é válido ou não
                     */

                    //eu vou buscar o id que o usuário já inseriu
                    Aluno alunoExiste = new Aluno();
                    alunoExiste = cliController.buscarClienteporID(alunoExiste, id);

                    //verifico se o retorno é diferende de nulo
                    if (alunoExiste != null){
                        System.out.println("Insira o novo nome do aluno: ");
                        /* só pra lembrar, criei uma nova instância scanner porque estava dando erro ao usar
                         * uma instância que já tinha sido usada anteriormente.
                         */
                        Scanner scanNovoNomeCliente = new Scanner(System.in);
                        String novoNomeCliente = scanNovoNomeCliente.nextLine();
                        cliController.editarCliente(alunoExiste, novoNomeCliente);
                    } else {
                        System.out.println("Não existe nenhum aluno com esse id!");
                    }

                    break;
                case 4:
                    System.out.println("Opção 4 selecionada");

                    System.out.println("Insira o id do aluno: ");
                    Scanner scannerIdDelete = new Scanner(System.in);
                    Integer idDelete = scannerIdDelete.nextInt(); //recebo esse id

                    Aluno alunoExisteDelete = new Aluno();
                    alunoExisteDelete = cliController.buscarClienteporID(alunoExisteDelete, idDelete);

                    //verifico se o retorno é diferende de nulo
                    if (alunoExisteDelete != null){
                        cliController.removerCliente(alunoExisteDelete);
                        System.out.println("Aluno removido com sucesso");
                    } else {
                        System.out.println("Não existe nenhum aluno com esse id!");
                    }
                    break;
                case 5:
                    System.out.println("Você inseriu o valor 5. Você irá voltar ao menu principal...");
                    break;
                case 6:
                    System.out.println("Você inseriu o valor 6, o programa será encerrado.");
                    System.exit(0);
                default:
                    System.out.println("Opção Inválida");
            }

        } while (escolha >= 1 && escolha <= 4);
    }

    public static void exibirMenu(){
        System.out.printf("\n---------- MENU ALUNOS ----------\n");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Criar novo Aluno");
        System.out.println("3 - Alterar Aluno");
        System.out.println("4 - Deletar Aluno");
        System.out.println("5 - Voltar para o menu principal");
        System.out.println("6 - Sair do programa");
        System.out.println("---------- MENU ALUNOS ----------");

    }
}
