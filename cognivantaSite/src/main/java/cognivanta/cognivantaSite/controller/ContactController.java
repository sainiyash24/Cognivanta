package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.dto.ContactRequestDTO;
import cognivanta.cognivantaSite.entity.ContactMessage;
import cognivanta.cognivantaSite.repository.ContactMessageRepository;
import cognivanta.cognivantaSite.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactMessageRepository repository;
    @Autowired
    private EmailService emailService;

    @PostMapping
public String submitContact(@Valid @RequestBody ContactRequestDTO dto) {

    ContactMessage message = new ContactMessage();
    message.setName(dto.getName());
    message.setEmail(dto.getEmail());
    message.setMessage(dto.getMessage());

    repository.save(message);
    System.out.println("✅ Message saved to DB");

    try {
        // 🔔 Admin notification
        emailService.sendContactNotification(
                dto.getName(),
                dto.getEmail(),
                dto.getMessage()
        );

        // 📩 User confirmation
        emailService.sendUserConfirmation(
                dto.getEmail(),
                dto.getName()
        );

        System.out.println("✅ Admin + user emails sent");

    } catch (Exception e) {
        System.out.println("❌ Email sending failed");
        e.printStackTrace();
    }

    return "Message received successfully";
}



}
