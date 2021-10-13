package com.madirex.windows;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.madirex.components.EditorPanel;
import com.madirex.components.EditorText;
import com.madirex.components.menu.MenuApp;
import com.madirex.util.Util;

import javax.swing.*;
import java.awt.*;


public class Ventana extends JFrame {

    private JPanel jpNav; //Navegación
    private JSplitPane splitPanelMain;
    private JPanel jpConsola;  //Consola
    private JSplitPane splitPanels;
    private JTabbedPane tabsEditorPanel; //TABS

    public EditorText getActualEditorText() {
        Component c = tabsEditorPanel.getComponent(tabsEditorPanel.getSelectedIndex());

        if (c instanceof EditorPanel) {
            return (EditorText) ((EditorPanel) c).getEditorText();//((EditorPanel) c).getEditorText();
        }
        else{
            //Si no existe ningún archivo, devolver null
            return null;
        }
    }

    public Ventana(){
        //Inicializar icono
        Util.initializeIcon(this);

        //Inicializar programa
        initialize();
    }


    private JSplitPane initializesplitPanels() {

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: NAVEGADOR Y EDITOR
        ///////////////////////////
            //NAVEGADOR
            jpNav = new JPanel();
            jpNav.setLayout(new BorderLayout());
            JTree tree = new JTree();
            jpNav.add(tree);

            //Panel editor
            tabsEditorPanel = new JTabbedPane();

            //TODO: TEMP
            EditorPanel jpEditor = new EditorPanel(this);
            tabsEditorPanel.addTab("* Nuevo",jpEditor);

            //SPLIT PANEL
            splitPanelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    jpNav, tabsEditorPanel);
            splitPanelMain.setOneTouchExpandable(true);
            splitPanelMain.setResizeWeight(0.1);

        //TAMAÑO MÍNIMO
        jpNav.setMinimumSize(new Dimension(50, 50));
        jpEditor.setMinimumSize(new Dimension(50, 50));

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: Split (navegador+editor) Y Consola
        ///////////////////////////

        //CONSOLA
        jpConsola = new JPanel();
        jpConsola.setLayout(new BorderLayout());
        JTextPane tp2 = new JTextPane();
        JScrollPane sp2 = new JScrollPane(tp2);
        jpConsola.add(sp2);

        //SPLIT PANEL
        splitPanels = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPanelMain,
                jpConsola);
        splitPanels.setOneTouchExpandable(true);
        splitPanels.setResizeWeight(0.8);


        return splitPanels;
    }

    private JPanel barraMenu() {
        //Barra superior
        JPanel barra = new JPanel();
        barra.setLayout(new BorderLayout());
        barra.setPreferredSize(new Dimension(50, 25));

        //Agregar texto de árbol
        JTextArea treeText = new JTextArea();
        treeText.setText("Test > Test2 > Test3"); //TODO: MODIFY
        treeText.setEnabled(false);
        barra.add(treeText,BorderLayout.WEST);

        //BARRA DE ICONOS
        JPanel barraIconos = new JPanel();
        barraIconos.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

        //Agregar Icono build
        Icon buildI = new FlatSVGIcon("images/build.svg");
        JLabel buildL = new JLabel(buildI);
        barraIconos.add(buildL);

        //Agregar Icono guardar
        Icon playI = new FlatSVGIcon("images/play.svg");
        JLabel playL = new JLabel(playI);
        barraIconos.add(playL);

        //Agregar Icono debug
        Icon debugI = new FlatSVGIcon("images/debug.svg");
        JLabel debugL = new JLabel(debugI);
        barraIconos.add(debugL);

        //Agregar Icono stop
        Icon stopI = new FlatSVGIcon("images/stop.svg");
        JLabel stopL = new JLabel(stopI);
        stopL.setEnabled(false); //Deshabilitar por defecto
        barraIconos.add(stopL);

        barra.add(barraIconos,BorderLayout.EAST);

        return barra;
    }

    private void initialize(){

        this.setSize(1200,720);
        this.setPreferredSize(new Dimension(1200,720));
        this.setMinimumSize(new Dimension(400,300));

        //JPANEL Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //MENÚ
        JMenuBar menuBar = new MenuApp(this);
        this.setJMenuBar(menuBar);
        this.setTitle("MadIDE");

        //Agregar menú
        panel.add(barraMenu(), BorderLayout.NORTH);

            //Panel central IDE
            panel.add(initializesplitPanels(),BorderLayout.CENTER);

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    //GETTERS && SETTERS
    public JPanel getJpConsola() {
        return jpConsola;
    }

    public JSplitPane getSplitPanels() {
        return splitPanels;
    }

    public JPanel getJpNav() {
        return jpNav;
    }

    public JSplitPane getSplitPanelMain() {
        return splitPanelMain;
    }

    public JTabbedPane getTabsEditorPanel() {
        return tabsEditorPanel;
    }

}