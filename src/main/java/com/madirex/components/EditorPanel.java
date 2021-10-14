package com.madirex.components;

import com.madirex.util.TextLineNumber;
import com.madirex.util.Util;
import com.madirex.windows.Ventana;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    private EditorText editorText;

    public EditorPanel(Ventana window){
        this.setLayout(new BorderLayout());
        //Util.EditorDocument ed = new Util.EditorDocument();
        DefaultStyledDocument ed = new DefaultStyledDocument();
        editorText = new EditorText(window, ed);

        JScrollPane sp = new JScrollPane(editorText);

        //Líneas
        TextLineNumber lines = new TextLineNumber(editorText);
        sp.setRowHeaderView( lines );

        this.add(sp);
    }

    public EditorText getEditorText() {
        return editorText;
    }


}
