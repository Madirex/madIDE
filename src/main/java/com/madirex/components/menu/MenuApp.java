package com.madirex.components.menu;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.madirex.windows.Ventana;
import com.madirex.windows.WindowInfo;
import com.madirex.windows.WindowOpenSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuApp extends JMenuBar {

    //Menú
    JMenu menu, submenu;
    JMenuItem menuItem;
    Ventana window;

    public MenuApp(Ventana window){
        this.window = window;

        initializeFile();
        initializeEdit();
        initializeRun();
        initializeView();
        initializeHelp();
    }

    private void initializeFile(){
        menu = new JMenu("Archivo");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Menú de archivo");
        this.add(menu);

        //NEW
        menuItem = new JMenuItem("Nuevo",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Crear un nuevo proyecto");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowOpenSave w = new WindowOpenSave(window,true);
                w.nuevo();
            }
        });
        menu.add(menuItem); //ADD

        //OPEN
        menuItem = new JMenuItem("Abrir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Abre un proyecto existente");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowOpenSave w = new WindowOpenSave(window,true);
                w.abrir();
            }
        });
        menu.add(menuItem); //ADD

        //SAVE
        menuItem = new JMenuItem("Guardar",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Guarda el proyecto actual");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowOpenSave w = new WindowOpenSave(window,false);
                w.guardar();
            }
        });
        menu.add(menuItem); //ADD

        //GUARDAR COMO...
        menuItem = new JMenuItem("Guardar como...",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Guarda el proyecto actual en una ubicación a" +
                        "especificar, con el formato deseado");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowOpenSave w = new WindowOpenSave(window,false);
                w.guardarComo();
            }
        });
        menu.add(menuItem); //ADD

        //PRINT
        menu.addSeparator();
        menuItem = new JMenuItem("Imprimir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Imprime el contenido");
        menu.add(menuItem); //ADD

        /*
        //CONFIG
        menu.addSeparator();
        menuItem = new JMenuItem("Configuración",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Editar la configuración del IDE");
        menu.add(menuItem); //ADD
        */

        //EXIT IDE
        menu.addSeparator();
        menuItem = new JMenuItem("Salir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Cerrar IDE");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    System.exit(0);
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        menu.add(menuItem); //ADD

    }

    private void initializeEdit(){
        menu = new JMenu("Edición");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription(
                "Opciones de edición");

        MenuEdicion edition = new MenuEdicion(window);
        menu.add(edition);

        //ADD
        this.add(menu);
    }

    private void initializeRun() {
        menu = new JMenu("Run");
        menu.setMnemonic(KeyEvent.VK_D);
        menu.getAccessibleContext().setAccessibleDescription(
                "Opciones de ejecución");


        //RUN
        menuItem = new JMenuItem("Run",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Ejecutar programa");
        menu.add(menuItem);

        //DEBUG
        menuItem = new JMenuItem("Debug",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F6, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Hacer Debug");
        menu.add(menuItem);

        //BUILD
        menuItem = new JMenuItem("Build",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F7, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Hacer build");
        menu.add(menuItem);

        ///
        //STOP
        menu.addSeparator();
        menuItem = new JMenuItem("Parar",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Parar ejecución del programa");
        menuItem.setEnabled(false);
        menu.add(menuItem);


        //ADD
        this.add(menu);
    }

    private void initializeView() {
        menu = new JMenu("Vista");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "Menú vista");
        this.add(menu);

        //Menú de navegación
        JMenuItem menuOcultarNav = new JMenuItem("Ocultar navegador",
                KeyEvent.VK_T);
        menuOcultarNav.getAccessibleContext().setAccessibleDescription(
                "Oculta / Muestra el panel de navegación");
        menuOcultarNav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (window.getJpNav().isVisible()) {
                    window.getJpNav().setVisible(false);
                    menuOcultarNav.setText("Mostrar navegador");
                }
                else{
                    window.getJpNav().setVisible(true);
                    window.getSplitPanelMain().resetToPreferredSizes();
                    menuOcultarNav.setText("Ocultar navegador");
                }

            }
        });
        menu.add(menuOcultarNav);

        //Menú de consola
        JMenuItem menuOcultarConsola = new JMenuItem("Ocultar consola",
                KeyEvent.VK_T);
        menuOcultarConsola.getAccessibleContext().setAccessibleDescription(
                "Oculta / Muestra la consola");
        menuOcultarConsola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (window.getJpConsola().isVisible()) {
                    window.getJpConsola().setVisible(false);
                    menuOcultarConsola.setText("Mostrar consola");
                }
                else{
                    window.getJpConsola().setVisible(true);
                    window.getSplitPanels().resetToPreferredSizes();
                    menuOcultarConsola.setText("Ocultar consola");
                }

            }
        });
        menu.add(menuOcultarConsola);

    //THEMES
        menu.addSeparator();
        submenu = new JMenu("Tema");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Light Theme");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SETUP
                try {
                    UIManager.setLookAndFeel( new FlatLightLaf() );
                    SwingUtilities.updateComponentTreeUI(window);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                UIManager.put( "CheckBox.icon.style", "filled" );
            }
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("Dark Theme");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SETUP
                try {
                    UIManager.setLookAndFeel( new FlatDarkLaf() );
                    SwingUtilities.updateComponentTreeUI(window);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                UIManager.put( "CheckBox.icon.style", "filled" );
            }
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("MadiPro Theme");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SETUP
                try {
                    UIManager.setLookAndFeel( new FlatDarculaLaf() );
                    SwingUtilities.updateComponentTreeUI(window);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                UIManager.put( "CheckBox.icon.style", "filled" );
            }
        });
        submenu.add(menuItem);

        menu.add(submenu);

        //ADD
        this.add(menu);
    }

    private void initializeHelp() {

        menu = new JMenu("Ayuda");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription(
                "Ver la ayuda de MadIDE");

        //Help
        menuItem = new JMenuItem("Ayuda...",
                KeyEvent.VK_T);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new URL("https://www.madirex.com/").toURI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Ver la ayuda de MadIDE");
        menu.add(menuItem);

        //About
        menu.addSeparator();
        menuItem = new JMenuItem("Acerca de MadIDE",
                KeyEvent.VK_T);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowInfo w = new WindowInfo();
                w.setIconImage(window.getIconImage());
            }
        });

        menuItem.getAccessibleContext().setAccessibleDescription(
                "Muestra información acerca de este IDE");
        menu.add(menuItem);

        //ADD
        this.add(menu);
    }
}
