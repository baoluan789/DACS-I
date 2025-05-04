package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameLogin extends JFrame {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JCheckBox NhoMatKhauCheckBox;
    private JRadioButton userRadioButton;
    private JButton QuenMatKhauButton;
    private JButton DangNhapButton;
    private JButton DangKyButton;
    private JPanel panel2;

    public FrameLogin() {
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
        setTitle("Trang Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Center
        setVisible(true);
        //


    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}
