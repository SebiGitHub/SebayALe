package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class CtrAlumno {

    public conexionesBD conexion;

    public CtrAlumno(){
        conexion = new conexionesBD(); // Iniciamos la conexion
    }

    // Metodo para validar el usuario y contraseña
    public boolean validarUsuario(String usuario, String contrasena) {
        boolean valido = false; // Inicializamos como falso

        if (usuario.equals("root")){
            if(contrasena.equals("")){
                valido = true;
            }
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