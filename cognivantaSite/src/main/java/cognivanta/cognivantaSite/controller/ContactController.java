package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.dto.ContactRequestDTO;
import cognivanta.cognivantaSite.entity.ContactMessage;
import cognivanta.cognivantaSite.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactMessageRepository repository;

    @PostMapping
    public String submitContact(@Valid @RequestBody ContactRequestDTO dto) {

        ContactMessage message = new ContactMessage();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setMessage(dto.getMessage());

        repository.save(message);

        return "Message received successfully";
    }
}
