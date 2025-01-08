package view;

import javax.swing.*;
import java.awt.*;

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

public class FrmMain extends JFrame {

    Button validar = new Button();
    Button visualizar = new Button();
    Button acercaDe = new Button();



}
