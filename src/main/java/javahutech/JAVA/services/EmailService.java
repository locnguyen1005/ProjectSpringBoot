package javahutech.JAVA.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body, String name, String phone) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Họ tên: " + name + "\n" + "Điện thoại: " + phone + "\n" + "Email: " + to + "\n" + "Về việc: Phản hồi về phim\n" + "Tiêu đề: " + subject + "\n" + "Nội dung: " + body);

        mailSender.send(message);
    }

}