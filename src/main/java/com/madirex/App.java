package com.madirex;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.madirex.util.Utils;
import com.madirex.View.Ventana;

import javax.swing.*;
import java.io.File;

public class App {
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(() -> {
            //Look And Feel
            FlatLightLaf.setup();

            //SETUP
            try {
                UIManager.setLookAndFeel( new FlatDarculaLaf() );
            } catch( Exception ex ) {
                System.err.println( "Error al inicializar LaF" );
            }
            UIManager.put( "CheckBox.icon.style", "filled" );

            //Crear ventana
            Utils.ventana = new Ventana();

            //Abrir por defecto archivo de prueba
            String path = System.getProperty("user.dir") + File.separator + "ejemplo" + File.separator + "ejemplo.java";
            File file = new File(path);
            Utils.abrirArchivo(file, path);

        });
    }
}
