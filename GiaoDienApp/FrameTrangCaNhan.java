package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameTrangCaNhan extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton lưuButton;
    private JButton hủyButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;


    public FrameTrangCaNhan() {

        URL urlIconNotepad = FrameTrangCaNhan.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }






        // Thiết lập GUI
        setTitle("Trang cá nhân ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1); // Gán giao diện
        setSize(500, 400); // Hoặc pack();
        setLocationRelativeTo(null); // Center
        setVisible(true);

        //
        UIUtils.applyBackground(this, panel1, "background.jpg");
    }

    public static void main(String[] args) {
        new FrameTrangCaNhan();
    }
}
