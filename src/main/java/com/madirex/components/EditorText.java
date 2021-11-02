package com.madirex.components;

import com.madirex.components.menu.MenuEdition;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorText extends JTextPane {


    private boolean archivoModificado = false;

    public boolean isArchivoModificado() {
        return archivoModificado;
    }

    public EditorText(DefaultStyledDocument doc) {

        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        //AGREGAR LISTENER
        this.getDocument().addDocumentListener(new DocumentListener() {
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
        menu.getAccessibleContext().setAccessibleDescription("Opciones de edición");
        MenuEdition menuEdit = new MenuEdition(menu);
        menuEdit.undoAndRedo();
        menu.addSeparator();
        menuEdit.portapapeles();
        menuEdit.selectRemove();
        this.setComponentPopupMenu(menu);

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
        String espacios = "    ";

        try {
            this.getDocument().insertString(this.getCaretPosition(), espacios, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deshabilita el 'World Wrap' (hace que no haya saltos de línea al alcanzar el límite visual del editor
     * @return boolean
     */
    public boolean getScrollableTracksViewportWidth() {
        Component parent = getParent();
        ComponentUI ui = getUI();

        return parent == null || (ui.getPreferredSize(this).width <= parent
                .getSize().width);
    }

}
