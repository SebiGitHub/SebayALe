package view;

import controlador.CtrAlumno;
import model.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PanelResumen extends JPanel {

    private JLabel lblNumero, lblUsuario, lblFechaNacimiento, lblNotaMedia, lblImagen;
    private JTable tablaAsignaturas;
    private JButton btnCalcular, btnCambiarFecha;
    private JSpinner datePicker;
    private Alumno alumno;
    private CtrAlumno ctrAlumno;

    public PanelResumen(Alumno alumno) {
        this.alumno = alumno;
        ctrAlumno = new CtrAlumno();

        setLayout(new BorderLayout(20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Resumen del Alumno", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        // Información del alumno
        JPanel panelInfo = new JPanel(new GridLayout(5, 1, 15, 15));

        lblNumero = new JLabel("Número: " + alumno.getNumero(), JLabel.CENTER);
        lblUsuario = new JLabel("Usuario: " + alumno.getUsuario(), JLabel.CENTER);
        lblFechaNacimiento = new JLabel("Fecha Nacimiento: " + alumno.getF_nac(), JLabel.CENTER);
        lblNotaMedia = new JLabel("Nota Media: " + alumno.getN_media(), JLabel.CENTER);

        lblImagen = new JLabel(new ImageIcon(getClass().getResource("/path/to/image/" + alumno.getImagen() + ".png")));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        panelInfo.add(lblNumero);
        panelInfo.add(lblUsuario);
        panelInfo.add(lblFechaNacimiento);
        panelInfo.add(lblNotaMedia);

        panelCentral.add(panelInfo, BorderLayout.CENTER);
        panelCentral.add(lblImagen, BorderLayout.WEST);

        // Tabla de asignaturas
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Asignatura", "Nota"}, 0);
        tablaAsignaturas = new JTable(modeloTabla);
        cargarAsignaturasEnTabla(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaAsignaturas);
        panelCentral.add(scrollTabla, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));

        // DatePicker para la fecha
        JPanel panelFecha = new JPanel(new FlowLayout());
        datePicker = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(datePicker, "dd/MM/yyyy");
        datePicker.setEditor(editor);
        btnCambiarFecha = new JButton("Cambiar Fecha");
        btnCambiarFecha.addActionListener(e -> actualizarFecha());

        panelFecha.add(new JLabel("Nueva Fecha de Nacimiento: "));
        panelFecha.add(datePicker);
        panelFecha.add(btnCambiarFecha);

        panelInferior.add(panelFecha, BorderLayout.NORTH);

        // Botón para calcular la nota media
        btnCalcular = new JButton("Calcular Nota Media");
        btnCalcular.addActionListener(e -> calcularNotaMedia());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCalcular);
        panelInferior.add(panelBoton, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarAsignaturasEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0); // Limpiar la tabla

        // Aquí puedes reemplazar por una consulta al controlador para obtener asignaturas
        List<String[]> asignaturas = List.of(
                new String[]{"Matemáticas", "8"},
                new String[]{"Historia", "7"},
                new String[]{"Ciencias", "9"}
        );

        for (String[] asignatura : asignaturas) {
            modeloTabla.addRow(asignatura);
        }
    }

    private void actualizarFecha() {
        Date nuevaFecha = (Date) datePicker.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(nuevaFecha);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(nuevaFecha);

        ctrAlumno.actualizarFechaNacimiento(alumno.getNumero(), calendar);

        lblFechaNacimiento.setText("Fecha Nacimiento: " + fechaFormateada);
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
}
