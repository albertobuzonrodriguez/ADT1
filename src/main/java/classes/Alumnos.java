package classes;

import java.sql.*;
import java.util.Scanner;

public class Alumnos {
    public static void crearTablaAlumnos(){
         try {
			Connection con = GestorConexiones.getMySQL_Connection("alumnos");
			Statement st = con.createStatement();
			String consulta = "CREATE TABLE IF NOT EXISTS alumnos (idAlumno INT PRIMARY KEY \r\n" + //
                                "AUTO_INCREMENT, nombreAlumno VARCHAR(255), nota DOUBLE, asignatura \r\n" + //
                                "VARCHAR(255))";
			st.executeQuery(consulta);
            System.out.println("La tabla ha sido creada correctamente.");
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
		}
    }

    public static void insertarAlumnos(String nombreAlumno, double nota, String asignatura) throws SQLException {
        try {
			Connection con = GestorConexiones.getMySQL_Connection("alumnos");
			Statement st = con.createStatement();
			String query = "INSERT INTO alumnos (nombreAlumno, nota, asignatura) VALUES ('" + nombreAlumno + "', '" + nota + "', '" + asignatura + "')";

			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Alumno insertado correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			st.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }

    public static void actualizarNotaAsignatura(){
        try {
			Connection con = GestorConexiones.getMySQL_Connection("alumnos");
            Scanner sc = new Scanner (System.in);
            boolean actualizar = true;
            PreparedStatement ps = null;
            int respuesta;
            do {
                System.out.println("Elige una asignatura para actualizar su nota: ");
                String asignaturaUpdate = sc.nextLine();
                System.out.println("Dime también una nota para actualizar la asignatura: ");
                int notaUpdate = sc.nextInt();
            
                String query = "UPDATE alumnos SET nota = ? WHERE asignatura = ?";
                ps = con.prepareStatement(query);

                ps.setInt(1, notaUpdate);
                ps.setString(2, asignaturaUpdate);


                int filas = ps.executeUpdate();

                if (filas > 0) {
                    System.out.println("Alumno actualizado correctamente.");
                } else {
                    System.out.println("No se insertó ningún registro.");
                }

                System.out.println("Se han actualizado: "+ filas+ " filas.");

                System.out.println("Pulsa 1 si quieres seguir actualizando. Pulsa 0 si quieres salir.");
                respuesta = sc.nextInt();
                sc.nextLine();
                if (respuesta == 0) {
                    actualizar = false;
                    System.out.println("Hasta luego tito mio");
                }
            } while (actualizar);
           

            sc.close();
			ps.close();
			con.close();
			

		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());

		}
    }
    public static void main(String[] args) throws SQLException {
        //crearTablaAlumnos();
        //insertarAlumnos("Antoñito González", 6, "Lengua");
        //actualizarNotaAsignatura();
    }
}
