package com.yuri.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.cursomc.domain.Categoria;
import com.yuri.cursomc.domain.Cidade;
import com.yuri.cursomc.domain.Cliente;
import com.yuri.cursomc.domain.Endereco;
import com.yuri.cursomc.domain.Estado;
import com.yuri.cursomc.domain.Produto;
import com.yuri.cursomc.domain.enums.TipoCliente;
import com.yuri.cursomc.repositories.CategoriaRepository;
import com.yuri.cursomc.repositories.CidadeRepository;
import com.yuri.cursomc.repositories.ClienteRepository;
import com.yuri.cursomc.repositories.EnderecoRepository;
import com.yuri.cursomc.repositories.EstadoRepository;
import com.yuri.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*-------------CATEGORIA PRODRUTOS----------------*/
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// Associação de categorias com produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		// Associação de produtos com categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		/*------------- ESTADO/CIDADES ----------------*/
		// Declaração Estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		// Declaração Cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "São José dos Campo", est2);

		// Associação de Estados > Cidades
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		// Salvar estados e cidades
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		/*-------------CLIENTE ENDEREÇO----------------*/
		Cliente cli1 = new Cliente(null, "Yuri Campos", "yuri@gmail.com", "003118008", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("4002-8922", "4777-7000"));

		Endereco e1 = new Endereco(null, "Rua da Favela", "6969", "ap 206", "capão redondo", "34088090", cli1, c2);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "65088060", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
