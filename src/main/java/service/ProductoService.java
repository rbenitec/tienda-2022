package service;

import java.util.List;
import org.apache.log4j.Logger;
import entities.Producto;
import repositories.ProductoRepository;

public class ProductoService {
	
	private static final Logger log = Logger.getLogger(ProductoService.class);
	private ProductoRepository productoRepository;

	public ProductoService() {
		this.productoRepository = new ProductoRepository();
	}

	public List<Producto> listar() throws Exception {
		log.info("call listar()");
		return productoRepository.listar();
	}

	public void registrar(Producto producto) throws Exception {
		log.info("call registrar(producto: " + producto + ")");
		productoRepository.registrar(producto);
	}

	public Producto obtener(Integer id) throws Exception {
		log.info("call obtener(id: " + id + ")");
		return productoRepository.obtener(id);
	}

	public void eliminar(Integer id) throws Exception {
		log.info("call eliminar(id: " + id + ")");
		productoRepository.eliminar(id);
	}
}
