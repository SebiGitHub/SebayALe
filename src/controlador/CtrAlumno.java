package controlador;

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
    public boolean validarUsuario(String usuario, String contrasena){

        boolean valido = false;
        String sentencia = "SELECT * FROM Alumno WHERE usuario = ? AND contrasena = ?";

        try{
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sentencia);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            valido = rs.next(); // Si hay un resultado, el usuario es válido

            if (valido){
                System.out.println("Usuario validad con éxito");
            } else{
                System.out.println("Usuario o contraseña incorrectos");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error al validar al usuario: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return valido;
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



}
