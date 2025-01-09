package view;
import controlador.CtrAlumno;

import javax.swing.*;
import java.awt.*;

public class PanelValidar extends JPanel {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JLabel lblMensaje;

    public PanelValidar(MainFrame mainFrame) {
        setLayout(new BorderLayout(20, 20)); // Separación entre los bordes y componentes principales

        // Panel superior con el título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Bienvenido a Gestión de Alumnos", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 44));
        lblTitulo.setForeground(new Color(0, 102, 204)); // Azul llamativo
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0)); // Separación superior e inferior
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central para los campos de entrada
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        panelCentral.add(txtUsuario, gbc);

        // Campo de contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentral.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtContrasena = new JPasswordField(15);
        panelCentral.add(txtContrasena, gbc);

        // Botón de validar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnValidar = new JButton("Validar");
        btnValidar.addActionListener(e -> validarUsuario2(mainFrame));
        panelCentral.add(btnValidar, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para el mensaje de error o éxito
        JPanel panelMensaje = new JPanel(new BorderLayout());
        lblMensaje = new JLabel("", JLabel.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        panelMensaje.add(lblMensaje, BorderLayout.CENTER);
        panelMensaje.setBorder(BorderFactory.createEmptyBorder(20, 0, 70, 0)); // Separación superior e inferior
        add(panelMensaje, BorderLayout.SOUTH);
    }

    private void validarUsuario4(MainFrame mainFrame) {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // Ejemplo de validación
        if ("admin".equals(usuario) && "1234".equals(contrasena)) {
            mostrarMensaje(true, "¡Acceso concedido!");
            mainFrame.habilitarMenu();
        } else {
            mostrarMensaje(false, "Usuario o contraseña incorrectos.");
        }
    }

    private void validarUsuario2(MainFrame mainFrame) {
        CtrAlumno ctrA = new CtrAlumno();
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        boolean valido = ctrA.validarUsuario(usuario, contrasena);

        if(valido == true){
            mostrarMensaje(true, "¡Acceso concedido!");
            mainFrame.habilitarMenu();
        } else {
            mostrarMensaje(false, "Usuario o contraseña incorrectos.");
        }

    }

    private void mostrarMensaje(boolean exito, String mensaje) {
        if (exito) {
            lblMensaje.setText("<html><font color='green'><b>✔</b> " + mensaje + "</font></html>");
        } else {
            lblMensaje.setText("<html><font color='red'><b>✘</b> " + mensaje + "</font></html>");
        }
    }
}
