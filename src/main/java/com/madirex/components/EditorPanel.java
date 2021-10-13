package com.madirex.components;

import com.madirex.windows.Ventana;

import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    private EditorText editorText;

    public EditorPanel(Ventana window){
        this.setLayout(new BorderLayout());
        editorText = new EditorText(window);
        JScrollPane sp = new JScrollPane(editorText);
        this.add(sp);
    }

    public EditorText getEditorText() {
        return editorText;
    }
}
