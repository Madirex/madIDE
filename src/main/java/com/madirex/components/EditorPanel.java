package com.madirex.components;

import com.madirex.util.TextLineNumber;
import com.madirex.util.Utils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import javax.swing.undo.UndoManager;

public class EditorPanel extends JPanel {
    private final EditorText EDITOR_TEXT;
    private String direction;
    private UndoManager undoManager;

    public void setDirection(String direction) {
        this.direction = direction;
        Utils.ventana.updateTreeText();
    }

    public String getDirection() {
        return direction;
    }

    public EditorPanel(String dir){

        this.direction = dir;

        this.setLayout(new BorderLayout());
        //Util.EditorDocument ed = new Util.EditorDocument();
        DefaultStyledDocument ed = new DefaultStyledDocument();
        EDITOR_TEXT = new EditorText(ed);
        actualizarUndo();

        JScrollPane sp = new JScrollPane(EDITOR_TEXT);

        //Líneas
        TextLineNumber lines = new TextLineNumber(EDITOR_TEXT);
        sp.setRowHeaderView( lines );

        this.add(sp);
    }

    public void doUndo(){
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    public void doRedo(){
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

    public EditorText getEDITOR_TEXT() {
        return EDITOR_TEXT;
    }

    public void actualizarUndo(){
        undoManager = new UndoManager();
        undoManager.setLimit(100);
        EDITOR_TEXT.getDocument().addUndoableEditListener(evt -> undoManager.addEdit(evt.getEdit()));
    }


}
