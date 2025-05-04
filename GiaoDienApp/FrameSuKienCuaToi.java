package GiaoDienApp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameSuKienCuaToi extends JFrame {
    private JPanel panel1;
    private JList list1;
    private JLabel LichSuLabel;
    private JButton TimSuKienButton;

    public FrameSuKienCuaToi() {
        // Load icon
        URL urlIconNotepad = FrameSuKienCuaToi.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập GUI
        setTitle("Trang sự kiện của tôi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Center
        setContentPane(panel1); // Gắn panel1 từ GUI form

        TimSuKienButton.addActionListener(e -> {
            FrameSearchEvent searchFrame = new FrameSearchEvent();
            searchFrame.setVisible(true);
            dispose(); // đóng frame hiện tại
        });


        // Hiển thị frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameSuKienCuaToi();
    }
}
