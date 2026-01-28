package cognivanta.cognivantaSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 📩 Admin notification (existing / optional)
    public void sendContactNotification(String name, String email, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("ys972976@gmail.com");   // admin email
        mail.setFrom("ys972976@gmail.com");
        mail.setSubject("New Contact Message Received");

        mail.setText(
                "New contact message:\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n\n" +
                "Message:\n" + message
        );

        mailSender.send(mail);
    }

    // ✅ User confirmation email (NEW)
    public void sendUserConfirmation(String userEmail, String userName) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(userEmail);             // user email
        mail.setFrom("ys972976@gmail.com"); // MUST match Gmail config
        mail.setSubject("Thanks for contacting Cognivanta");

        mail.setText(
                "Hi " + userName + ",\n\n" +
                "Thank you for reaching out to Cognivanta.\n\n" +
                "We have received your message and our team will get back to you shortly.\n\n" +
                "Regards,\n" +
                "Cognivanta Team"
        );

        mailSender.send(mail);
    }
}
