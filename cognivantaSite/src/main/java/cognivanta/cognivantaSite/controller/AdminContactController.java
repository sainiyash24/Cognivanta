package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.entity.ContactMessage;
import cognivanta.cognivantaSite.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contact-messages")
public class AdminContactController {

    @Autowired
    private ContactMessageRepository repository;

    // GET ALL
    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }

    // MARK AS READ
    @PutMapping("/{id}/read")
    public void markAsRead(@PathVariable Long id) {
        ContactMessage msg = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        msg.setRead(true);
        repository.save(msg);
    }

    // DELETE MESSAGE
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

