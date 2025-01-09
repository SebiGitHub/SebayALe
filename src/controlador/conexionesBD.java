package controlador;
import java.sql.*;

public class conexionesBD {

    private Connection connection;

    public void abrirConexion(){

        try{
            if (connection == null || connection.isClosed()){
                String nombreBD = "bdPSP";
                String url = "jdbc:mysql://localhost:3306/"; // Cambia el nombre_base_datos
                String usuario = "root";
                String password = "";
                connection = DriverManager.getConnection(url + nombreBD, usuario, password);
                System.out.println("Conexión abierta exitosamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al abrir la conexión: " + e.getMessage());
        }

    }

    public void cerrarConexion(){

        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e){
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    // REVISAR ESTO
    public Statement getStatementResumen(){
        try{
            abrirConexion();
            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e){
            System.err.println("Error al obtener Statement para detalle: " + e.getMessage());
            return null;
        }
    }

    public Statement getStatementDetalle(){

        try{
            // Verificamos si la conexion esta abierta, si no lo está, la abrimos
            if (connection == null || connection.isClosed()){
                abrirConexion();
            }

            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            System.err.println("Error al crear el Statement para detalle: " + e.getMessage());
            return null; // Retornamos nulo en caso de que falle
        }
    }

}
