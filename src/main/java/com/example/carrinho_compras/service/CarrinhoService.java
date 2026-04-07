package com.example.carrinho_compras.service;

import com.example.carrinho_compras.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

    private List<Produto> itens = new ArrayList<>();


    private double descontoCupom = 0.0;

    public void adicionarProduto(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero");
        }

        if (produto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        itens.add(produto);
    }


    public void addCupom(String cupom) {

        if ("BEMVINDO10".equalsIgnoreCase(cupom)) {
            this.descontoCupom = 0.10;
        } else {

            throw new IllegalArgumentException("Cupom inválido ou expirado!");
        }
    }

    public double calcularTotal() {
        double total = 0.0;


        for (Produto produto : itens) {
            total = total + (produto.getPreco() * produto.getQuantidade());
        }


        total = total - (total * descontoCupom);

        return total;
    }

    public List<Produto> getItens() {
        return itens;
    }
}