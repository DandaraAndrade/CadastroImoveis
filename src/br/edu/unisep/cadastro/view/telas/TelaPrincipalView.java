package br.edu.unisep.cadastro.view.telas;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipalView extends JFrame {

    public TelaPrincipalView() {
        setTitle("Tela Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton cadastrarButton = new JButton("Cadastrar Imóvel");
        JButton listarButton = new JButton("Listar Imóveis");
        JButton editarButton = new JButton("Editar Imóvel");

        cadastrarButton.addActionListener(e -> {
            this.dispose();
            new CadastroImovelView();
        });

        listarButton.addActionListener(e -> {
            this.dispose();
            new ListaImoveisView();
        });


        editarButton.addActionListener(e -> {
            this.dispose();
            new EditarImovelView();
        });

        add(cadastrarButton);
        add(listarButton);
        add(editarButton);

        setVisible(true);
    }
}
