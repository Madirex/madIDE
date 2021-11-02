package com.madirex.components.menu;


import com.madirex.util.Utils;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuEdition
{
    private JMenuItem item;
    private final JComponent MENU;

    public MenuEdition(JComponent menu){
        this.MENU = menu;

        menu.getAccessibleContext().setAccessibleDescription(
                "Menú de edición");
    }

    public void undoAndRedo() {
        //Undo
        item = new JMenuItem("Deshacer",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Deshacer acción");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorPanel().doUndo();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);

        //Redo
        item = new JMenuItem("Rehacer",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Rehacer acción");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorPanel().doRedo();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);
    }

    public void portapapeles(){
        //Copy
        item = new JMenuItem("Copiar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Copiar el texto seleccionado");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().copy();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);

        //Cut
        item = new JMenuItem("Cortar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Cortar el texto seleccionado");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().cut();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);

        //Paste
        item = new JMenuItem("Pegar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Pegar el texto seleccionado");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().paste();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);
    }

    public void selectRemove(){
        //Delete
        item = new JMenuItem("Suprimir",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Eliminar el texto seleccionado");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().replaceSelection("");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);

        //Select all
        item = new JMenuItem("Seleccionar todo",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Seleccionar todo el texto de la ventana actual");
        item.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().selectAll();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        MENU.add(item);
    }
}