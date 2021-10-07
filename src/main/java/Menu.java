import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {

    //Menú
    JMenu menu, submenu;
    JMenuItem menuItem;

    Menu(){
        initializeFile();
        initializeEdit();
        initializeRun();
        initializeView();
        initializeHelp();
    }

    private void initializeFile(){
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "File Menu");
        this.add(menu);

        //NEW
        menuItem = new JMenuItem("New project",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Create a new project");
        menu.add(menuItem); //ADD

        //OPEN
        menuItem = new JMenuItem("Open project",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Load an existing project");
        menu.add(menuItem); //ADD

        //SAVE
        menuItem = new JMenuItem("Save project",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Save the project");
        menu.add(menuItem); //ADD

        //CONFIG
        menu.addSeparator();
        menuItem = new JMenuItem("Config",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Edit IDE Config");
        menu.add(menuItem); //ADD

        //EXIT IDE
        menu.addSeparator();
        menuItem = new JMenuItem("Exit",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Close IDE");
        menu.add(menuItem); //ADD

    }

    private void initializeEdit(){
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription(
                "Edit Menu");


        //Undo
        menuItem = new JMenuItem("Undo",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Undo action");
        menu.add(menuItem);

        //Redo
        menuItem = new JMenuItem("Redo",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Redo action");
        menu.add(menuItem);

        /////////////////
        ///Manipulación de texto
        /////////////////
        menu.addSeparator();

        //Cut
        menuItem = new JMenuItem("Cut",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Cut selected text");
        menu.add(menuItem);

        //Copy
        menuItem = new JMenuItem("Copy",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Copy selected text");
        menu.add(menuItem);

        //Paste
        menuItem = new JMenuItem("Paste",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Paste selected text");
        menu.add(menuItem);

        //Delete
        menuItem = new JMenuItem("Delete",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Delete selected text");
        menu.add(menuItem);

        //Select all
        menuItem = new JMenuItem("Select all",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Select all the text");
        menu.add(menuItem);

        /////////////////
        ///SEARCH
        /////////////////
        menu.addSeparator();

        //Search
        menuItem = new JMenuItem("Search...",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Search something in the project");
        menu.add(menuItem);


        //ADD
        this.add(menu);
    }

    private void initializeRun() {
        menu = new JMenu("Run");
        menu.setMnemonic(KeyEvent.VK_D);
        menu.getAccessibleContext().setAccessibleDescription(
                "Edit Menu");


        //RUN
        menuItem = new JMenuItem("Run",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Run program");
        menu.add(menuItem);

        //DEBUG
        menuItem = new JMenuItem("Debug",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F6, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Debug program");
        menu.add(menuItem);

        //BUILD
        menuItem = new JMenuItem("Build",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F7, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Build program");
        menu.add(menuItem);

        ///
        //STOP
        menu.addSeparator();
        menuItem = new JMenuItem("Stop",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Stop program");
        menuItem.setEnabled(false);
        menu.add(menuItem);


        //ADD
        this.add(menu);
    }

    private void initializeView() {
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "File Menu");
        this.add(menu);

        //VIEW
        //TODO: ADD

    //THEMES
        menu.addSeparator();
        submenu = new JMenu("Theme");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Light Theme");
        submenu.add(menuItem);

        menuItem = new JMenuItem("Dark Theme");
        submenu.add(menuItem);

        menuItem = new JMenuItem("MadiPro Theme");
        submenu.add(menuItem);

        menu.add(submenu);

        //ADD
        this.add(menu);
    }

    private void initializeHelp() {

        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.getAccessibleContext().setAccessibleDescription(
                "Show MadIDE Help Menu");



        //Help
        menuItem = new JMenuItem("Help...",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Show IDE help");
        menu.add(menuItem);

        //About
        menu.addSeparator();
        menuItem = new JMenuItem("About MadIDE",
                KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Show information about this IDE");
        menu.add(menuItem);

        //ADD
        this.add(menu);
    }
}
