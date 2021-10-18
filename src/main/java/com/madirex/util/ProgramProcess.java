package com.madirex.util;

import com.madirex.components.TerminalText;
import com.madirex.windows.WindowOpenSave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramProcess {
    private String path;

    public ProgramProcess(){
        this.path = Utils.ventana.getTabsEditorPanel().getTitleAt(Utils.ventana.getTabsEditorPanel().getSelectedIndex());
    }

    public void compilar() throws IOException {
        //Guardar antes de compilar
        WindowOpenSave w = new WindowOpenSave(false);
        w.guardar();

        //Compilar
        Process process = Runtime.getRuntime().exec("cmd.exe /C javac " + path);
    }

    public void debug() throws IOException{
        //Guardar antes de hacer debug
        WindowOpenSave w = new WindowOpenSave(false);
        w.guardar();

        //Hacer debug
        Process process = Runtime.getRuntime().exec("cmd.exe /C java " + path);
    }

    public void run() throws IOException {
        //Guardar antes de ejecutar
        WindowOpenSave w = new WindowOpenSave(false);
        w.guardar();

        //Ejecutar
        Process process = Runtime.getRuntime().exec("cmd.exe /C java " + path);

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        TerminalText terminal = Utils.ventana.getJpTerminal().getTerminalText();

        terminal.setText(""); //Resetear contenido
        while((line = br.readLine()) != null){
            terminal.setText(terminal.getText() + line + "\n"); //Agregar línea
        }
    }
}
