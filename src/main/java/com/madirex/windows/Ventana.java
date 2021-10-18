package com.madirex.windows;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.madirex.components.EditorPanel;
import com.madirex.components.EditorText;
import com.madirex.components.TerminalPanel;
import com.madirex.components.menu.MenuApp;
import com.madirex.util.ProgramProcess;
import com.madirex.util.Utils;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;


public class Ventana extends JFrame {

    private JPanel jpNav; //Navegación
    private JSplitPane splitPanelMain;
    private TerminalPanel jpTerminal;  //Terminal
    private JSplitPane splitPanels;
    private JTabbedPane tabsEditorPanel; //TABS
    private JTextArea treeText;

    //Atributos estáticos
    private static JTree tree = new JTree();
    private static String treePath = System.getProperty("user.dir");

    ///GETTERS && SETTERS

    public static JTree getTree() {
        return tree;
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
            return (EditorText) ((EditorPanel) c).getEditorText();//((EditorPanel) c).getEditorText();
        }
        else{
            //Si no existe ningún archivo, devolver null
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

    private JSplitPane initializesplitPanels() {

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: NAVEGADOR Y EDITOR
        ///////////////////////////
            //NAVEGADOR
            jpNav = new JPanel();
            jpNav.setLayout(new BorderLayout());

            //JTREE INICIALIZAR
            DefaultMutableTreeNode nodo = Utils.treeCreation(treePath);
            DefaultTreeModel modelo =  new DefaultTreeModel(nodo);
            actualizarTree();

            //Agregar listener y acciones al tree
            tree.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {

                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                        //CREAR LA DIRECCIÓN
                        String dir = "";
                        DefaultMutableTreeNode actualNode = node;

                        while (actualNode.getParent() != null){
                            dir = actualNode.toString() + File.separator + dir; //Agregar a dirección
                            actualNode = (DefaultMutableTreeNode) actualNode.getParent(); //Poner el siguiente parent
                        }

                        //Eliminar el file separator del final para que quede una dirección correcta
                        if (dir != ""){
                            dir = dir.substring(0, dir.length() - 1);
                        }

                        dir = treePath + File.separator + dir; //Crear dirección inicial del Tree

                        File file = new File(dir);

                        if (!file.isDirectory()){
                            Utils.abrirArchivo(file,dir);
                        }

                    }
                }
            });

            jpNav.add(tree);

            //Panel editor
            tabsEditorPanel = new JTabbedPane();
            tabsEditorPanel.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    treeText.setText(getActualEditorText().getDirection());
                }
            });

            //TODO: TEMP *
            EditorPanel jpEditor = new EditorPanel();
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
        /// AGREGAR SPLIT PANEL: Split (navegador+editor) Y Terminal
        ///////////////////////////

        //TERMINAL
        jpTerminal = new TerminalPanel();

        //SPLIT PANEL
        splitPanels = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPanelMain,
                jpTerminal);
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
        treeText = new JTextArea();
        //treeText.setText(this.getActualEditorText().getDirection());
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
                p.run();
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
                if (stopL.isEnabled()) {
                    //ProgramProcess p = new ProgramProcess();
                    //p.debug(); //TODO: CONFIG STOP
                }
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

        //JPANEL Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //MENÚ
        JMenuBar menuBar = new MenuApp();
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

}

