import javax.swing.*;

public abstract class Utils {
    public static void initializeIcon(JFrame frame) {
        ImageIcon img = new ImageIcon("images/logo.png");

        java.net.URL imgURL = Window.class.getResource("images/logo.png");
        if (imgURL != null) {
            img = new ImageIcon(imgURL);
            frame.setIconImage(img.getImage());
        }
    }
}
