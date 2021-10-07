import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    Window(){
        initializeIcon();
        initialize();
    }

    private void initializeIcon() {
        ImageIcon img = new ImageIcon("images/logo.png");

        java.net.URL imgURL = Window.class.getResource("images/logo.png");
        if (imgURL != null) {
            img = new ImageIcon(imgURL);
            this.setIconImage(img.getImage());
        } else {
            JOptionPane.showMessageDialog(this, "Icono de la aplicación no encontrado.");
        }
    }

    private JSplitPane initializesplitPanels() {

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: NAVEGADOR Y EDITOR
        ///////////////////////////
            //NAVEGADOR
            JPanel jpNav = new JPanel();
            jpNav.setLayout(new BorderLayout());
            JTree tree = new JTree();
            jpNav.add(tree);

            //Editor
            JPanel jpEditor = new JPanel();
            jpEditor.setLayout(new BorderLayout());
            JTextArea ta = new JTextArea();
            JScrollPane sp = new JScrollPane(ta);
            jpEditor.add(sp);

            //SPLIT PANEL
            JSplitPane splitPanelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jpNav, jpEditor);
            splitPanelMain.setOneTouchExpandable(true);
            splitPanelMain.setResizeWeight(0.1);

        //TAMAÑO MÍNIMO
        jpNav.setMinimumSize(new Dimension(50, 50));
        jpEditor.setMinimumSize(new Dimension(50, 50));

        ///////////////////////////
        /// AGREGAR SPLIT PANEL: Split (navegador+editor) Y Consola
        ///////////////////////////

        //CONSOLA
        JPanel jpConsola = new JPanel();
        jpConsola.setLayout(new BorderLayout());
        JTextArea ta2 = new JTextArea();
        JScrollPane sp2 = new JScrollPane(ta2);
        jpConsola.add(sp2);

        //SPLIT PANEL
        JSplitPane splitPanels = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPanelMain, jpConsola);
        splitPanels.setOneTouchExpandable(true);
        splitPanels.setResizeWeight(0.8);


        return splitPanels;
    }

    private void initialize(){

        this.setSize(1200,720);
        this.setPreferredSize(new Dimension(1200,720));
        this.setMinimumSize(new Dimension(400,300));

        //JPANEL Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //MENÚ
        JMenuBar menuBar = new Menu();
        this.setJMenuBar(menuBar);
        this.setTitle("MadIDE");

        //Agregar los paneles
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

            //Agregar menú
            panel.add(barra, BorderLayout.NORTH);

            //Panel central IDE
            panel.add(initializesplitPanels(),BorderLayout.CENTER); //Agregar al Main Panel

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}