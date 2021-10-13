package com.madirex.util;

import com.madirex.windows.Ventana;
import com.madirex.windows.WindowOpenSave;

import javax.swing.*;

public abstract class Util {
    public static void initializeIcon(Ventana v) {
        ImageIcon img;
        java.net.URL imgURL = Ventana.class.getResource("../../../images/logo.png");

        if (imgURL != null) {
            img = new ImageIcon(imgURL);
            v.setIconImage(img.getImage());
        }
    }

}
