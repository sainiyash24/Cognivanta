package cognivanta.cognivantaSite.service;

import cognivanta.cognivantaSite.entity.Admin;
import cognivanta.cognivantaSite.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminSetupService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createAdmin(String username, String rawPassword) {

        Admin admin = new Admin();
        admin.setUsername(username);

        // 🔐 HASH PASSWORD
        admin.setPassword(passwordEncoder.encode(rawPassword));

        adminRepository.save(admin);
    }
}
