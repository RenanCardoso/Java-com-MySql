package Views;

import Modules.Controllers.MenuPrincipalController;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView {

    //    defino os componentes que serão utilizados
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton btnAcao;

    public MenuPrincipalView(){
        verMenuPrincipal();
    }

    private void verMenuPrincipal(){

//        crio o meu JFrame
        mainFrame = new JFrame("Sistema Academia");
        mainFrame.setBounds(100, 100, 450, 250);
//        e coloco a operação de fechar padrão no botão x
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout(0));

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        //abaixo eu crio os meus componentes
        btnAcao = new JButton("Ver e Gerenciar Alunos");
        btnAcao.setActionCommand("verTelaAlunos");

        mainFrame.add(mainPanel);
        mainFrame.add(btnAcao);

//        aqui vou trabalhar com meus eventos
        btnAcao.addActionListener(new MenuPrincipalController(btnAcao)); //a partir daqui a Controller passará a assumir

        mainFrame.setVisible(true);
    }
}
