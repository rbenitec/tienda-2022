package service;

import java.util.*;
import entities.Categoria;
import repositories.CategoriaRepository;
import repositories.ProductoRepository;

import org.apache.log4j.Logger;

public class CategoriaService {
	
	private static final Logger log = Logger.getLogger(CategoriaService.class);	
	private CategoriaRepository categoriaRepository;

	//Metodos

	public CategoriaService() {
		this.categoriaRepository = new CategoriaRepository();
	}
	
	public List<Categoria> listar() throws Exception{
		log.info("call listar()");
		return categoriaRepository.listar();
	}
	
	/*
	public void registrar(Categoria categoria) throws Exception{
		log.info("Call Registrar()");
		categoriaRepository.registrar(categoria);
	}
	*/
}
