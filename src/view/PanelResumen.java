package view;

import controlador.CtrAlumno;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelResumen extends JPanel {
    private JLabel lblNotaMedia, lblNumeroAlumno, lblNombreAlumno, lblFecha, lblImagen;
    private JTable tablaAsignaturas;
    private JButton btnCalcular, btnCambiarFecha;
    private JSpinner datePicker;

    public PanelResumen() {
        setLayout(new BorderLayout(20, 20)); // Espaciado general

        // Título
        JLabel lblTitulo = new JLabel("Resumen de Alumno", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        // Información del alumno
        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 15, 15));
        lblNumeroAlumno = new JLabel("Numero: 1", JLabel.CENTER);
        lblNombreAlumno = new JLabel("Nombre: Alumno1", JLabel.CENTER);
        lblNotaMedia = new JLabel("Nota Media: 0.0", JLabel.CENTER);
        lblFecha = new JLabel("Fecha: " + getCurrentDate(), JLabel.CENTER); // Fecha inicial

        lblNumeroAlumno.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombreAlumno.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNotaMedia.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 16));

        panelInfo.add(lblNumeroAlumno);
        panelInfo.add(lblNombreAlumno);
        panelInfo.add(lblNotaMedia);
        panelInfo.add(lblFecha);

        panelCentral.add(panelInfo, BorderLayout.NORTH);

        // Imagen del alumno
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        //lblImagen.setIcon(new ImageIcon(getClass().getResource("//ico.png"))); // Cambia el path a tu imagen
        panelCentral.add(lblImagen, BorderLayout.WEST);

        // Tabla de asignaturas
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Asignatura", "Nota"}, 0);
        tablaAsignaturas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaAsignaturas);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para botones y DatePicker
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));

        // DatePicker
        JPanel panelFecha = new JPanel(new FlowLayout());
        datePicker = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(datePicker, "dd/MM/yyyy");
        datePicker.setEditor(editor);
        btnCambiarFecha = new JButton("Cambiar Fecha");

        CtrAlumno ctrAlumno = new CtrAlumno();

        //Numero es lo que aparece a la derecha de nuevo numero
        //Date nuevaFecha = (Date) datePicker.getValue();
        btnCambiarFecha.addActionListener(e -> actualizarFecha());

        panelFecha.add(new JLabel("Fecha:"));
        panelFecha.add(datePicker);
        panelFecha.add(btnCambiarFecha);

        panelInferior.add(panelFecha, BorderLayout.NORTH);

        // Botón calcular
        btnCalcular = new JButton("Calcular");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCalcular);

        panelInferior.add(panelBoton, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    // Método para obtener la fecha actual
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

    // Método para actualizar la fecha
    private void actualizarFecha() {
        Date nuevaFecha = (Date) datePicker.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        lblFecha.setText("Fecha: " + sdf.format(nuevaFecha));
    }
}
