package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	public static Connection obtenerConexion() throws SQLException{
		Connection con = null;
		
		try {
			//Cargamos el driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Generamos la conexion
			con = DriverManager.getConnection("jdbc:mysql://localhost/tienda?useSSL=false", "root", "");	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}

}
