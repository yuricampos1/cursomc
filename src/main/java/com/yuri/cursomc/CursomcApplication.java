package com.yuri.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.cursomc.domain.Categoria;
import com.yuri.cursomc.domain.Produto;
import com.yuri.cursomc.repositories.CategoriaRepository;
import com.yuri.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//declaração categorias
		Categoria cat1 = new Categoria (null, "Informática");
		Categoria cat2 = new Categoria (null, "Escritório");
		
		//declaração produtos
		 Produto p1 = new Produto (null, "Computador", 2000.0);
		 Produto p2 = new Produto (null, "Impressora", 800.00);
		 Produto p3 = new Produto (null, "Mouse", 80.00);
		 
		 //Associação de categorias com produtos
		 cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		 cat2.getProdutos().addAll(Arrays.asList(p2));
		 
		 //Associação de produtos com categorias
		 p1.getCategorias().addAll(Arrays.asList(cat1));
		 p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		 p3.getCategorias().addAll(Arrays.asList(cat1));
	
		 //Salvar
		 categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		 produtoRepository.saveAll(Arrays.asList(p1,p2,p3)); 
	
	}

}
