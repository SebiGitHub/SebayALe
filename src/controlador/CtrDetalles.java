package controlador;

import java.sql.*;

public class CtrDetalles {

    private conexionesBD conexion;
    private ResultSet resultSet;

    public CtrDetalles(){
        conexion = new conexionesBD();
    }

    public void cargarAsignaturas(){

        String sentencia = "SELECT * FROM Asignatura";

        try{
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();

            if (conn == null){
                System.err.println("Conexión no establecida.");
                return;
            }

            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultSet = statement.executeQuery(sentencia);

            System.out.println("Se han cargado las asignaturas correctamente");

        } catch (SQLException e) {
            System.err.println("Error al cargar las asignaturas: " + e.getMessage());
        }
    }

    public void siguienteAsignatura(){

        try{
            if (resultSet == null){
                cargarAsignaturas();
            }

            if (resultSet.next()){
                System.out.println("Asignatura actual: " + resultSet.getString("nombre"));
            }else {
                System.out.println("No hay mas asignaturas");
                resultSet.last();
            }

        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la siguiente persona: " + e.getMessage());
        }
    }

    public void anteriorAsignatura(){
        try{
            if (resultSet == null){
                cargarAsignaturas();
            }

            if (resultSet.previous()){
                System.out.println("Asignatura actual: " + resultSet.getString("nombre"));
            } else {
                System.out.println("No hay mas asignaturas anteriores");
            }
        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la persona anterior: " + e.getMessage());
        }
    }

    public void irPrimero(){
        try{
            if (resultSet == null){
                cargarAsignaturas();
            }

            if (resultSet.first()){
                System.out.println("Primera asignatura: " + resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la primera asignatura " + e.getMessage());
        }
    }

    public void irUltimo(){
        try {
            if (resultSet == null){
                cargarAsignaturas();
            }

            if (resultSet.last()){
                System.out.println("Última asignatura: " + resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la última asignatura " + e.getMessage());
        }
    }

    public void guardarNota(int codigo, float nuevaNota){

        String sentencia = "UPDATE Asignatura SET nota = ? WHERE codigo = ?";

        try{
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();

            if (conn == null){
                System.err.println("No se ha podido establecer la conexión");
                return;
            }

            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setFloat(1, nuevaNota);
            statement.setInt(2, codigo);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Nota actualizada correctamente para la asignatura con el codigo: " + codigo);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("Error, no se ha podido guardar la nota " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

    }
}
