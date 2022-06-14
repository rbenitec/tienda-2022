package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entities.Categoria;
import service.CategoriaService;
import service.ProductoService;

/**
 * Servlet implementation class CategoriaListarServlet
 */
@WebServlet("/CategoriaListarServlet")
public class CategoriaListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ProductoListarServlet.class);
	private CategoriaService categoriaService;
			
       
    public CategoriaListarServlet() {
        this.categoriaService = new CategoriaService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("Get ProductoServiceServlet");
		log.info("Get ff");
		
		try {
			List<Categoria> categorias = categoriaService.listar();
			
			request.setAttribute("categorias", categorias);
			request.getRequestDispatcher("/WEB-INF/jsp/producto/listar.jsp").forward(request, response);
		}catch (Exception e) {
			log.error(e,e);
			throw new ServletException(e.getMessage(), e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
