package Modules.Controllers;

import Views.ViewAluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipalController implements ActionListener {

    private JButton btnOpcao;

    public MenuPrincipalController(JButton opcao){
        this.btnOpcao = opcao;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String btnOpcao = this.btnOpcao.getActionCommand();

        switch (btnOpcao){
            case "verTelaAlunos":
                System.out.println("AEAEAE");
//                new ViewAluno();
            break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
        }

    }

}
