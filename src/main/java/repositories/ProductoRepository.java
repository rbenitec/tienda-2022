package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entities.*;
import util.ConexionDB;

public class ProductoRepository {
	
	//Generamoslog
	
	public static final Logger log = Logger.getLogger(ProductoRepository.class);
	//Listar todos los datos de la tabla producto
	
	public List<Producto> listar() throws Exception{
		log.info("call listar()");
		//Creamos nuestra conexion con la ayuda de nuestro metodo den la clase ConexionDB
		Connection con = ConexionDB.obtenerConexion();
		String query = "SELECT p.id, p.categorias_id, c.nombre AS categorias_nombre, p.nombre, p.descripcion, p.precio, p.stock, p.imagen_nombre, p.imagen_tipo, p.imagen_tamanio, p.creado, p.estado\r\n" + 
				"FROM productos p\r\n" + "INNER JOIN categorias c ON c.id=p.categorias_id\r\n" + 
				"WHERE estado=1\r\n" + 
				"ORDER BY id";
		
		PreparedStatement stmt = con.prepareStatement(query);
		
		ResultSet rs = stmt.executeQuery();
		List<Producto> lista = new ArrayList();
		
		while(rs.next()) {
			Producto producto = new Producto();
			producto.setId(rs.getInt("id"));
			producto.setCategorias_id(rs.getInt("categorias_id"));
			
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("categorias_id"));
			categoria.setNombre(rs.getString("categorias_nombre"));
			
			producto.setCategoria(categoria);
			producto.setNombre(rs.getString("nombre"));
			producto.setDescripcion(rs.getString("descripcion"));
			producto.setPrecio(rs.getDouble("precio"));
			
			if(rs.wasNull()) producto.setPrecio(null);
			
			producto.setStock(rs.getInt("stock"));
			producto.setImagen_nombre(rs.getString("imagen_nombre"));
			producto.setImagen_tipo(rs.getString("imagen_tipo"));
			producto.setImagen_tamanio(rs.getLong("imagen_tamanio"));
			
			if(rs.wasNull()) producto.setImagen_tamanio(null);
			
			producto.setEstado(rs.getInt("estado"));
			lista.add(producto);
		
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		log.info("success: "+ lista);
		
		return lista;
	}
	
	//Metodo para registrar un producto
	
	public void registrar(Producto producto) throws Exception{
		log.info("call registrar(producto: "+producto +")");
		
		Connection con = ConexionDB.obtenerConexion();
		
		String query = "insert into productos(categorias_id, nombre, descripcion, precio, \r\n"
				+ "stock, imagen_nombre, imagen_tipo, imagen_tamanio)\r\n"+
				"values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt =  con.prepareStatement(query);
		stmt.setInt(1, producto.getCategorias_id());
		stmt.setString(2, producto.getNombre());
		stmt.setString(3, producto.getDescripcion());
		stmt.setDouble(4, producto.getPrecio());
		stmt.setInt(5, producto.getStock());
		stmt.setString(6, producto.getImagen_nombre());
		stmt.setString(7, producto.getImagen_tipo());
		stmt.setObject(8, producto.getImagen_tamanio());
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		log.info("Success");
		
	}
	
	//Metodo para Buscar o obtener un producto por ID
	
	public Producto obtener(Integer id) throws Exception {
		
		log.info("call obtener (id: "+id+")");
		
		Connection con = ConexionDB.obtenerConexion();
		
		String query = "SELECT p.id, p.categorias_id, c.nombre AS categorias_nombre,  p.nombre, p.descripcion, p.precio, p.stock, p.imagen_nombre, p.imagen_tipo, p.imagen_tamanio,  p.creado, p.estado\r\n" + 
				"FROM productos p\r\n" + 
				"INNER JOIN categorias c ON c.id=p.categorias_id\r\n" + 
				"WHERE estado=1\r\n" + 
				"AND p.id=?";
		
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		Producto producto = null;
		if(rs.next()) {
			producto = new Producto();
			
			producto.setId(rs.getInt("id"));
			producto.setCategorias_id(rs.getInt("categorias_id"));
			
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("categorias_id"));
			categoria.setNombre(rs.getString("categorias_nombre"));
			producto.setCategoria(categoria);
			producto.setNombre(rs.getString("nombre"));
			producto.setDescripcion(rs.getString("descripcion"));
			producto.setPrecio(rs.getDouble("precio"));
			if(rs.wasNull()) producto.setPrecio(null);
			producto.setStock(rs.getInt("stock"));
			producto.setImagen_nombre(rs.getString("imagen_nombre"));
			producto.setImagen_tipo(rs.getString("imagen_tipo"));
			producto.setImagen_tamanio(rs.getLong("imagen_tamanio"));
			if(rs.wasNull()) producto.setImagen_tamanio(null);
			producto.setEstado(rs.getInt("estado"));
		}
		rs.close();
		stmt.close();
		con.close();
		
		log.info("success!" +producto);
		return producto;
	}
	
	//Metodo para Eliminar registros
	public void eliminar(Integer id)throws Exception{
		log.info("call Eliminar (id: "+id+")");
		
		Connection con = ConexionDB.obtenerConexion();
		
		String query="delete from productos where id=?";
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setInt(1, id);
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		log.info("success");
		
	}
	
}
