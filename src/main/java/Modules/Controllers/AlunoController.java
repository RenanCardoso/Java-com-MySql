package Modules.Controllers;

import Models.Aluno;
import ModelsDAO.AlunoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AlunoController {

    //aqui vou criar o meu ModelsDAO.AlunoDAO
    AlunoDAO dao = new AlunoDAO();

    public Aluno buscarClienteporID(Aluno aluno, Integer id) throws SQLException {
        aluno = dao.findById(id);
        return aluno;
    }

    public void listarClientes() throws SQLException {
        //tem que fazer um cast indicando que vou utilizar de fato uma lista
        ArrayList<Aluno> data = (ArrayList<Aluno>) dao.findAll();

        //adiciono aqui a minha model data e assim consigo usar meus getters e setters e exibir o que for necessário
        for (Aluno aluno : data){
            System.out.println("Id: " + aluno.getId());
            System.out.println("Nome: " + aluno.getName());
        }
    }

    public void adicionarCliente(String nome) throws SQLException {
        //aqui faço a inserção
        dao.insert(new Aluno(nome));
        System.out.println("Aluno adicionado com sucesso");
    }

    public void editarCliente(Aluno alunoAntigo, String novoNomeCliente) throws SQLException {
        /* o meu dao.update espera dois argumentos, o primeiro é o antigo dado(clienteExists)
         * e o segundo é o novo dado (uma instância nova)
         */

        Aluno novoAluno = new Aluno(alunoAntigo.getId(), novoNomeCliente); //criei a instância do objeto passando o nome que o usuário acabou de inserir
        dao.update(alunoAntigo, novoAluno);
        System.out.println("Aluno alterado com sucesso");
    }

    public void removerCliente(Aluno alunoExisteDelete) throws SQLException {
        /* o meu dao.update espera dois argumentos, o primeiro é o antigo dado(clienteExists)
         * e o segundo é o novo dado (uma instância nova)
         */
        dao.remove(alunoExisteDelete);
    }



}
