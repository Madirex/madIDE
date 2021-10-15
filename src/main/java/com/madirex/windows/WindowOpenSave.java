package com.madirex.windows;

import com.madirex.components.EditorPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WindowOpenSave extends JFileChooser {
    Ventana window;
    boolean proyectoModificado = true;

    public WindowOpenSave(Ventana window, boolean confirmSave){
        proyectoModificado = true; //TODO: Configurar detección de archivo modificado ++ agregar caracter * al inicio de los códigos modificados
        this.window = window;

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
        String nombreDir = window.getTabsEditorPanel().getTitleAt(window.getTabsEditorPanel().getSelectedIndex());
        File file = new File(nombreDir);
        existe = file.exists();

        //Guardar
        if (existe){
            try{
                //Guardar directamente
                FileWriter fw = new FileWriter(nombreDir);
                fw.write(window.getActualEditorText().getText());
                fw.flush();
                fw.close();

                //Cambiar title a la ventana
                int index = window.getTabsEditorPanel().getSelectedIndex();
                window.getTabsEditorPanel().setTitleAt(index, file.getPath());
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
        if (this.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
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
            if (inTabs(path) && nuevo) { //Solo ejecutar si es nuevo archivo
                proceder = false;
            }
            else{
                int confirmado = JOptionPane.showConfirmDialog(window, "El archivo ya existe. ¿Deseas sobreescribirlo?");

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

                fw.write(window.getActualEditorText().getText());
                fw.flush();
                fw.close();

                if (nuevo) {
                    //Si es un archivo nuevo (Nueva tab)
                    EditorPanel jpEditor = new EditorPanel(window);
                    window.getTabsEditorPanel().addTab(tab, jpEditor);

                    //Desplazar a la tab abierta
                    window.getTabsEditorPanel().setSelectedIndex(window.getTabsEditorPanel().getTabCount() - 1);
                } else {
                    //Si es un archivo existente (Guardar como)
                    int index = window.getTabsEditorPanel().getSelectedIndex();
                    window.getTabsEditorPanel().setTitleAt(index, tab);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void nuevo(){
        inicializeWindow();
        if (this.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
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

        if (this.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = this.getSelectedFile();
                String path = this.getSelectedFile().getPath();

                //Abrir
                FileReader fr = new FileReader(file);
                EditorPanel jpEditor = new EditorPanel(window);

                if (!inTabs(path)){
                    //Agregar nueva tab
                    window.getTabsEditorPanel().addTab(this.getSelectedFile().getPath(),jpEditor);
                    jpEditor.getEditorText().read(fr,this.getSelectedFile().getPath()); //Introducir texto al archivo

                    //Desplazar a la tab abierta
                    window.getTabsEditorPanel().setSelectedIndex(window.getTabsEditorPanel().getTabCount() - 1);
                }

                //Cerrar
                fr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean inTabs(String path){

        //Chequear si está en tabs
        boolean enTabs = false;

        int tabCounts = window.getTabsEditorPanel().getTabCount();

        for (int i=0; i < tabCounts; i++)
        {
            String tabTitle = window.getTabsEditorPanel().getTitleAt(i);

            if (tabTitle.equals(path)){
                window.getTabsEditorPanel().setSelectedIndex(i);
                enTabs = true;
                break;
            }
        }

        return enTabs;
    }

    public void mensajeConfirmSave(){
        if (proyectoModificado) {
            int confirmado = JOptionPane.showConfirmDialog(window, "¿Guardar cambios?");

            if (JOptionPane.OK_OPTION == confirmado) {
                guardar();
            } else {
                //No guardar los cambios
            }
        }
    }
}
