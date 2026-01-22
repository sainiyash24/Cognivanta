package cognivanta.cognivantaSite.service;

import cognivanta.cognivantaSite.config.FileStorageConfig;
import cognivanta.cognivantaSite.entity.ApplicationStatus;
import cognivanta.cognivantaSite.entity.Job;
import cognivanta.cognivantaSite.entity.JobApplication;
import cognivanta.cognivantaSite.exception.ResourceNotFoundException;
import cognivanta.cognivantaSite.repository.JobApplicationRepository;
import cognivanta.cognivantaSite.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    // ✅ GET applications for a specific job (Admin use-case)
    public List<JobApplication> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // ✅ APPLY for job WITH resume upload
    public void applyForJobWithResume(
            Long jobId,
            String name,
            String email,
            MultipartFile resumeFile) throws IOException {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        // 🔴 VALIDATE FILE
        if (resumeFile == null || resumeFile.isEmpty()) {
            throw new RuntimeException("Resume file is required");
        }

        // ✅ NULL-SAFE filename check (PREVENT 500)
        String originalFilename = resumeFile.getOriginalFilename();

        if (originalFilename == null ||
                !originalFilename.toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException("Only PDF resumes are allowed");
        }

        // 🔴 GENERATE SAFE FILE NAME
        String fileName =
                UUID.randomUUID() + "_" + originalFilename;

        String filePath =
                FileStorageConfig.RESUME_UPLOAD_DIR + "/" + fileName;

        // 🔴 ENSURE DIRECTORY EXISTS
        File uploadDir = new File(FileStorageConfig.RESUME_UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 🔴 SAVE FILE TO DISK
        resumeFile.transferTo(new File(filePath));

        // 🔴 CREATE APPLICATION
        JobApplication application = new JobApplication();
        application.setName(name);
        application.setEmail(email);
        application.setResumePath(filePath);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        applicationRepository.save(application);
    }

    // ✅ UPDATE application status (Admin use-case)
    public void updateApplicationStatus(
            Long applicationId,
            ApplicationStatus status) {

        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        application.setStatus(status);
        applicationRepository.save(application);
    }
}
