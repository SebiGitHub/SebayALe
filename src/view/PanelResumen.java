package view;

import controlador.CtrAlumno;
import model.Alumno;
import model.Asignatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PanelResumen extends JPanel {

    private JLabel lblTitulo, lblNumero, lblUsuario, lblFechaNacimiento, lblNotaMedia;
    private JTable tablaAsignaturas;
    private JButton btnCalcular;
    private Alumno alumno;
    private CtrAlumno ctrAlumno;

    public PanelResumen() {
        ctrAlumno = new CtrAlumno();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Título
        lblTitulo = new JLabel("Resumen del Alumno", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);
        add(Box.createRigidArea(new Dimension(0, 20)));  // Espacio entre título y panel de información

        // Información del alumno
        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblNumero = new JLabel("Número: ");
        lblUsuario = new JLabel("Usuario: ");
        lblFechaNacimiento = new JLabel("Fecha Nacimiento: ");
        lblNotaMedia = new JLabel("Nota Media: ");

        panelInfo.add(lblNumero);
        panelInfo.add(lblUsuario);
        panelInfo.add(lblFechaNacimiento);
        panelInfo.add(lblNotaMedia);

        add(panelInfo);
        add(Box.createRigidArea(new Dimension(0, 20)));  // Espacio entre panel de información y tabla

        // Tabla de asignaturas
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Asignatura", "Nota"}, 0);
        tablaAsignaturas = new JTable(modeloTabla);

        // Cargar las asignaturas sólo después de tener el alumno actualizado
        if (alumno != null) {
            cargarAsignaturasEnTabla(modeloTabla);
        }

        JScrollPane scrollTabla = new JScrollPane(tablaAsignaturas);
        scrollTabla.setPreferredSize(new Dimension(400, 150));
        add(scrollTabla);
        add(Box.createRigidArea(new Dimension(0, 20)));  // Espacio entre tabla y botón

        // Botón para calcular la nota media
        btnCalcular = new JButton("Calcular Nota Media");
        btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCalcular.addActionListener(e -> calcularNotaMedia());
        add(btnCalcular);
    }

    private void cargarAsignaturasEnTabla(DefaultTableModel modeloTabla) {
        if (alumno == null) {
            System.err.println("El alumno no está asignado, no se pueden cargar las asignaturas.");
            return; // Salir si alumno es null
        }

        modeloTabla.setRowCount(0);  // Limpiar la tabla

        // Obtener las asignaturas y sus notas
        Map<String, Float> asignaturas = ctrAlumno.obtenerAsignaturasPorAlumno(alumno.getNumero());

        // Recorrer el mapa y añadir los datos al modelo de la tabla
        for (Map.Entry<String, Float> entry : asignaturas.entrySet()) {
            String asignatura = entry.getKey();  // Nombre de la asignatura
            Float nota = entry.getValue();       // Nota de la asignatura

            // Añadir una nueva fila a la tabla con el nombre de la asignatura y la nota
            modeloTabla.addRow(new Object[]{asignatura, nota});
        }

        // Asignar el modelo actualizado a la tabla
        tablaAsignaturas.setModel(modeloTabla);
    }

    private void calcularNotaMedia() {
        if (alumno == null) {
            System.err.println("El alumno no está asignado, no se puede calcular la nota media.");
            return; // Salir si alumno es null
        }

        double sumaNotas = 0;
        int totalAsignaturas = tablaAsignaturas.getRowCount();

        for (int i = 0; i < totalAsignaturas; i++) {
            sumaNotas += Double.parseDouble(tablaAsignaturas.getValueAt(i, 1).toString());
        }

        double nuevaNotaMedia = sumaNotas / totalAsignaturas;
        lblNotaMedia.setText("Nota Media: " + nuevaNotaMedia);

        if ((int) nuevaNotaMedia != alumno.getN_media()) {
            JOptionPane.showMessageDialog(this, "Nota media diferente. Actualizando...");
            alumno.setN_media((int) nuevaNotaMedia);
        }
    }

    public void actualizarDatosAlumno(Alumno alumno) {
        this.alumno = alumno;
        lblNumero.setText("Número: " + alumno.getNumero());
        lblUsuario.setText("Usuario: " + alumno.getUsuario());
        lblFechaNacimiento.setText("Fecha Nacimiento: " + alumno.getF_nac());
        lblNotaMedia.setText("Nota Media: " + alumno.getN_media());

        // Cargar las asignaturas después de actualizar los datos
        cargarAsignaturasEnTabla((DefaultTableModel) tablaAsignaturas.getModel());

        revalidate();
        repaint();
    }
}
