package view;

import javax.swing.*;
import java.awt.*;

public class PanelDetalle extends JPanel {
    private JLabel lblAsignatura;
    private JTextField txtNota;
    private JButton btnGuardar, btnAnterior, btnSiguiente;

    public PanelDetalle() {
        setLayout(new BorderLayout(20, 20)); // Espaciado general

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
    }
}
