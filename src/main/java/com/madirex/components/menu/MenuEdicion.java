package com.madirex.components.menu;


import com.madirex.windows.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuEdicion extends JMenu
{
    private JMenuItem item;
    private Ventana window;

    public MenuEdicion(Ventana window){
        this.window = window;
        crearMenu();
    }

    public void crearMenu(){

        this.setMnemonic(KeyEvent.VK_S);
        this.getAccessibleContext().setAccessibleDescription(
                "Menú de edición");


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
                try{
                    window.getActualEditorText().doUndo();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

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
                try{
                    window.getActualEditorText().doRedo();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

        /////////////////
        ///Manipulación de texto
        /////////////////
        this.addSeparator();

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
                try{
                    window.getActualEditorText().copy();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

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
                try{
                    window.getActualEditorText().cut();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

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
                try{
                    window.getActualEditorText().paste();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

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
                try{
                    window.getActualEditorText().replaceSelection("");
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

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
                try{
                    window.getActualEditorText().selectAll();
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        this.add(item);

            /*
            /////////////////
            ///SEARCH
            /////////////////
            menu.addSeparator();

            //Search
            menuItem = new JMenuItem("Buscar...",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_F, ActionEvent.CTRL_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Buscar algo en el proyecto");
            menu.add(menuItem);
            */
    }


}