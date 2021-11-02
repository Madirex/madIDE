package com.madirex.util;

import com.madirex.components.EditorPanel;
import com.madirex.components.TerminalText;
import com.madirex.View.WindowOpenSave;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProgramProcess {
    final private String PATH;

    public ProgramProcess(){
        Component temp = Utils.ventana.getTabsEditorPanel()
                .getComponentAt(Utils.ventana.getTabsEditorPanel().getSelectedIndex());

        if (Utils.ventana.getTabsEditorPanel()
                .getComponentAt(Utils.ventana.getTabsEditorPanel().getSelectedIndex()) instanceof EditorPanel){
            this.PATH = ((EditorPanel) temp).getDirection();
        }
        else{
            this.PATH = "";
        }
    }


    public void compilar() {

        SwingUtilities.invokeLater(() -> {
            try
            {
                //Guardar antes de compilar
                WindowOpenSave w = new WindowOpenSave(false);
                w.guardar();

                //Compilar
                String[] comandos;
                comandos = new String[] { "javac", PATH};

                Process process = Runtime.getRuntime().exec(comandos);

                TerminalText terminal = Utils.ventana.getJpTerminal().getTERMINAL_TEXT();
                terminal.setText("");

                BufferedReader stdError = new BufferedReader(new
                        InputStreamReader(process.getErrorStream()));

                //LEER CÓDIGO EJECUTADO
                String s;
                boolean errores = false;

                //LEER ERRORES
                while ((s = stdError.readLine()) != null) {
                    terminal.setText(terminal.getText() + s + "\n"); //Agregar línea
                    errores = true;
                }

                if (!errores){
                    terminal.setText("Compilación realizada.\nNo se han encontrado errores.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void debug() {
        SwingUtilities.invokeLater(() -> {
            //try {
                //TODO: DEBUG
           // } catch (Exception ex) {
           //     ex.printStackTrace();
           // }
        });
    }

    public void runProgram() {
        SwingUtilities.invokeLater(() -> {
            try {
            TerminalText terminal = Utils.ventana.getJpTerminal().getTERMINAL_TEXT();
            terminal.setText(""); //Resetear contenido

            //Guardar antes de ejecutar
            WindowOpenSave w = new WindowOpenSave(false);
            w.guardar();

            //Ejecutar
                String[] comandos;
                comandos = new String[] { "java", PATH};
                Process process = Runtime.getRuntime().exec(comandos);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));


            //LEER CÓDIGO EJECUTADO
            String s;
            while ((s = stdInput.readLine()) != null) {
                terminal.setText(terminal.getText() + s + "\n"); //Agregar línea
            }

            //LEER ERRORES
            while ((s = stdError.readLine()) != null) {

                //Colorear de rojo
                Style style = terminal.addStyle("red", null);
                StyleConstants.setForeground(style, new Color(255,122,106));
                terminal.getDocument().insertString(terminal.getDocument().getLength(), terminal.getText() + s + "\n",style);

            }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


}
