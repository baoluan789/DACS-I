package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameTrangChu extends JFrame {
    private JPanel panel1;
    private JButton TrangCaNhanButton;
    private JButton SuKienCuaToiButton;
    private JButton themSukienButton;
    private JButton TimSuKienButton;
    private JList list1;
    private JPanel panelMain;


    public FrameTrangChu() {




        // Load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập ảnh nền
        UIUtils.applyBackground(this, panel1, "background.jpg");

        // Thiết lập GUI
        setTitle("Trang chủ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Hoặc pack();
        setLocationRelativeTo(null); // Center
        setVisible(true);

        //


        UIUtils.applyBackground(this, panel1, "background.jpg");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameTrangChu());
    }


}
