package com.madirex.components;

import com.madirex.components.menu.MenuEdicion;
import com.madirex.windows.Ventana;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorText extends JTextArea {

    private UndoManager undoManager;

    public EditorText(Ventana window) {

        //Inicializar menú popup
        JPopupMenu popup = new JPopupMenu();
        MenuEdicion editionMenu = new MenuEdicion(window);
        popup.add(editionMenu);
        this.setComponentPopupMenu(popup);

        undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);
        this.setFont(new Font("Arial", Font.PLAIN, 16));

        //Cambiar tab por 4 espacios
        this.setTabSize(0);

        this.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    e.consume();
                    tabEvent();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


    }

    public void tabEvent(){
        this.append("    ");
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

}
