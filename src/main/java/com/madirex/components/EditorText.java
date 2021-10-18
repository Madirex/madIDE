package com.madirex.components;

import com.madirex.components.menu.MenuEdition;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorText extends JTextPane {

    private UndoManager undoManager;
    private boolean archivoModificado = false;
    private String direction = "Test2 > Test2 > Test2"; //TODO: MODIFY TREE DIR

    public boolean isArchivoModificado() {
        return archivoModificado;
    }

    public String getDirection() {
        return direction;
    }

    public EditorText(DefaultStyledDocument doc) {

        //AGREGAR LISTENER
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                archivoModificado = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                archivoModificado = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                archivoModificado = true;
            }
        });



        //Asignar doc
        setStyledDocument(doc);

        //Inicializar menú popup
        JPopupMenu menu = new JPopupMenu();
        //MenuSections editionMenu = new MenuSections(window,popup);
        //editionMenu.menuFormat();
        //
        menu.getAccessibleContext().setAccessibleDescription(
                "Opciones de edición");

        MenuEdition menuEdit = new MenuEdition(menu);

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
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        //this.


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
