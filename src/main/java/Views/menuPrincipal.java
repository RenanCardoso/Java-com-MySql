package Views;

import java.sql.SQLException;
import java.util.Scanner;

public class menuPrincipal {

    public static void main(String[] args) throws SQLException {

        exibirMenuPrincipal();

        System.out.println("Insira sua opção: ");
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        do {
            //switch para manipular a escolha do usuário
            switch (escolha) {
                case 1:
                    System.out.println("Opção 1 selecionada");
                    ViewAluno.verTelaAluno();
                    break;

                case 2:
                    System.out.println("Você inseriu o valor 2, o programa será encerrado.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção Inválida");
            }
            exibirMenuPrincipal();
            System.out.printf("Insira sua opção: ");
            escolha = scanner.nextInt();
        } while (escolha >= 1 || escolha <= 2);
    }

    public static void exibirMenuPrincipal(){
        System.out.printf("\n---------- MENU PRINCIPAL ----------\n");
        System.out.println("1 - Acessar tela de Alunos");
        System.out.println("2 - Sair do programa");
        System.out.println("---------- MENU PRINCIPAL ----------");
    }
}
