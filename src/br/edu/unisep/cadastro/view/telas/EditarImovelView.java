package br.edu.unisep.cadastro.view.telas;

import br.edu.unisep.cadastro.util.ControllerUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditarImovelView extends JFrame {

    private JComboBox<String> imoveisComboBox;
    private JTextField enderecoField, tipoField, areaField, precoField;
    private JButton salvarButton, excluirButton, voltarButton;

    public EditarImovelView() {
        setTitle("Editar Imóvel");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));  // Usando GridLayout para organização

        enderecoField = new JTextField(20);
        tipoField = new JTextField(20);
        areaField = new JTextField(20);
        precoField = new JTextField(20);
        salvarButton = new JButton("Salvar");
        excluirButton = new JButton("Excluir Imóvel");
        voltarButton = new JButton("Voltar");

        imoveisComboBox = new JComboBox<>();
        carregarImoveis();

        add(new JLabel("Selecione o Imóvel:"));
        add(imoveisComboBox);
        add(new JLabel("Endereço:"));
        add(enderecoField);
        add(new JLabel("Tipo:"));
        add(tipoField);
        add(new JLabel("Área (m²):"));
        add(areaField);
        add(new JLabel("Preço (R$):"));
        add(precoField);
        add(salvarButton);
        add(excluirButton);
        add(voltarButton);

        imoveisComboBox.addActionListener(e -> carregarDadosImovel());

        salvarButton.addActionListener(e -> editarImovel());
l
        excluirButton.addActionListener(e -> excluirImovel());

        voltarButton.addActionListener(e -> voltar());

        setVisible(true);
    }

    private void carregarImoveis() {
        imoveisComboBox.removeAllItems();
        ArrayList<String> imoveis = ControllerUtil.listarImoveis();
        for (String imovel : imoveis) {
            imoveisComboBox.addItem(imovel);
        }
    }

    private void carregarDadosImovel() {
        String selectedImovel = (String) imoveisComboBox.getSelectedItem();
        if (selectedImovel != null) {
            String[] dados = selectedImovel.split(",");
            enderecoField.setText(dados[0].trim());
            tipoField.setText(dados[1].trim());
            areaField.setText(dados[2].trim());
            precoField.setText(dados[3].trim());
        }
    }

    private void editarImovel() {
        try {
            String endereco = enderecoField.getText();
            String tipo = tipoField.getText();
            double area = Double.parseDouble(areaField.getText());
            double preco = Double.parseDouble(precoField.getText());

            int indice = imoveisComboBox.getSelectedIndex();

            if (indice == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um imóvel para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ControllerUtil.editarImovel(indice, endereco, tipo, area, preco);

            JOptionPane.showMessageDialog(this, "Imóvel editado com sucesso!");
            carregarImoveis();  // Recarregar os imóveis após a edição
            dispose();  // Fecha a tela de edição
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para área e preço.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirImovel() {
        int indice = imoveisComboBox.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum imóvel selecionado para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este imóvel?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ControllerUtil.excluirImovel(indice);
            JOptionPane.showMessageDialog(this, "Imóvel excluído com sucesso!");
            carregarImoveis();  // Recarregar os imóveis após a exclusão
            dispose();
        }
    }
    private void voltar() {
        dispose();
    }
}
