package com.madirex.util;

import com.madirex.windows.Ventana;
import com.madirex.windows.WindowOpenSave;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public abstract class Util {
    public static void initializeIcon(Ventana v) {
        ImageIcon img;
        java.net.URL imgURL = Ventana.class.getResource("../../../images/logo.png");

        if (imgURL != null) {
            img = new ImageIcon(imgURL);
            v.setIconImage(img.getImage());
        }
    }
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

