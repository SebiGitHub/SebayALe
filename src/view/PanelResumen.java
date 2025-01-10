package view;

import controlador.CtrAlumno;
import model.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

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
        cargarAsignaturasEnTabla(modeloTabla);

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
        modeloTabla.setRowCount(0);  // Limpiar la tabla

        // Simulación de datos de asignaturas y notas del alumno
        List<String[]> asignaturas = List.of(
                new String[]{"Matemáticas", "8"},
                new String[]{"Historia", "7"},
                new String[]{"Ciencias", "9"}
        );

        for (String[] asignatura : asignaturas) {
            modeloTabla.addRow(asignatura);
        }
    }

    private void calcularNotaMedia() {
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
        lblNotaMedia.setText("Nota Media: " + "0");
        revalidate();
        repaint();
    }
}
