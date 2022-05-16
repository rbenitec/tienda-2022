package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import entities.Producto;
import service.ProductoService;

/**
 * Servlet implementation class ProductoListarServlet
 */
@WebServlet("/ProductoListarServlet")
public class ProductoListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ProductoListarServlet.class);
	private ProductoService productoService;
       
    public ProductoListarServlet() {
    	this.productoService = new ProductoService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("Get ProductoListarServlet");
		try {
			List<Producto> productos = productoService.listar();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher("/WEB-INF/jsp/producto/listar.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
