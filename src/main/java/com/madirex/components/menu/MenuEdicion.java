package com.madirex.components.menu;


import com.madirex.windows.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuEdicion
{
    private JMenuItem item;
    private Ventana window;
    private JComponent menu;

    public MenuEdicion(Ventana window, JComponent menu){
        this.window = window;
        this.menu = menu;

        menu.getAccessibleContext().setAccessibleDescription(
                "Menú de edición");
    }

    public void undoredo() {
        //Undo
        item = new JMenuItem("Deshacer",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Deshacer acción");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().doUndo();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);

        //Redo
        item = new JMenuItem("Rehacer",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Rehacer acción");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().doRedo();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);
    }

    public void portapapeles(){
        //Copy
        item = new JMenuItem("Copiar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Copiar el texto seleccionado");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().copy();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);

        //Cut
        item = new JMenuItem("Cortar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Cortar el texto seleccionado");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().cut();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);

        //Paste
        item = new JMenuItem("Pegar",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Pegar el texto seleccionado");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().paste();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);
    }

    public void selectRemove(){
        //Delete
        item = new JMenuItem("Suprimir",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Eliminar el texto seleccionado");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().replaceSelection("");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);

        //Select all
        item = new JMenuItem("Seleccionar todo",
                KeyEvent.VK_T);
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item.getAccessibleContext().setAccessibleDescription(
                "Seleccionar todo el texto de la ventana actual");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    window.getActualEditorText().selectAll();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(item);
    }
}