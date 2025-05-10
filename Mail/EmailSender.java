package dao.Mail;

import dao.DatabaseConnection;

import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    private final Session mailSession;

    public EmailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Lấy thông tin từ biến môi trường
        String email = System.getenv("EMAIL_USERNAME");
        String password = System.getenv("EMAIL_PASSWORD");

        mailSession = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }

    public void sendEmailsForTime(LocalDateTime currentTime) {
        List<String> emailRecipients = getEmailsFromDatabase(currentTime);
        if (emailRecipients.isEmpty()) {
            System.out.println("No emails to send for time: " + currentTime);
            return;
        }

        String emailSubject = "Schedule Notification";
        String emailBody = "Reminder: Your scheduled event is starting soon!";

        try {
            MimeMessage mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(System.getenv("EMAIL_USERNAME")));
            for (String recipient : emailRecipients) {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            mimeMessage.setSubject(emailSubject);
            mimeMessage.setText(emailBody);
            Transport.send(mimeMessage);
            System.out.println("Email sent to: " + emailRecipients);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private List<String> getEmailsFromDatabase(LocalDateTime currentTime) {
        List<String> emailList = new ArrayList<>();
        String sql = "SELECT emailNotification FROM Schedule1 WHERE numberOfOccurrences > 0 AND " +
                "DATEADD(MINUTE, -remindBefore, startTime) BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(1, currentTime.minusMinutes(5).format(formatter));
            stmt.setString(2, currentTime.plusMinutes(5).format(formatter));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    emailList.add(rs.getString("emailNotification"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emailList;
    }
}