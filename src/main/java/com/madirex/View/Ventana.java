package com.madirex.View;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.madirex.components.EditorPanel;
import com.madirex.components.EditorText;
import com.madirex.components.TerminalPanel;
import com.madirex.components.menu.MenuApp;
import com.madirex.util.ProgramProcess;
import com.madirex.util.Utils;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class Ventana extends JFrame {

    private JPanel jpNav; //Navegación
    private JSplitPane splitPanelMain;
    private TerminalPanel jpTerminal;  //Terminal
    private JSplitPane splitPanels;
    private JTabbedPane tabsEditorPanel; //TABS
    private JTextArea treeText;

    //Atributos estáticos
    private final static JTree TREE = new JTree();
    private static String treePath = System.getProperty("user.dir");

    ///GETTERS && SETTERS

    public static JTree getTree() {
        return TREE;
    }

    public static String getTreePath() {
        return treePath;
    }

    public static void setTreePath(String treePath) {
        Ventana.treePath = treePath;
    }

    public EditorText getActualEditorText() {
        Component c = tabsEditorPanel.getComponent(tabsEditorPanel.getSelectedIndex());

        if (c instanceof EditorPanel) {
            return ((EditorPanel) c).getEDITOR_TEXT();
        }
        else{
            JOptionPane.showMessageDialog(Utils.ventana
                    , "Error: El archivo no existe."
                    , "Archivo inexistente", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public EditorPanel getActualEditorPanel() {
        Component c = tabsEditorPanel.getComponent(tabsEditorPanel.getSelectedIndex());

        if (c instanceof EditorPanel) {
            return (EditorPanel) c;
        }
        else{
            return null;
        }
    }

    public Ventana(){
        //Inicializar icono
        Utils.initializeIcon(this);

        //Inicializar programa
        initialize();


    }

    public static void actualizarTree(){
        DefaultMutableTreeNode nodo = Utils.treeCreation(Ventana.getTreePath());
        DefaultTreeModel modelo =  new DefaultTreeModel(nodo);
        Ventana.getTree().setModel(modelo);
    }

    private JSplitPane initializeSplitPanels() {

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: NAVEGADOR Y EDITOR
        ///////////////////////////
            //NAVEGADOR
            jpNav = new JPanel();
            jpNav.setLayout(new BorderLayout());

            //INICIALIZAR tree
            actualizarTree();

            //Agregar listener y acciones al tree
            TREE.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {

                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) TREE.getLastSelectedPathComponent();

                        //CREAR LA DIRECCIÓN
                        StringBuilder dir = new StringBuilder();
                        DefaultMutableTreeNode actualNode = node;

                        while (actualNode.getParent() != null){
                            dir.insert(0, actualNode + File.separator); //Agregar a dirección
                            actualNode = (DefaultMutableTreeNode) actualNode.getParent(); //Poner el siguiente parent
                        }

                        //Eliminar el file separator del final para que quede una dirección correcta
                        if (!dir.toString().equals("")){
                            dir = new StringBuilder(dir.substring(0, dir.length() - 1));
                        }

                        dir.insert(0, treePath + File.separator); //Crear dirección inicial del Tree

                        File file = new File(dir.toString());

                        if (!file.isDirectory()){
                            Utils.abrirArchivo(file, dir.toString());
                        }

                    }
                }
            });

            jpNav.add(TREE);

            //Panel editor
            tabsEditorPanel = new JTabbedPane();
            tabsEditorPanel.addChangeListener(e -> updateTreeText());

            //SPLIT PANEL
            splitPanelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    jpNav, tabsEditorPanel);
            splitPanelMain.setOneTouchExpandable(true);
            splitPanelMain.setResizeWeight(0.1);

        //TAMAÑO MÍNIMO
        jpNav.setMinimumSize(new Dimension(50, 50));
        //jpEditor.setMinimumSize(new Dimension(50, 50));

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: Split (navegador+editor) Y Terminal
        ///////////////////////////

        //TERMINAL
        jpTerminal = new TerminalPanel();

        //SPLIT PANEL
        splitPanels = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPanelMain,
                jpTerminal);
        splitPanels.setOneTouchExpandable(true);
        splitPanels.setResizeWeight(0.7);


        return splitPanels;
    }

    private JPanel barraMenu() {
        //Barra superior
        JPanel barra = new JPanel();
        barra.setLayout(new BorderLayout());
        barra.setPreferredSize(new Dimension(50, 25));

        //Agregar texto de árbol
        treeText = new JTextArea();
        //treeText.setText(this.getActualEditorText().getTreeInfo());
        treeText.setEnabled(false);
        barra.add(treeText,BorderLayout.WEST);

        //BARRA DE ICONOS
        JPanel barraIconos = new JPanel();
        barraIconos.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

        //Agregar Icono build
        Icon buildI = new FlatSVGIcon("images/build.svg");
        JLabel buildL = new JLabel(buildI);
        buildL.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent me) {
                ProgramProcess p = new ProgramProcess();
                p.compilar();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icon buildI = new FlatSVGIcon("images/build2.svg");
                buildL.setIcon(buildI);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icon buildI = new FlatSVGIcon("images/build.svg");
                buildL.setIcon(buildI);
            }
        });
        barraIconos.add(buildL);

        //Agregar Icono ejecutar
        Icon playI = new FlatSVGIcon("images/play.svg");
        JLabel playL = new JLabel(playI);
        playL.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent me) {
                ProgramProcess p = new ProgramProcess();
                p.runProgram();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icon playI = new FlatSVGIcon("images/play2.svg");
                playL.setIcon(playI);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icon playI = new FlatSVGIcon("images/play.svg");
                playL.setIcon(playI);
            }
        });
        barraIconos.add(playL);

        //Agregar Icono debug
        Icon debugI = new FlatSVGIcon("images/debug.svg");
        JLabel debugL = new JLabel(debugI);
        debugL.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent me) {
                ProgramProcess p = new ProgramProcess();
                p.debug();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Icon debugI = new FlatSVGIcon("images/debug2.svg");
                debugL.setIcon(debugI);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Icon debugI = new FlatSVGIcon("images/debug.svg");
                debugL.setIcon(debugI);
            }
        });
        barraIconos.add(debugL);

        //Agregar Icono stop
        Icon stopI = new FlatSVGIcon("images/stop.svg");
        JLabel stopL = new JLabel(stopI);
        stopL.setEnabled(false); //Deshabilitar por defecto
        barraIconos.add(stopL);
        stopL.addMouseListener(new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent me) {
                stopL.isEnabled();//ProgramProcess p = new ProgramProcess();
                //p.debug(); //TODO: CONFIG STOP
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (stopL.isEnabled()) {
                    Icon stopI = new FlatSVGIcon("images/stop2.svg");
                    stopL.setIcon(stopI);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (stopL.isEnabled()) {
                    Icon stopI = new FlatSVGIcon("images/stop.svg");
                    stopL.setIcon(stopI);
                }
            }
        });
        barra.add(barraIconos,BorderLayout.EAST);

        return barra;
    }

    private void initialize(){

        this.setSize(1200,720);
        this.setPreferredSize(new Dimension(1200,720));
        this.setMinimumSize(new Dimension(400,300));

        //Main panel (JPanel)
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //MENÚ
        JMenuBar menuBar = new MenuApp();
        this.setJMenuBar(menuBar);
        this.setTitle("MadIDE");

        //Agregar menú
        panel.add(barraMenu(), BorderLayout.NORTH);

            //Panel central IDE
            panel.add(initializeSplitPanels(),BorderLayout.CENTER);

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    //GETTERS && SETTERS
    public TerminalPanel getJpTerminal() {
        return jpTerminal;
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

    public void updateTreeText() {
            treeText.setText(getActualEditorPanel().getDirection());
    }

}

