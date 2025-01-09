package view;

import controlador.CtrAlumno;
import model.Alumno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuValidar, menuVisualizar, menuAcercaDe;
    private JMenuItem menuItemEntrar, menuItemSalir, menuItemDetalle, menuItemResumen, menuItemAcercaDe;
    private JPanel panelValidar, panelDetalle, panelResumen, panelAcercaDe;
    private Alumno alumnoValidado; // Alumno validado

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
                // Crear panelResumen con el alumno validado
                if (alumnoValidado != null) {
                    panelResumen = new PanelResumen(alumnoValidado);
                    setContentPane(panelResumen);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "Debe validar un alumno antes de acceder al resumen.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
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
    public void habilitarMenu(Alumno alumno) {
        menuVisualizar.setEnabled(true);
        menuItemDetalle.setEnabled(true);
        menuItemResumen.setEnabled(true);
        this.alumnoValidado = alumno; // Guardar alumno validado
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
