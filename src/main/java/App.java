import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.io.File;

public class App {
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Look And Feel
                FlatLightLaf.setup();

                //SETUP
                try {
                    UIManager.setLookAndFeel( new FlatDarculaLaf() );
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                UIManager.put( "CheckBox.icon.style", "filled" );

                //Crear Window
                Window w = new Window();
            }
        });
    }
}
