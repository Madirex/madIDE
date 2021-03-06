package com.madirex.components.menu;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.madirex.util.ProgramProcess;
import com.madirex.util.Utils;
import com.madirex.View.WindowInfo;
import com.madirex.View.WindowOpenSave;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuApp extends JMenuBar {

    //Menú
    JMenu menu, submenu;
    JMenuItem menuItem;

    public MenuApp(){

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
                KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Crear un nuevo proyecto");
        menuItem.addActionListener(e -> {
            WindowOpenSave w = new WindowOpenSave(true);
            w.nuevo();
        });
        menu.add(menuItem); //ADD

        //OPEN
        menuItem = new JMenuItem("Abrir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Abre un proyecto existente");
        menuItem.addActionListener(e -> {
            WindowOpenSave w = new WindowOpenSave(true);
            w.abrir();
        });
        menu.add(menuItem); //ADD

        //SAVE
        menuItem = new JMenuItem("Guardar",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Guarda el proyecto actual");
        menuItem.addActionListener(e -> {
            WindowOpenSave w = new WindowOpenSave(false);
            w.guardar();
        });
        menu.add(menuItem); //ADD

        //GUARDAR COMO...
        menuItem = new JMenuItem("Guardar como...",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Guarda el proyecto actual en una ubicación a" +
                        "especificar, con el formato deseado");
        menuItem.addActionListener(e -> {
            WindowOpenSave w = new WindowOpenSave(false);
            w.guardarComo();
        });
        menu.add(menuItem); //ADD

        //PRINT
        menu.addSeparator();
        menuItem = new JMenuItem("Imprimir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Imprime el contenido");
        menuItem.addActionListener(e -> {
            try {
                Utils.ventana.getActualEditorText().print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });
        menu.add(menuItem); //ADD

        //EXIT IDE
        menu.addSeparator();
        menuItem = new JMenuItem("Salir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Cerrar IDE");
        menuItem.addActionListener(e -> {
            try{
                System.exit(0);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        });
        menu.add(menuItem); //ADD

    }

    private void initializeEdit(){
        menu = new JMenu("Edición");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription(
                "Opciones de edición");

        MenuEdition menuEdit = new MenuEdition(menu);

        //Undo Redo
        menuEdit.undoAndRedo();
        menu.addSeparator();
        menuEdit.portapapeles();
        menuEdit.selectRemove();

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
                KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Ejecutar programa");
        menuItem.addActionListener(e -> {
            ProgramProcess pg = new ProgramProcess();
            pg.runProgram();
        });
        menu.add(menuItem);

        //DEBUG
        menuItem = new JMenuItem("Debug",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F6, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Hacer Debug");
        menuItem.addActionListener(e -> {
            ProgramProcess p = new ProgramProcess();
            p.debug();
        });
        menu.add(menuItem);

        //BUILD
        menuItem = new JMenuItem("Build",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F7, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Hacer build");
        menuItem.addActionListener(e -> {
            ProgramProcess p = new ProgramProcess();
            p.compilar();
        });
        menu.add(menuItem);

        ///
        //STOP //TODO: STOP
        menu.addSeparator();
        menuItem = new JMenuItem("Parar",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F2, KeyEvent.CTRL_DOWN_MASK));
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
        menuOcultarNav.addActionListener(e -> {

            if (Utils.ventana.getJpNav().isVisible()) {
                Utils.ventana.getJpNav().setVisible(false);
                menuOcultarNav.setText("Mostrar navegador");
            }
            else{
                Utils.ventana.getJpNav().setVisible(true);
                Utils.ventana.getSplitPanelMain().resetToPreferredSizes();
                menuOcultarNav.setText("Ocultar navegador");
            }

        });
        menu.add(menuOcultarNav);

        //Menú de terminal
        JMenuItem menuOcultarTerminal = new JMenuItem("Ocultar terminal",
                KeyEvent.VK_T);
        menuOcultarTerminal.getAccessibleContext().setAccessibleDescription(
                "Oculta / Muestra el terminal");
        menuOcultarTerminal.addActionListener(e -> {

            if (Utils.ventana.getJpTerminal().isVisible()) {
                Utils.ventana.getJpTerminal().setVisible(false);
                menuOcultarTerminal.setText("Mostrar terminal");
            }
            else{
                Utils.ventana.getJpTerminal().setVisible(true);
                Utils.ventana.getSplitPanels().resetToPreferredSizes();
                menuOcultarTerminal.setText("Ocultar terminal");
            }

        });
        menu.add(menuOcultarTerminal);

    //THEMES
        menu.addSeparator();
        submenu = new JMenu("Tema");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Light Theme");
        menuItem.addActionListener(e -> {
            //SETUP
            try {
                UIManager.setLookAndFeel( new FlatLightLaf() );
                SwingUtilities.updateComponentTreeUI(Utils.ventana);

            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            UIManager.put( "CheckBox.icon.style", "filled" );
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("Dark Theme");
        menuItem.addActionListener(e -> {
            //SETUP
            try {
                UIManager.setLookAndFeel( new FlatDarkLaf() );
                SwingUtilities.updateComponentTreeUI(Utils.ventana);
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            UIManager.put( "CheckBox.icon.style", "filled" );
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("MadiPro Theme");
        menuItem.addActionListener(e -> {
            //SETUP
            try {
                UIManager.setLookAndFeel( new FlatDarculaLaf() );
                SwingUtilities.updateComponentTreeUI(Utils.ventana);
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            UIManager.put( "CheckBox.icon.style", "filled" );
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
        menuItem.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(
                        new URL("https://www.madirex.com/").toURI());
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
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
        menuItem.addActionListener(e -> {
            WindowInfo w = new WindowInfo();
            w.setIconImage(Utils.ventana.getIconImage());
        });

        menuItem.getAccessibleContext().setAccessibleDescription(
                "Muestra información acerca de este IDE");
        menu.add(menuItem);

        //ADD
        this.add(menu);
    }
}
