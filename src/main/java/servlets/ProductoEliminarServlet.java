package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.ProductoService;

/**
 * Servlet implementation class ProductoEliminarServlet
 */
@WebServlet("/ProductoEliminarServlet")
public class ProductoEliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ProductoEliminarServlet.class);
    
    private ProductoService productoService;
	
    public ProductoEliminarServlet() {
        this.productoService = new ProductoService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Get ProductoEliminarServlet");
			
		
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			productoService.eliminar(id);
			
			////Este es el flash despues de eliminar un registro
			
			request.getSession().setAttribute("success", "Registro eliminar satisfactoriamente");
			
			response.sendRedirect(request.getContextPath()+ "/ProductoListarServlet");
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
