package cognivanta.cognivantaSite.repository;

import cognivanta.cognivantaSite.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository
        extends JpaRepository<ContactMessage, Long> {
}
