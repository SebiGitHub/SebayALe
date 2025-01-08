package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
Flujo de Trabajo
    Inicio de la App:
        App.java carga el MainFrame y muestra el menú desactivado.
    Validación:
        Usuario introduce usuario y contraseña.
        Si es correcto:
            Activa el resto del menú.
            Carga los datos del alumno validado.
    Visualización / Detalle:
        Carga asignaturas del alumno.
        Permite navegar, modificar notas y guardarlas.
    Visualización / Resumen:
        Muestra todos los datos del alumno (incluyendo imagen).
        Permite calcular la nota media y actualizar si hay discrepancia.
    Acerca de:
        Ventana modal informativa sobre el proyecto.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuValidar, menuVisualizar, menuAcercaDe;
    private JMenuItem menuItemEntrar, menuItemSalir, menuItemDetalle, menuItemResumen, menuItemAcercaDe;
    private JPanel panelValidar, panelDetalle, panelResumen, panelAcercaDe;

    public MainFrame() {
        // Configuración básica
        setTitle("Gestión de Alumnos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear menús
        menuBar = new JMenuBar();
        menuValidar = new JMenu("Validar");
        menuVisualizar = new JMenu("Visualizar");
        menuAcercaDe = new JMenu("Acerca de");

        // Crear elementos del menú
        menuItemEntrar = new JMenuItem("Entrar");
        menuItemSalir = new JMenuItem("Salir");
        menuItemDetalle = new JMenuItem("Detalle");
        menuItemResumen = new JMenuItem("Resumen");
        menuItemAcercaDe = new JMenuItem("Acerca de");

        // Agregar elementos a los menús
        menuValidar.add(menuItemEntrar);
        menuValidar.add(menuItemSalir);
        menuVisualizar.add(menuItemDetalle);
        menuVisualizar.add(menuItemResumen);
        menuAcercaDe.add(menuItemAcercaDe);

        // Añadir menús a la barra de menús
        menuBar.add(menuValidar);
        menuBar.add(menuVisualizar);
        menuBar.add(menuAcercaDe);

        setJMenuBar(menuBar);

        // Inicializar paneles
        panelValidar = new PanelValidar(this);
        panelDetalle = new PanelDetalle();
        panelResumen = new PanelResumen();
        panelAcercaDe = new PanelAcercaDe();

        // Desactivar opciones de menú inicialmente
        menuVisualizar.setEnabled(false);
        menuItemDetalle.setEnabled(false);
        menuItemResumen.setEnabled(false);

        // Eventos de menú
        menuItemEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(panelValidar);
                revalidate();
                repaint();
            }
        });
        menuItemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                revalidate();
                repaint();
            }
        });
        menuItemDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(panelDetalle);
                revalidate();
                repaint();
            }
        });
        menuItemResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(panelResumen);
                revalidate();
                repaint();
            }
        });
        menuItemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(panelAcercaDe);
                revalidate();
                repaint();
            }
        });


        // Mostrar panel inicial
        setContentPane(panelValidar);
    }

    // Habilita las opciones del menú tras validación
    public void habilitarMenu() {
        menuVisualizar.setEnabled(true);
        menuItemDetalle.setEnabled(true);
        menuItemResumen.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
