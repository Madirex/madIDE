package com.madirex;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.madirex.util.Utils;
import com.madirex.windows.Ventana;

import javax.swing.*;

public class App {
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                Ventana w = new Ventana();
                Utils.ventana = w;

            }
        });
    }
}
