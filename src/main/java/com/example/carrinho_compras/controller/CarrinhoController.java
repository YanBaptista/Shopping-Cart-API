package com.example.carrinho_compras.controller;

import com.example.carrinho_compras.model.Produto;
import com.example.carrinho_compras.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {


    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    // Rota para ver os itens: GET http://localhost:8080/carrinho/itens
    @GetMapping("/itens")
    public List<Produto> listarItens() {
        return carrinhoService.getItens();
    }

    // Rota para ver o total: GET http://localhost:8080/carrinho/total
    @GetMapping("/total")
    public double verTotal() {
        return carrinhoService.calcularTotal();
    }

    // Rota para adicionar um produto: POST http://localhost:8080/carrinho/adicionar
    @PostMapping("/adicionar")
    public void adicionarProdutoAoCarrinho(@RequestBody Produto produto) {

        carrinhoService.adicionarProduto(produto);
    }

    // Rota para aplicar o cupom: POST http://localhost:8080/carrinho/cupom?codigo=CUPOMZADA
    @PostMapping("/cupom")
    public void aplicarCupom(@RequestParam String codigo) {
        // O @RequestParam pega o texto que vem na URL após o ponto de interrogação
        carrinhoService.addCupom(codigo);
    }
}