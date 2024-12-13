package br.edu.unisep.cadastro.view.telas;
import br.edu.unisep.cadastro.util.ControllerUtil;

import javax.swing.*;
import java.awt.*;

public class CadastroImovelView extends JFrame {
    private JTextField enderecoField;
    private JTextField tipoField;
    private JTextField areaField;
    private JTextField precoField;
    private JButton salvarButton;

    public CadastroImovelView() {
        setTitle("Cadastro de Imóvel");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        enderecoField = new JTextField(20);
        tipoField = new JTextField(20);
        areaField = new JTextField(20);
        precoField = new JTextField(20);
        salvarButton = new JButton("Salvar");

        add(new JLabel("Endereço:"));
        add(enderecoField);
        add(new JLabel("Tipo:"));
        add(tipoField);
        add(new JLabel("Área:"));
        add(areaField);
        add(new JLabel("Preço:"));
        add(precoField);
        add(salvarButton);

        salvarButton.addActionListener(e -> {
            try {
                String endereco = enderecoField.getText().trim();
                String tipo = tipoField.getText().trim();
                String areaStr = areaField.getText().trim();
                String precoStr = precoField.getText().trim();

                if (endereco.isEmpty() || tipo.isEmpty() || areaStr.isEmpty() || precoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double area = Double.parseDouble(areaStr);
                double preco = Double.parseDouble(precoStr);

                if (area <= 0 || preco <= 0) {
                    JOptionPane.showMessageDialog(this, "A área e o preço devem ser maiores que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ControllerUtil.salvarImovel(endereco, tipo, area, preco);
                JOptionPane.showMessageDialog(this, "Imóvel salvo com sucesso!");

                enderecoField.setText("");
                tipoField.setText("");
                areaField.setText("");
                precoField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "A área e o preço devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o imóvel.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
