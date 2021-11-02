package com.madirex.util;

import com.madirex.components.EditorPanel;
import com.madirex.View.Ventana;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Utils {
    public static Ventana ventana;
    public static DefaultMutableTreeNode tempTreeCreation;

    public static void initializeIcon(Ventana window) {
        ImageIcon img;
        String imgURL = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "images" + File.separator + "logo.png";
        img = new ImageIcon(imgURL);
        window.setIconImage(img.getImage());
    }

    public static DefaultMutableTreeNode treeCreation(String path) {
        DefaultMutableTreeNode tree = new DefaultMutableTreeNode(new File(path).getName());
        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            assert files != null;
            if (files.length == 0) {

                //Carpeta vacía (no permitir children)
                if(file.isDirectory()) {
                    return new DefaultMutableTreeNode(file.getName(), false);
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
        } else { //el archivo no existe
            JOptionPane.showMessageDialog(Utils.ventana
                    , "Error: El archivo no existe."
                    , "Archivo inexistente", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return tree;
    }

    /**
     * ABRE ARCHIVOS
     * @param file {@link File}
     * @param dir {@link String}
     */
    public static void abrirArchivo(File file, String dir) {

        try {
            //Abrir
            FileReader fr = new FileReader(file);
            EditorPanel jpEditor = new EditorPanel(dir);

            if (!fileInTabs(dir)) {
                //Agregar nueva tab
                Utils.ventana.getTabsEditorPanel().addTab(file.getName(), jpEditor);
                jpEditor.getEDITOR_TEXT().read(fr, dir); //Introducir texto al archivo
                jpEditor.actualizarUndo();

                //Desplazar a la tab abierta
                Utils.ventana.getTabsEditorPanel().setSelectedIndex(Utils.ventana.getTabsEditorPanel().getTabCount() - 1);
            }
            //Cerrar
            fr.close();
            } catch (
            IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * Comprueba si el archivo se encuentra en algún tab
     * @param path {@link String}
     * @return (boolean) ¿Está en tabs?
     */
    public static boolean fileInTabs(String path){

        //Chequear si está en tabs
        boolean enTabs = false;

        int tabCounts = Utils.ventana.getTabsEditorPanel().getTabCount();

        for (int i=0; i < tabCounts; i++)
        {
            if (Utils.ventana.getTabsEditorPanel().getComponentAt(i) instanceof EditorPanel) {
                EditorPanel editor = (EditorPanel) Utils.ventana.getTabsEditorPanel().getComponentAt(i);

                String nombreDir = editor.getDirection();

                if (nombreDir.equals(path)){
                    Utils.ventana.getTabsEditorPanel().setSelectedIndex(i);
                    enTabs = true;
                    break;
                }
            }else{
                JOptionPane.showMessageDialog(Utils.ventana
                        , "No se ha localizado el directorio."
                        , "Fallo de localización", JOptionPane.ERROR_MESSAGE);
                }
}
        return enTabs;
    }
}