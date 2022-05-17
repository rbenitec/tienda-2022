package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.apache.log4j.Logger;

import entities.Categoria;
import util.ConexionDB;

public class CategoriaRepository {
	
	//Listar todo la informacion de categorias
	
	private static final Logger log = Logger.getLogger(CategoriaRepository.class);
	
	public List<Categoria> listar() throws Exception{
		log.info("call listar()");
		
		Connection con = ConexionDB.obtenerConexion(); //Debido a que el metodo obtenerConexion() es estatico no es necesario instancear la clase
		String query = "select*from categorias order by orden";
		
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		List<Categoria> lista =  new ArrayList();
		
		while(rs.next()) {
			Categoria cat = new Categoria();
			cat.setId(rs.getInt("id"));
			cat.setNombre(rs.getString("nombre"));
			cat.setOrden(rs.getInt("orden"));
			lista.add(cat);
		}
		
		stmt.close();
		con.close();
		rs.close();
		log.info("lista: "+ lista);
		return lista;
	}
	
	//Metodo pata Registrar un registro de categoria
/*	
	public void registrar(Categoria categoria) throws Exception{
		//Crear  mi conexion
		Connection con = ConexionDB.obtenerConexion();
		
		String query ="INSERT TO categorias (id,nombre,orden) values (?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, categoria.getId());
		stmt.setString(2, categoria.getNombre());
		stmt.setInt(3, categoria.getOrden());
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		log.info("Success");
		
	}
	*/
	
}
