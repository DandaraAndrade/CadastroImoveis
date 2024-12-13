package br.edu.unisep.cadastro.view.telas;

import br.edu.unisep.cadastro.util.ControllerUtil;  // Corrigindo a importação

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListaImoveisView extends JFrame {

    private JTextArea area;
    private ControllerUtil controllerUtil;
    public ListaImoveisView() {
        // Configurações da janela
        setTitle("Lista de Imóveis");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(fecharButton);
        add(bottomPanel, BorderLayout.SOUTH);

        controllerUtil = new ControllerUtil();

        carregarImoveis();

        setVisible(true);
    }

    private void carregarImoveis() {
        ArrayList<String> imoveis = controllerUtil.listarImoveis();
        if (imoveis.isEmpty()) {
            area.append("Nenhum imóvel cadastrado.\n");
        } else {
            for (String imovel : imoveis) {
                area.append(imovel + "\n");
            }
        }
    }
}
