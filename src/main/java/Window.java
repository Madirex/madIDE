import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

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
            barra.setLayout(new FlowLayout());
            panel.add(barra, BorderLayout.NORTH);

            //Panel central IDE
            panel.add(initializesplitPanels(),BorderLayout.CENTER); //Agregar al Main Panel

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}