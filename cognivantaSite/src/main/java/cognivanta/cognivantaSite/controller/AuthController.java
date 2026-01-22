package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.entity.Admin;
import cognivanta.cognivantaSite.repository.AdminRepository;
import cognivanta.cognivantaSite.security.JwtUtil;
import cognivanta.cognivantaSite.dto.LoginRequestDTO;
import cognivanta.cognivantaSite.exception.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, String> login(
            @Valid @RequestBody LoginRequestDTO loginDTO) {

        Admin dbAdmin = adminRepository
                .findByUsername(loginDTO.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid username or password"));

        if (!passwordEncoder.matches(
                loginDTO.getPassword(),
                dbAdmin.getPassword())) {

            throw new UnauthorizedException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(dbAdmin.getUsername());
        return Map.of("token", token);
    }
}
