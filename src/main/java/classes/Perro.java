package classes;

import java.sql.*;

public class Perro {
    public static void crearTablaPerros(){
        try {
			Connection con = GestorConexiones.getMySQL_Connection("perros");
			Statement st = con.createStatement();
			String consulta = "create table if not exists perro (\r\n" + //
                                "\tid int not null primary key,\r\n" + //
                                "\traza varchar (20) not null default '', \r\n" + //
                                "\ttamanyo int,\r\n" + //
                                "\tedad int,\r\n" + //
                                "\tcolor varchar (20)\r\n" + //
                                "\r\n" + //
                                ");";
			st.executeQuery(consulta);
            System.out.println("La tabla ha sido creada correctamente.");
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
		}
    }

    public static void insertarPerro(int id, String raza, int tamanyo, int edad, String color) throws ClassNotFoundException{
        try {
			Connection con = GestorConexiones.getMySQL_Connection("perros");
			Statement st = con.createStatement();
			String query = "INSERT INTO perro VALUES ('" + id + "', '" + raza + "', " + tamanyo + ", " + edad + ", '" + color + "')";

			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Perro insertado correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			con.close();
			st.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }

    public static void insertarPerroRS(String raza, int tamanyo, int edad, String color){
        
    }
}
