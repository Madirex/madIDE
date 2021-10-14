package com.madirex.components;

import com.madirex.components.menu.MenuEdicion;
import com.madirex.windows.Ventana;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorText extends JTextPane {

    private UndoManager undoManager;



    public EditorText(Ventana window, DefaultStyledDocument doc) {

        //Asignar doc
        setStyledDocument(doc);

        //Inicializar menú popup
        JPopupMenu menu = new JPopupMenu();
        //MenuSections editionMenu = new MenuSections(window,popup);
        //editionMenu.menuFormat();
        //
        menu.getAccessibleContext().setAccessibleDescription(
                "Opciones de edición");

        MenuEdicion menuEdit = new MenuEdicion(window,menu);

        //Undo Redo
        menuEdit.undoredo();
        menu.addSeparator();
        menuEdit.portapapeles();
        menuEdit.selectRemove();

        //ADD
        this.setComponentPopupMenu(menu);

        //Undo Manager
        undoManager = new UndoManager();
        this.getDocument().addUndoableEditListener(undoManager);
        this.setFont(new Font("Arial", Font.PLAIN, 16));



        //Cambiar tab por 4 espacios
        //this.setTabSize(0);

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
        try{
            String espacios = "    ";
            this.getDocument().insertString(this.getCaretPosition(), espacios, null);
        }catch(Exception ex){
            System.out.println(ex);
        }
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
