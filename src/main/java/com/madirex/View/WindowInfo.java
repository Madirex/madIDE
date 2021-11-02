package com.madirex.View;

import javax.swing.*;
import java.awt.*;


public class WindowInfo extends JDialog {

    public WindowInfo(){
        initialize();
    }

    private void initialize(){

        this.setSize(400,140);
        this.setResizable(false);
        this.setTitle("Acerca de MadIDE");

        //Main panel (JPanel)
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ///Contenido
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Agregar Icono debug
            //Título
            JLabel title = new JLabel();
            title.setText("MadIDE");
            title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
            title.setBorder(BorderFactory.createEmptyBorder(0, 0,
                    5, 0)); //Crear márgenes
            title.setAlignmentX(JLabel.CENTER_ALIGNMENT); //CENTRAR
            contenido.add(title);

            //Descripción
            JLabel description = new JLabel();
            description.setText("Tu IDE de confianza.");
            description.setBorder(BorderFactory.createEmptyBorder(0, 0,
                    5, 0)); //Crear márgenes
            description.setAlignmentX(JLabel.CENTER_ALIGNMENT); //CENTRAR
            contenido.add(description);

            //Copyright
            JLabel copyright = new JLabel();
            copyright.setText("Madirex - Todos los derechos reservados");
            copyright.setBorder(BorderFactory.createEmptyBorder(0, 0,
                    5, 0)); //Crear márgenes
            copyright.setAlignmentX(JLabel.CENTER_ALIGNMENT); //CENTRAR
            contenido.add(copyright);

            //Web
            JLabel web = new JLabel();
            web.setText("https://www.madirex.com");
            web.setBorder(BorderFactory.createEmptyBorder(0, 0,
                    5, 0)); //Crear márgenes
            web.setAlignmentX(JLabel.CENTER_ALIGNMENT); //CENTRAR
            contenido.add(web);

        panel.add(contenido,BorderLayout.CENTER);

        ///
        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}