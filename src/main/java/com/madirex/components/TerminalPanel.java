package com.madirex.components;

import com.madirex.windows.Ventana;

import javax.swing.*;
import java.awt.*;

public class TerminalPanel extends JPanel {
    private TerminalText terminalText;

    public TerminalPanel(){
        this.setLayout(new BorderLayout());
        this.terminalText = new TerminalText();

        //TODO: Agregar proceso terminal

        //Scroll Pane e implementación
        JScrollPane sp2 = new JScrollPane(terminalText);
        this.add(sp2);
    }

    public TerminalText getTerminalText() {
        return terminalText;
    }


}
