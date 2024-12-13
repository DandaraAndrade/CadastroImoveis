package br.edu.unisep.cadastro.util;

import br.edu.unisep.cadastro.model.Imovel;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class ControllerUtil {

    private static final Path CAMINHO_ARQUIVO = Paths.get("imoveis.txt");

    private static void garantirArquivoExiste() {
        if (!Files.exists(CAMINHO_ARQUIVO)) {
            try {
                Files.createFile(CAMINHO_ARQUIVO);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar o arquivo de imóveis.", e);
            }
        }
    }

    public static void excluirImovel(int indice) {
        garantirArquivoExiste();
        ArrayList<String> imoveis = listarImoveis();

        if (indice < 0 || indice >= imoveis.size()) {
            throw new IllegalArgumentException("Índice de imóvel inválido.");
        }

        imoveis.remove(indice);

        try (BufferedWriter writer = Files.newBufferedWriter(CAMINHO_ARQUIVO)) {
            for (String imv : imoveis) {
                writer.write(imv);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao excluir o imóvel do arquivo.", e);
        }
    }

    public static ArrayList<String> listarImoveis() {
        garantirArquivoExiste();
        ArrayList<String> imoveis = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(CAMINHO_ARQUIVO)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                imoveis.add(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de imóveis.", e);
        }

        return imoveis;
    }

    public static void editarImovel(int indice, String endereco, String tipo, double area, double preco) {
        garantirArquivoExiste();
        ArrayList<String> imoveis = listarImoveis();

        if (indice < 0 || indice >= imoveis.size()) {
            throw new IllegalArgumentException("Índice de imóvel inválido.");
        }

        if (endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio.");
        }
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo não pode ser vazio.");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Área deve ser maior que zero.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }

        Imovel imovelEditado = new Imovel(endereco, tipo, area, preco);

        imoveis.set(indice, imovelEditado.toString());

        try (BufferedWriter writer = Files.newBufferedWriter(CAMINHO_ARQUIVO)) {
            for (String imv : imoveis) {
                writer.write(imv);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Imóvel editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o arquivo de imóveis.", e);
        }
    }

    public static void salvarImovel(String endereco, String tipo, double area, double preco) {
        garantirArquivoExiste();

        if (endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio.");
        }
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo não pode ser vazio.");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Área deve ser maior que zero.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }

        Imovel imovelNovo = new Imovel(endereco, tipo, area, preco);

        try (BufferedWriter writer = Files.newBufferedWriter(CAMINHO_ARQUIVO, StandardOpenOption.APPEND)) {
            writer.write(imovelNovo.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Imóvel cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o imóvel no arquivo.", e);
        }
    }
}

