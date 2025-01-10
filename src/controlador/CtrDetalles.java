package controlador;

import java.sql.*;

public class CtrDetalles {

    private conexionesBD conexion;
    private ResultSet resultSet;

    public CtrDetalles() {
        conexion = new conexionesBD();
    }

    public void cargarAsignaturas() {
        String sentencia = "SELECT * FROM Asignatura";

        try {
            conexion.abrirConexion();
            Connection conn = conexion.getConnection();

            if (conn == null) {
                System.err.println("Conexión no establecida.");
                return;
            }

            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sentencia);

            // Nos posicionamos en la primera asignatura
            if (resultSet.next()) {
                System.out.println("Se han cargado las asignaturas correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar las asignaturas: " + e.getMessage());
        }
    }

    public void siguienteAsignatura() {
        try {
            if (resultSet == null) {
                cargarAsignaturas(); // Esto asegura que se cargan las asignaturas si no se ha hecho antes
            }

            // Solo navegar al siguiente si el ResultSet está abierto
            if (resultSet != null && resultSet.next()) {
                System.out.println("Asignatura actual: " + resultSet.getString("nombre"));
            } else {
                // Si ya no hay más asignaturas, movernos al último registro
                System.out.println("No hay más asignaturas, se mueve al último registro.");
                resultSet.last(); // Asegurarse de estar en el último elemento
            }
        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la siguiente asignatura: " + e.getMessage());
        }
    }

    public void anteriorAsignatura() {
        try {
            if (resultSet == null) {
                cargarAsignaturas(); // Esto asegura que se cargan las asignaturas si no se ha hecho antes
            }

            // Solo navegar al anterior si el ResultSet está abierto
            if (resultSet != null && resultSet.previous()) {
                System.out.println("Asignatura actual: " + resultSet.getString("nombre"));
            } else {
                System.out.println("No hay más asignaturas anteriores.");
            }
        } catch (SQLException e) {
            System.err.println("Error, no se puede mover a la asignatura anterior: " + e.getMessage());
        }
    }


    public String getAsignaturaNombre() throws SQLException {
        if (resultSet != null) {
            return resultSet.getString("nombre");
        }
        return "";
    }

    public float getNotaActual() throws SQLException {
        if (resultSet != null) {
            return resultSet.getFloat("nota");
        }
        return 0;
    }

    public int getCodigoAsignatura() throws SQLException {
        if (resultSet != null) {
            return resultSet.getInt("codigo");
        }
        return 0;
    }

    public void guardarNota(String nota, int idAsignatura) {
        try {
            // Guardar la nota en la base de datos
            String query = "UPDATE Asignatura SET nota = ? WHERE codigo = ?";
            try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
                stmt.setString(1, nota);
                stmt.setInt(2, idAsignatura);
                int filasAfectadas = stmt.executeUpdate();  // Realizamos la actualización

                if (filasAfectadas > 0) {
                    System.out.println("Nota guardada correctamente.");
                } else {
                    System.out.println("No se encontró la asignatura.");
                }

                // Después de la actualización, recargamos el ResultSet con los datos actualizados
                recargarDatos();

            } catch (SQLException e) {
                System.err.println("Error al guardar la nota: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error al guardar la nota: " + e.getMessage());
        }
    }

    public void recargarDatos() {
        try {
            // Consultamos de nuevo la base de datos para recargar los datos actualizados
            String sentencia = "SELECT * FROM Asignatura";
            Statement stmt = conexion.getStatementResumen();  // Usamos el Statement desde la clase de conexión

            if (stmt != null) {
                resultSet = stmt.executeQuery(sentencia);  // Recargamos el ResultSet
                System.out.println("Datos recargados correctamente.");
            } else {
                System.out.println("Error al obtener el Statement.");
            }

        } catch (SQLException e) {
            System.err.println("Error al recargar los datos: " + e.getMessage());
        }
    }

    // Métodos para verificar si hay asignaturas anteriores o siguientes
    public boolean tieneAsignaturaAnterior() {
        try {
            return resultSet != null && !resultSet.isFirst();
        } catch (SQLException e) {
            System.err.println("Error al verificar asignatura anterior: " + e.getMessage());
            return false;
        }
    }

    public boolean tieneAsignaturaSiguiente() {
        try {
            return resultSet != null && !resultSet.isLast();
        } catch (SQLException e) {
            System.err.println("Error al verificar asignatura siguiente: " + e.getMessage());
            return false;
        }
    }
}
