package com.madirex.util;

import com.madirex.components.TerminalText;
import com.madirex.windows.Ventana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramProcess {
    private Ventana window;
    private String path;

    public ProgramProcess(Ventana window){
        this.window = window;
        this.path = window.getTabsEditorPanel().getTitleAt(window.getTabsEditorPanel().getSelectedIndex());
    }

    public void compilar() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd.exe /C javac " + path);
    }

    public void run() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd.exe /C java " + path);

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        TerminalText terminal = window.getJpTerminal().getTerminalText();

        terminal.setText(""); //Resetear contenido
        while((line = br.readLine()) != null){
            terminal.setText(terminal.getText() + line + "\n"); //Agregar línea
        }
    }
}
