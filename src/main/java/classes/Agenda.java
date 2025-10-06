package classes;

import java.sql.*;

public class Agenda {

    public static void crearTablaAgenda(){
         try {
			Connection con = GestorConexiones.getMySQL_Connection("agenda");
			Statement st = con.createStatement();
			String consulta = "CREATE TABLE IF NOT EXISTS agenda (ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                            "nombre VARCHAR(255), telefono VARCHAR(255));";
			st.executeQuery(consulta);
            System.out.println("La tabla ha sido creada correctamente.");
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
		}
    }

    public static void insertarRegistroAgenda(String nombre, String telefono){
         try {
			Connection con = GestorConexiones.getMySQL_Connection("agenda");
			Statement st = con.createStatement();
			String query = "INSERT INTO agenda (nombre, telefono) VALUES ('" + nombre + "', '" + telefono + "')";
			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Contacto insertado correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
            
			st.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }
    public static void main(String[] args) throws Exception{
        //crearTablaAgenda();
        insertarRegistroAgenda("Pepe", "9876545");
        insertarRegistroAgenda("María", "9832645");
        insertarRegistroAgenda("Juan", "97832545");
    }
}
