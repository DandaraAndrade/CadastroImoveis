package br.edu.unisep.cadastro.view.telas;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Menu");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton cadastrarButton = new JButton("Cadastrar Imóvel");
        JButton listarButton = new JButton("Listar Imóveis");

        cadastrarButton.addActionListener(e -> {
            this.dispose();
            new CadastroImovelView();
        });

        listarButton.addActionListener(e -> {
            this.dispose();
            new ListaImoveisView();
        });

        add(cadastrarButton);
        add(listarButton);

        setVisible(true);
    }
}
