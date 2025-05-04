package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UIUtils {
    public static void applyBackground(JFrame frame, JPanel panel, String imageName) {
        URL url = frame.getClass().getResource(imageName);
        if (url != null) {
            Image bg = Toolkit.getDefaultToolkit().getImage(url);
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
            panel.setOpaque(false);
            backgroundPanel.add(panel, BorderLayout.CENTER);
            frame.setContentPane(backgroundPanel);
        } else {
            System.out.println("Không tìm thấy ảnh nền " + imageName);
            frame.setContentPane(panel);
        }
    }
}
