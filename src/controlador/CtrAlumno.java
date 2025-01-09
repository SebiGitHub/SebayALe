package controlador;

import model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class CtrAlumno {

    private conexionesBD conexion;

    public CtrAlumno(){
        conexion = new conexionesBD(); // Iniciamos la conexion
    }

    // Metodo para validar el usuario y contraseña
    public Alumno validarUsuario(String usuario, String contrasena) {
        Alumno alumno = null;
        String query = "SELECT * FROM Alumno WHERE usuario = ? AND contrasena = ?";

        try {
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crear objeto Alumno con los datos de la base
                alumno = new Alumno(
                        rs.getInt("numero"),
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("f_nac"),
                        rs.getInt("imagen"),
                        rs.getInt("nota_media")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return alumno;
    }


    public void actualizarFechaNacimiento(int numero, GregorianCalendar fechaNueva){

        String sentencia = "UPDATE Alumno SET fechaNacimiento = ? WHERE numero = ?";

        try{
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();
            PreparedStatement statement = conn.prepareStatement(sentencia);

            // Establecer la fecha directamente desde GregorianCalendar
            statement.setDate(1, new java.sql.Date(fechaNueva.getTimeInMillis()), fechaNueva);
            statement.setInt(2, numero);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Fecha de nacimiento actualizada correctamente.");
            } else{
                System.out.println("No se ha podido encontrar al alumno con el numero proporcionado");
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al actualizar la fecha de nacimiento: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
    }

    public Alumno obtenerAlumnoPorUsuario(String usuario) {
        Alumno alumno = null;
        String sentencia = "SELECT * FROM Alumno WHERE usuario = ?";

        try {
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sentencia);
            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                alumno = new Alumno(
                        rs.getInt("numero"),
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("f_nac"),
                        rs.getInt("imagen"),
                        rs.getInt("n_media")
                );
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener el alumno: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return alumno;
    }


}
