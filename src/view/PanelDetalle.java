package view;

import controlador.CtrDetalles;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PanelDetalle extends JPanel {
    private JLabel lblAsignatura;
    private JTextField txtNota;
    private JButton btnGuardar, btnAnterior, btnSiguiente;
    private CtrDetalles ctrDetalles;
    private int codigoAsignaturaActual;

    public PanelDetalle() {
        setLayout(new BorderLayout(20, 20)); // Espaciado general

        ctrDetalles = new CtrDetalles(); // Inicializamos el controlador
        ctrDetalles.cargarAsignaturas(); // Cargamos las asignaturas al iniciar el panel

        // Título
        JLabel lblTitulo = new JLabel("Detalle de Asignaturas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central para los datos
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Campo de Asignatura
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(new JLabel("Asignatura:"), gbc);

        gbc.gridx = 1;
        lblAsignatura = new JLabel("Nombre de la asignatura");
        panelCentral.add(lblAsignatura, gbc);

        // Campo de Nota
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentral.add(new JLabel("Nota:"), gbc);

        gbc.gridx = 1;
        txtNota = new JTextField(10);
        panelCentral.add(txtNota, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnGuardar = new JButton("Guardar");

        panelBotones.add(btnAnterior);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnSiguiente);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos para los botones
        btnAnterior.addActionListener(e -> cargarAsignaturaAnterior());
        btnSiguiente.addActionListener(e -> cargarAsignaturaSiguiente());
        btnGuardar.addActionListener(e -> guardarNota());

        // Actualizamos la vista al cargar las asignaturas
        actualizarVista();
    }

    private void cargarAsignaturaAnterior() {
        ctrDetalles.anteriorAsignatura();
        actualizarVista();
    }

    private void cargarAsignaturaSiguiente() {
        ctrDetalles.siguienteAsignatura();
        actualizarVista();
    }

    private void actualizarVista() {
        try {
            // Verificamos si hay asignaturas disponibles
            if (ctrDetalles.getCodigoAsignatura() != 0) {
                String asignatura = ctrDetalles.getAsignaturaNombre();
                float nota = ctrDetalles.getNotaActual();
                codigoAsignaturaActual = ctrDetalles.getCodigoAsignatura();

                lblAsignatura.setText(asignatura);
                txtNota.setText(String.valueOf(nota));

                // Deshabilitar botones en los límites
                btnAnterior.setEnabled(ctrDetalles.tieneAsignaturaAnterior());
                btnSiguiente.setEnabled(ctrDetalles.tieneAsignaturaSiguiente());
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar la vista: " + e.getMessage());
        }
    }

    private void guardarNota() {
        try {
            float nuevaNota = Float.parseFloat(txtNota.getText());

            // Guardamos la nota en el controlador
            ctrDetalles.guardarNota(String.valueOf(nuevaNota), codigoAsignaturaActual);

            // Mostrar mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Nota guardada correctamente.");

            // Recargamos los datos para reflejar la nueva nota en la vista
            ctrDetalles.recargarDatos();
            actualizarVista(); // Actualizamos la vista para que el usuario pueda seguir navegando
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor válido para la nota.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la nota: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
