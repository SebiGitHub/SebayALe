package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CtrResumen {

    private conexionesBD conexion;

    public CtrResumen(){
        conexion = new conexionesBD();
    }

    // Metodo para conseguir las asignaturas de un alumno
    public void getAsignaturasAlumno(int aluNumero){

        String sentencia = "SELECT asignatura, nota FROM Asignaturas WHERE aluNumero = ?";

        try{
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();

            if (conn == null){
                System.err.println("Conexión no establecida");
                return;
            }

            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, aluNumero);

            ResultSet rs = statement.executeQuery();

            System.out.println("Asignaturas del alumno con número: " + aluNumero + ": ");
            while(rs.next()){
                String asignatura = rs.getString("asignatura");
                float nota =  rs.getFloat("nota");
                System.out.println("- " + asignatura + ": " + nota);

            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener las asignaturas del alumno: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
    }

    public float calcularNotaMedia(int aluNumero) {
        String sentencia = "SELECT AVG(nota) AS promedio FROM Asignatura WHERE aluNumero = ?";
        float promedio = 0;

        try {
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();

            if (conn == null) {
                System.err.println("Conexión no establecida");
                return 0;
            }

            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, aluNumero);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                promedio = rs.getFloat("promedio");
            } else {
                System.out.println("No se encontraron asignaturas para el alumno con número " + aluNumero);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al calcular la nota media del alumno: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }

        return promedio;
    }

}
