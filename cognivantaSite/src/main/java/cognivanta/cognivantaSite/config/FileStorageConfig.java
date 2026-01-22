package cognivanta.cognivantaSite.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileStorageConfig {

    public static final String RESUME_UPLOAD_DIR =
            System.getProperty("user.home") + "/cognivanta_uploads/resumes";

    @PostConstruct
    public void init() {
        File dir = new File(RESUME_UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
