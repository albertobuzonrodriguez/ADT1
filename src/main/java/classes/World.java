package classes;
import java.sql.*;

public class World {
	public static void mostrarTodosLosPaises() throws ClassNotFoundException {
		try {
			Connection con = GestorConexiones.getMySQL_Connection("world");
			Statement st = con.createStatement();
			String consulta = "SELECT NAME, POPULATION FROM COUNTRY";
			ResultSet rs = st.executeQuery(consulta);
			
			while (rs.next()) {
				String nombre = rs.getString("name");
				int population = rs.getInt("population");
				System.out.println("PAÍS: " + nombre);
				System.out.println("POBLACIÓN: " + population);
				
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
		}
	}
	
	public static void mostrarPaises(char c) throws ClassNotFoundException {
		try {
			Connection con = GestorConexiones.getMySQL_Connection("world");
			Statement st = con.createStatement();
			String consulta = "SELECT NAME FROM COUNTRY WHERE NAME LIKE '"+c+"%'";
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				String nombre = rs.getString("name");
				System.out.println("PAÍS: " + nombre);
				
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
	}
	
	public static void mostrarDatos() throws ClassNotFoundException {
		try {
			
			Connection con = GestorConexiones.getMySQL_Connection("world");
			Statement st = con.createStatement();
			String consulta = "SELECT * FROM COUNTRY LIMIT 1";
			ResultSet rs = st.executeQuery(consulta);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			for (int i = 1; i >= rsmd.getColumnCount(); i++) {
				System.out.println(i+". CAMPO: "+rsmd.getColumnName(i));
				System.out.println(i+". TIPO DE DATO: "+rsmd.getColumnTypeName(i));
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
	}
	
}

