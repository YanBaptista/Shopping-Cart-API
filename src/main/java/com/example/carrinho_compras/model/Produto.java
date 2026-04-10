package com.example.carrinho_compras.model;

public class Produto {
    private String id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String id,String nome,double preco,int quantidade){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
