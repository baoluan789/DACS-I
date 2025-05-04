package gui;

import dao.dangky.dangkiDao;
import dao.ScheduleDao;
import model.Schedule;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ScheduleManagementGUI extends JFrame {
    private int userId;
    private JTable scheduleTable;
    private DefaultTableModel tableModel;
    private JTextField txtFullname;
    private JTextField txtEmail;
    private JTextField txtSdt;
    private JPasswordField txtPassword;
    private ScheduleDao scheduleDao;

    public ScheduleManagementGUI(int userId) {
        this.userId = userId;
        scheduleDao = new ScheduleDao();

        setTitle("Quản lý lịch trình");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Bảng lịch trình
        JLabel lblSchedules = new JLabel("Danh sách lịch trình:");
        lblSchedules.setBounds(20, 20, 150, 25);
        panel.add(lblSchedules);

        String[] columnNames = {"ID", "Tiêu đề", "Địa điểm", "Thời gian bắt đầu", "Thời gian kết thúc"};
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setBounds(20, 50, 740, 200);
        panel.add(scrollPane);

        // Form thay đổi thông tin
        JLabel lblInfo = new JLabel("Thay đổi thông tin cá nhân:");
        lblInfo.setBounds(20, 260, 200, 25);
        panel.add(lblInfo);

        JLabel lblFullname = new JLabel("Họ tên:");
        lblFullname.setBounds(20, 290, 80, 25);
        panel.add(lblFullname);

        txtFullname = new JTextField();
        txtFullname.setBounds(100, 290, 150, 25);
        panel.add(txtFullname);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 320, 80, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 320, 150, 25);
        panel.add(txtEmail);

        JLabel lblSdt = new JLabel("Số ĐT:");
        lblSdt.setBounds(20, 350, 80, 25);
        panel.add(lblSdt);

        txtSdt = new JTextField();
        txtSdt.setBounds(100, 350, 150, 25);
        panel.add(txtSdt);

        JLabel lblPassword = new JLabel("Mật khẩu mới:");
        lblPassword.setBounds(20, 380, 80, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 380, 150, 25);
        panel.add(txtPassword);

        JButton btnUpdate = new JButton("Cập nhật thông tin");
        btnUpdate.setBounds(100, 420, 150, 30);
        panel.add(btnUpdate);

        btnUpdate.addActionListener(e -> updateUserInfo());

        add(panel);

        // Tải danh sách lịch trình khi mở màn hình
        loadSchedules();
    }

    private void loadSchedules() {
        try {
            List<Schedule> schedules = scheduleDao.getAllSchedule(userId);

            tableModel.setRowCount(0);
            for (Schedule schedule : schedules) {
                Object[] row = {
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getLocation(),
                        schedule.getStartTime(),
                        schedule.getEndTime()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải lịch trình: " + ex.getMessage());
        }
    }

    private void updateUserInfo() {
        String fullname = txtFullname.getText();
        String email = txtEmail.getText();
        String sdt = txtSdt.getText();
        String password = new String(txtPassword.getPassword());

        if (fullname.isEmpty() || email.isEmpty() || sdt.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            dangkiDao dangKyDao = new dangkiDao();
            boolean updated = dangKyDao.thayDoiThongTin(userId, fullname, email, sdt, password);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại! Không tìm thấy người dùng.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }
}