package com.madirex.util;

import com.madirex.components.EditorPanel;
import com.madirex.windows.Ventana;
import com.madirex.windows.WindowOpenSave;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;

public abstract class Utils {
    public static Ventana ventana;
    public static DefaultMutableTreeNode tempTreeCreation;

    public static void initializeIcon(Ventana window) {
        ImageIcon img;
        String imgURL = "src/main/resources/images/logo.png";
        /*String imgURL = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "images" + File.separator + "logo.png";*/
        if (imgURL != null) {
            img = new ImageIcon(imgURL);
            window.setIconImage(img.getImage());
        }
    }

    public static DefaultMutableTreeNode treeCreation(String path) {
        DefaultMutableTreeNode tree = new DefaultMutableTreeNode(new File(path).getName());
        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {

                //Carpeta vacía (no permitir children)
                if(file.isDirectory()) {
                    DefaultMutableTreeNode dn=new DefaultMutableTreeNode(file.getName(), false);
                    return dn;
                }

            }else{
                for (File file2 : files) {

                    //Coger directorios
                    if (file2.isDirectory()) {
                        tree.add(treeCreation(file2.getAbsolutePath()));
                    }

                    //Coger el resto de archivos
                    else{
                        tempTreeCreation=new DefaultMutableTreeNode(file2.getName());
                        tree.add(tempTreeCreation);
                    }
                }
            }
        } else {//el archivo no existe
            return null;
        }
        return tree;
    }

    public static void window() {
    }

    /**
     * ABRE ARCHIVOS
     * @param file
     * @param dir
     */
    public static void abrirArchivo(File file, String dir) {
        try {

            //Abrir
            FileReader fr = new FileReader(file);
            EditorPanel jpEditor = new EditorPanel();

            if (!fileInTabs(dir)) {
                //Agregar nueva tab
                Utils.ventana.getTabsEditorPanel().addTab(dir, jpEditor);
                jpEditor.getEditorText().read(fr, dir); //Introducir texto al archivo

                //Desplazar a la tab abierta
                Utils.ventana.getTabsEditorPanel().setSelectedIndex(Utils.ventana.getTabsEditorPanel().getTabCount() - 1);
            }
            //Cerrar
            fr.close();

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * Comprueba si el archivo se encuentra en algún tab
     * @param path
     * @return
     */
    public static boolean fileInTabs(String path){

        //Chequear si está en tabs
        boolean enTabs = false;

        int tabCounts = Utils.ventana.getTabsEditorPanel().getTabCount();

        for (int i=0; i < tabCounts; i++)
        {
            String tabTitle = Utils.ventana.getTabsEditorPanel().getTitleAt(i);

            if (tabTitle.equals(path)){
                Utils.ventana.getTabsEditorPanel().setSelectedIndex(i);
                enTabs = true;
                break;
            }
        }

        return enTabs;
    }

    /**
     * OTRO
     */
    //EDITOR DOCUMENT
    /*@Data
    public static class EditorDocument {
        static private StyleContext cont;
        static private AttributeSet attr;
        static private AttributeSet attrTitle;
        static private DefaultStyledDocument doc;

        public EditorDocument(){
            refrescarDoc(Color.WHITE);
        }

        public static DefaultStyledDocument getDoc(){
            return doc;
        }

        public static void refrescarDoc(Color titleColor){
            //Crear editor
            cont = StyleContext.getDefaultStyleContext();
            attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.ORANGE);
            attrTitle = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, titleColor);

            //Crear estilo de documento (palabras clave coloreadas)
            doc = new DefaultStyledDocument() {

                public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                    super.insertString(offset, str, a);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offset);
                    if (before < 0) before = 0;
                    int after = findFirstNonWordChar(text, offset + str.length());
                    int wordL = before;
                    int wordR = before;

                    while (wordR <= after) {
                        if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                            if (text.substring(wordL, wordR).matches("(\\W)*(private|public|protected|if|else|while|false|true)"))
                                setCharacterAttributes(wordL, wordR - wordL, attr, false);
                            else {
                                setCharacterAttributes(wordL, wordR - wordL, attrTitle, false);
                            }
                            wordL = wordR;
                        }
                        wordR++;
                    }
                }

                public void updateString(int offset, String str, AttributeSet a) throws BadLocationException{

                }

                public void remove (int offs, int len) throws BadLocationException {
                    super.remove(offs, len);

                    String text = getText(0, getLength());
                    int before = findLastNonWordChar(text, offs);
                    if (before < 0) before = 0;
                    int after = findFirstNonWordChar(text, offs);

                    if (text.substring(before, after).matches("(\\W)*(private|public|protected)")) {
                        setCharacterAttributes(before, after - before, attr, false);
                    } else {
                        setCharacterAttributes(before, after - before, attrTitle, false);
                    }
                }


            };
        }

        //Métodos necesarios para el correcto funcionamiento de la
        //asignación de color a ciertas palabras
        private static int findLastNonWordChar(String text, int index) {
            while (--index >= 0) {
                if (String.valueOf(text.charAt(index)).matches("\\W")) {
                    break;
                }
            }
            return index;
        }

        private static int findFirstNonWordChar(String text, int index) {
            while (index < text.length()) {
                if (String.valueOf(text.charAt(index)).matches("\\W")) {
                    break;
                }
                index++;
            }
            return index;
        }



    }*/
}

