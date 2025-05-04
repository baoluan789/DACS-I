package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameDangky extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox DongYCheckBox;
    private JButton DangKyButton;
    private JButton HuyButton;

    public FrameDangky() {
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
        setTitle("Trang Đăng Ký");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center
        setVisible(true);

    }

    public static void main(String[] args) {
        new FrameDangky();
    }
}
