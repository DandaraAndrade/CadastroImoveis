package br.edu.unisep.cadastro.model;

public class Imovel {
    private String endereco;
    private String tipo;
    private double area;
    private double preco;

    public Imovel(String endereco, String tipo, double area, double preco) {
        this.endereco = endereco;
        this.tipo = tipo;
        this.area = area;
        this.preco = preco;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return endereco + ";" + tipo + ";" + area + ";" + preco;
    }
}
