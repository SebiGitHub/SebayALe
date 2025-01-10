package view;

import controlador.CtrAlumno;
import controlador.CtrResumen;
import model.Alumno;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class PanelResumen extends JPanel {

    private JLabel lblTitulo, lblNumero, lblUsuario, lblFechaNacimiento, lblNotaMedia, lblImagenAlumno;
    private JTable tablaAsignaturas;
    private JButton btnCalcular;
    private Alumno alumno;
    private CtrAlumno ctrAlumno;
    private CtrResumen ctrResumen;

    public PanelResumen() {
        ctrAlumno = new CtrAlumno();
        ctrResumen = new CtrResumen();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        lblTitulo = new JLabel("Resumen del Alumno", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // Panel de información del alumno
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(panelInfo, gbc);

        // Imagen del alumno
        lblImagenAlumno = new JLabel();
        lblImagenAlumno.setPreferredSize(new Dimension(150, 150));
        lblImagenAlumno.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImagenAlumno.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblImagenAlumno, gbc);

        // Tabla de asignaturas
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Asignatura", "Nota"}, 0);
        tablaAsignaturas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaAsignaturas);
        scrollTabla.setPreferredSize(new Dimension(400, 150));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollTabla, gbc);

        // Botón para calcular la nota media
        btnCalcular = new JButton("Calcular Nota Media");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCalcular, gbc);

        btnCalcular.addActionListener(e -> {
            if (alumno != null) {
                float notaMedia = ctrResumen.calcularNotaMedia(alumno.getNumero());
                lblNotaMedia.setText("Nota Media: " + String.format("%.2f", notaMedia));
            } else {
                JOptionPane.showMessageDialog(this, "No hay un alumno seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public void actualizarDatosAlumno(Alumno alumno) {
        this.alumno = alumno;

        // Actualizar datos del alumno en las etiquetas
        lblNumero.setText("Número: " + alumno.getNumero());
        lblUsuario.setText("Usuario: " + alumno.getUsuario());
        lblFechaNacimiento.setText("Fecha Nacimiento: " + alumno.getF_nac());
        lblNotaMedia.setText("Nota Media: " + alumno.getN_media());

        // Mostrar imagen del alumno
        ImageIcon imagen = new ImageIcon(convertirBytesAImagen(alumno.getImagen()));
        Image imagenRedimensionada = imagen.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblImagenAlumno.setIcon(new ImageIcon(imagenRedimensionada));

        // Cargar asignaturas en la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaAsignaturas.getModel();
        cargarAsignaturasEnTabla(modeloTabla);

        revalidate();
        repaint();
    }

    private void cargarAsignaturasEnTabla(DefaultTableModel modeloTabla) {
        if (alumno == null) {
            System.err.println("El alumno no está asignado, no se pueden cargar las asignaturas.");
            return;
        }

        modeloTabla.setRowCount(0); // Limpiar la tabla

        // Obtener asignaturas del controlador y llenar la tabla
        Map<String, Float> asignaturas = ctrAlumno.obtenerAsignaturasPorAlumno(alumno.getNumero());
        for (Map.Entry<String, Float> entry : asignaturas.entrySet()) {
            modeloTabla.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        tablaAsignaturas.setModel(modeloTabla);
    }


    private Image convertirBytesAImagen(byte[] bytes) {
        try (InputStream in = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
