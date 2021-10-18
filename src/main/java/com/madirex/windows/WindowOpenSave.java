package com.madirex.windows;

import com.madirex.components.EditorPanel;
import com.madirex.components.TerminalPanel;
import com.madirex.util.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WindowOpenSave extends JFileChooser {

    public WindowOpenSave(boolean confirmSave){
        //Guardar proyecto si ha sido modificado
        if (confirmSave) {
            mensajeConfirmSave();
        }
    }

    private void inicializeWindow() {
        this.setCurrentDirectory(new File(System.getProperty("user.home")));

        //Agregar filtro java
        FileNameExtensionFilter filterJava = new FileNameExtensionFilter(".java", "java");
        this.setFileFilter(filterJava);
    }

    public void guardar(){
        boolean existe = false;

        //Revisar si existe
        String nombreDir = Utils.ventana.getTabsEditorPanel().getTitleAt(Utils.ventana.getTabsEditorPanel().getSelectedIndex());
        File file = new File(nombreDir);
        existe = file.exists();

        //Guardar
        if (existe){
            try{
                //Guardar directamente
                FileWriter fw = new FileWriter(nombreDir);
                fw.write(Utils.ventana.getActualEditorText().getText());
                fw.flush();
                fw.close();

                //Cambiar title a la ventana
                int index = Utils.ventana.getTabsEditorPanel().getSelectedIndex();
                Utils.ventana.getTabsEditorPanel().setTitleAt(index, file.getPath());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            guardarComo();
        }
    }

    public void guardarComo(){
        inicializeWindow();
        if (this.showSaveDialog(Utils.ventana) == JFileChooser.APPROVE_OPTION) {
            try {
                crearArchivo(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean procederArchivoExistente(boolean nuevo){
        boolean proceder = true;
        String path = this.getSelectedFile().getPath();

        //Detectar si el archivo ya existe
        if (this.getSelectedFile().exists()){

            //Detectar si el archivo ya está abierto en una tab
            if (Utils.fileInTabs(path) && nuevo) { //Solo ejecutar si es nuevo archivo
                proceder = false;
            }
            else{
                int confirmado = JOptionPane.showConfirmDialog(Utils.ventana, "El archivo ya existe. ¿Deseas sobreescribirlo?");

                if (JOptionPane.OK_OPTION == confirmado) {
                } else {
                    proceder = false;
                }
            }
        }
        return proceder;
    }

    private void crearArchivo(boolean nuevo) {
        try{
            if (procederArchivoExistente(nuevo)) {
                //Asignar formato si no lo tiene
                File file = this.getSelectedFile();
                String formato = this.getFileFilter().getDescription();

                FileWriter fw;
                String tab = this.getSelectedFile().getPath();

                //Agregar formato si no lo tiene
                if (!tieneFormato(file.getName(), formato)) {
                    fw = new FileWriter(file + formato);
                    tab += formato;
                } else {
                    fw = new FileWriter(file);
                }

                fw.write(Utils.ventana.getActualEditorText().getText());
                fw.flush();
                fw.close();

                if (nuevo) {
                    //Si es un archivo nuevo (Nueva tab)
                    EditorPanel jpEditor = new EditorPanel();
                    Utils.ventana.getTabsEditorPanel().addTab(tab, jpEditor);

                    //Desplazar a la tab abierta
                    Utils.ventana.getTabsEditorPanel().setSelectedIndex(Utils.ventana.getTabsEditorPanel().getTabCount() - 1);
                } else {
                    //Si es un archivo existente (Guardar como)
                    int index = Utils.ventana.getTabsEditorPanel().getSelectedIndex();
                    Utils.ventana.getTabsEditorPanel().setTitleAt(index, tab);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void nuevo(){
        inicializeWindow();
        if (this.showSaveDialog(Utils.ventana) == JFileChooser.APPROVE_OPTION) {
            try {
                crearArchivo(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean tieneFormato(String nombre, String formato){

        boolean tieneFormato = false;

        if (nombre.endsWith(formato)){
            return true;
        }

        return tieneFormato;
    }


    public void abrir() {
        inicializeWindow();

        if (this.showOpenDialog(Utils.ventana) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = this.getSelectedFile();
                String dir = this.getSelectedFile().getPath();

                Utils.abrirArchivo(file, dir);
                Ventana.setTreePath(this.getSelectedFile().getParentFile().getPath()); //Cambiar ubicación del tree
                Ventana.actualizarTree();


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void mensajeConfirmSave(){

        boolean proyectoModificado = Utils.ventana.getActualEditorText().isArchivoModificado();

        if (proyectoModificado) {
            int confirmado = JOptionPane.showConfirmDialog(Utils.ventana, "¿Guardar cambios?");

            if (JOptionPane.OK_OPTION == confirmado) {
                guardar();
            } else {
                //No guardar los cambios
            }
        }
    }
}
