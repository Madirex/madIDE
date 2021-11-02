package com.madirex.components;

import javax.swing.*;
import java.awt.*;

public class TerminalPanel extends JPanel {
    private final TerminalText TERMINAL_TEXT;

    public TerminalPanel(){
        this.setLayout(new BorderLayout());
        this.TERMINAL_TEXT = new TerminalText();

        //Scroll Pane e implementación
        JScrollPane sp2 = new JScrollPane(TERMINAL_TEXT);
        this.add(sp2);
    }

    public TerminalText getTERMINAL_TEXT() {
        return TERMINAL_TEXT;
    }


}
