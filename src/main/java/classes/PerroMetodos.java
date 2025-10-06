package classes;

import java.sql.*;

public class PerroMetodos {

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
			st.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }

    public static void insertarPerroRS(String raza, int tamanyo, int edad, String color){
        try {
			Connection con = GestorConexiones.getMySQL_Connection("perros");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);			
			String query = "select * from perro";
			ResultSet rs = st.executeQuery(query);
			rs.last();
			int id;
			id = rs.getInt("id") + 1;
			rs.moveToInsertRow();
			rs.updateInt("id", id);
			rs.updateString("raza", raza);
			rs.updateInt("tamanyo", tamanyo);
			rs.updateInt("edad", edad);
			rs.updateString("color", color);
			rs.insertRow();
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }

	public static void insertarPerroObjeto(Perro perro){
		try {
			Connection con = GestorConexiones.getMySQL_Connection("perros");
			Statement st = con.createStatement();
			String query = "INSERT INTO perro VALUES ('" + perro.id + "', '" + perro.raza + "', " + perro.tamanyo + ", " 
				+ perro.edad + ", '" + perro.color + "')";
			
			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Perro insertado correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
	}

	public static Perro getPerroDB(int id){
		
		Perro perro = new Perro(id, null, 0, 0, null);
		
		try {
			Connection con = GestorConexiones.getMySQL_Connection("perros");
			Statement st = con.createStatement();
			String query = "select * from perro where id = "+ id;
			ResultSet rs = st.executeQuery(query);
			if (rs.next()){
				perro.setRaza(rs.getString("raza"));
				perro.setTamanyo(rs.getInt("tamanyo"));
				perro.setEdad(rs.getInt("edad"));
				perro.setColor(rs.getString("color"));
			} else {
				System.out.println("No hay ningún perro en la base de datos con id: "+id);
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
			
		}
		return perro;

	}
}
