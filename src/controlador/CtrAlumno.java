package controlador;

import model.Alumno;
import model.Asignatura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CtrAlumno {

    private conexionesBD conexion;


    public CtrAlumno() {
        conexion = new conexionesBD(); // Iniciamos la conexión
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

    // Metodo para obtener las asignaturas de un alumno
    public Map<String, Float> obtenerAsignaturasPorAlumno(int aluNumero) {
        Map<String, Float> asignaturas = new HashMap<>();
        String sentencia = "SELECT nombre, nota FROM Asignatura WHERE aluNumero = ?";


        try {
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();
            if (conn == null) {
                System.err.println("Conexión no establecida.");
            }

            // Preparar y ejecutar la consulta
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, aluNumero);
            ResultSet rs = statement.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                Float nota = rs.getFloat("nota");
                asignaturas.put(nombre, nota);
            }

            // Cerrar recursos
            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener asignaturas: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return asignaturas;
    }
}
