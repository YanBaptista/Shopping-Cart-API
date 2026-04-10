package com.example.carrinho_compras;


import com.example.carrinho_compras.model.Produto;
import com.example.carrinho_compras.service.CarrinhoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CarrinhoComprasApplicationTests {

	@Test
	void adicionarProdutoComSucesso() {

		CarrinhoService carrinho = new CarrinhoService();

		Produto p1 = new Produto("1", "Teclado", 100.0, 1);

		carrinho.adicionarProduto(p1);
		assertEquals(1, carrinho.getItens().size());
	}

	@Test
	void calcularTotalCorretamenteSemDesconto() {

		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Mouse", 50.0, 1);
		Produto p2 = new Produto("2", "Monitor", 150.0, 1);


		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p2);

		// Assert (50 + 150 = 200)
		assertEquals(200.0, carrinho.calcularTotal());
	}

	@Test
	void calcularTotalConsiderandoAQuantidadeDoProduto() {

		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Cabo USB", 30.0, 4); // 4 produtos de 30.0


		carrinho.adicionarProduto(p1);

		// Assert (30 * 4 = 120)
		assertEquals(120.0, carrinho.calcularTotal());
	}

	@Test
	void aplicarCupomDezPorcentoComSucesso() {

		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "TV", 1000.0, 1);


		carrinho.adicionarProduto(p1);
		carrinho.addCupom("BEMVINDO10"); // 10% de desconto

		// Assert (1000 - 10% = 900)
		assertEquals(900.0, carrinho.calcularTotal());
	}

	@Test
	void naoDeveAplicarCupomComLetrasMinusculas() {

		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Celular", 500.0, 1);
		carrinho.adicionarProduto(p1);


		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.addCupom("cupomzada");
		});
	}

	@Test
	void naoDeveAdicionarProdutoComPrecoZero(){
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Fone", 0.0, 1);

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(p1);
		});
	}

	@Test
	void naoDeveAdicionarProdutoComPrecoNegativo() {
		// Arrange
		CarrinhoService carrinho = new CarrinhoService();
		// Criando produto com preço -10.0
		Produto p1 = new Produto("1", "Fone", -10.0, 1);

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(p1);
		});
	}

	@Test
	void naoDeveAdicionarProdutoComQuantidadeZero() {
		// Arrange
		CarrinhoService carrinho = new CarrinhoService();
		// Criando produto com quantidade 0
		Produto p1 = new Produto("2", "Mouse", 50.0, 0);

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(p1);
		});
	}

	@Test
	void naoDeveAdicionarProdutoComQuantidadeNegativa() {
		// Arrange
		CarrinhoService carrinho = new CarrinhoService();
		// Criando produto com quantidade -1
		Produto p1 = new Produto("3", "Cabo", 50.0, -1);

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(p1);
		});
	}

	@Test
	void carrinhoDeveIniciarVazio() {
		CarrinhoService carrinho = new CarrinhoService();

		assertEquals(0, carrinho.getItens().size());
		assertEquals(0.0, carrinho.calcularTotal());
	}

	@Test
	void adicionarMultiplosProdutosAumentaTamanhoDaLista() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Processador", 1000.0, 1);
		Produto p2 = new Produto("2", "Placa Mãe", 800.0, 1);
		Produto p3 = new Produto("3", "Memória RAM", 300.0, 1);

		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p2);
		carrinho.adicionarProduto(p3);

		assertEquals(3, carrinho.getItens().size());
	}

	@Test
	void calcularTotalComPrecosFracionados() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Cabo HDMI", 25.50, 2); // 51.0
		Produto p2 = new Produto("2", "Pen Drive", 35.75, 1); // 35.75

		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p2);

		assertEquals(86.75, carrinho.calcularTotal());
	}

	@Test
	void calcularTotalComCupomEVariosProdutos() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Notebook", 3000.0, 1);
		Produto p2 = new Produto("2", "Mousepad", 100.0, 1);

		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p2);
		carrinho.addCupom("BEMVINDO10");

		assertEquals(2790.0, carrinho.calcularTotal());
	}

	@Test
	void calcularTotalComQuantidadeAltaDeUmUnicoProduto() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Parafuso", 0.50, 1000); // 1000 parafusos

		carrinho.adicionarProduto(p1);

		assertEquals(500.0, carrinho.calcularTotal());
	}


	@Test
	void naoDeveAdicionarProdutoNulo() {
		CarrinhoService carrinho = new CarrinhoService();

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(null);
		});
	}

	@Test
	void naoDeveAplicarCupomVazio() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Teclado", 100.0, 1);
		carrinho.adicionarProduto(p1);

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.addCupom("");
		});
	}

	@Test
	void naoDeveAplicarCupomNulo() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Teclado", 100.0, 1);
		carrinho.adicionarProduto(p1);

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.addCupom(null);
		});
	}

	@Test
	void naoDeveAdicionarProdutoComNomeVazio() {
		CarrinhoService carrinho = new CarrinhoService();

		Produto p1 = new Produto("1", "", 100.0, 1);

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.adicionarProduto(p1);
		});
	}

	@Test
	void naoDeveAplicarCupomInvalidoOuInexistente() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Monitor", 800.0, 1);
		carrinho.adicionarProduto(p1);

		assertThrows(IllegalArgumentException.class, () -> {
			carrinho.addCupom("CUPOM_FALSO_123");
		});
	}

	@Test
	void adicionarOMesmoProdutoDuasVezes_SomaValoresCorretamente() {
		CarrinhoService carrinho = new CarrinhoService();
		Produto p1 = new Produto("1", "Cabo", 10.0, 1);

		carrinho.adicionarProduto(p1);
		carrinho.adicionarProduto(p1); // Adiciona de novo

		// Assert: 10.0 + 10.0 = 20.0 e 2 itens na lista
		assertEquals(2, carrinho.getItens().size());
		assertEquals(20.0, carrinho.calcularTotal());
	}

}
