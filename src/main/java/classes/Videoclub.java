package classes;

import java.sql.*;

public class Videoclub {
    public static void crearTablaVideoclub() {
        try {
			Connection con = GestorConexiones.getMySQL_Connection("videoclub");
			Statement st = con.createStatement();
			String consulta = "CREATE TABLE IF NOT EXISTS peliculas (ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                                "TITULO VARCHAR(255), GENERO VARCHAR(255), ANIO INTEGER, PRECIO FLOAT, PRECIOALQUILER FLOAT);";
			st.executeQuery(consulta);
            System.out.println("La tabla ha sido creada correctamente.");
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
		}
    } 

    public static void insertarPelicula(String peli, String genero, int anyo, float precio, float precioAlquiler){
        try {
			Connection con = GestorConexiones.getMySQL_Connection("videoclub");
			Statement st = con.createStatement();
			String query = "INSERT INTO peliculas (titulo, genero, anio, precio, precioalquiler) + VALUES ('" + peli + "', '" + genero + "', " + anyo + ", " + precio + ", '" + precioAlquiler + "')";

			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Película insertada correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			st.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }

    public static void insertarPeliculaPS(String peli, String genero, int anyo, float precio, float precioAlquiler) throws SQLException{
        
         try {
			Connection con = GestorConexiones.getMySQL_Connection("videoclub");
            String query = "INSERT INTO peliculas (TITULO, GENERO, ANIO, PRECIO, PRECIOALQUILER) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, peli);
            ps.setString(2, genero);
			ps.setInt(3, anyo);
            ps.setFloat(4, precio);
            ps.setFloat(5, precioAlquiler);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				System.out.println("Película insertada correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			ps.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }
    public static void main (String args []) throws SQLException{
        //crearTablaVideoclub();
        //insertarPelicula("Star Wars", "SciFi", 1968, 50, 5);
        insertarPeliculaPS("Toy Story", "Animación", 1999, 3, 1);
    }
}
