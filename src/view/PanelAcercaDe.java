package view;

import javax.swing.*;
import java.awt.*;

public class PanelAcercaDe extends JPanel {
    public PanelAcercaDe() {
        setLayout(new BorderLayout(20, 20)); // Espaciado general

        // Título
        JLabel lblTitulo = new JLabel("Acerca De", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Texto de información
        JTextArea txtInfo = new JTextArea(
                "Versión: 1.0\n" +
                        "\nDesarrollado por: Sebi y Alex\n" +
                        "\nFecha: Enero 2025\n" +
                        "\nDescripción:" +
                        "Esta aplicación permite gestionar alumnos y asignaturas, " +
                        "modificar notas y calcular promedios.\n");
        txtInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);

        // Resaltar apartados
        txtInfo.setText(txtInfo.getText().replace("Versión:", "Versión:\n")
                .replace("Desarrollado por:", "Desarrollado por:\n")
                .replace("Fecha:", "Fecha:\n")
                .replace("Descripción:", "Descripción:\n"));

        JScrollPane scrollPane = new JScrollPane(txtInfo);
        add(scrollPane, BorderLayout.CENTER);

        // Botón regresar
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.setContentPane(new PanelValidar((MainFrame) topFrame));
                topFrame.revalidate();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }
}
