package Views;

import Modules.Controllers.MenuPrincipalController;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView {

    //    defino os componentes que serão utilizados
    private JFrame mainFrame;
    private JLabel selecioneOpcao;
    private JButton btnOpcao;

    public MenuPrincipalView() {
        verMenuPrincipal();
    }

    private void verMenuPrincipal() {

        mainFrame = new JFrame();
        mainFrame.setBounds(100, 200, 1366, 768);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quando clicar no x vai sair do app
        mainFrame.setTitle("Sistema Academia");
        mainFrame.setLayout(new FlowLayout(0));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        btnOpcao = new JButton("Ver e Gerenciar Alunos");
        btnOpcao.setActionCommand("verTelaAlunos");

        mainFrame.add(mainPanel);
        mainFrame.add(btnOpcao);

        //aqui chamo a controller pra ver qual opcao o usuario escolheu
        btnOpcao.addActionListener(new MenuPrincipalController(btnOpcao));

        mainFrame.setVisible(true);
    }
}