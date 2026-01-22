package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.dto.ApplicationStatusUpdateDTO;
import cognivanta.cognivantaSite.entity.ApplicationStatus;
import cognivanta.cognivantaSite.entity.JobApplication;
import cognivanta.cognivantaSite.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ApplicationService applicationService;

    // ✅ GET ALL APPLICATIONS FOR A JOB
    @GetMapping("/jobs/{jobId}/applications")
    public List<JobApplication> getApplicantsByJob(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJobId(jobId);
    }

    // ✅ UPDATE APPLICATION STATUS
    @PutMapping("/applications/{applicationId}/status")
    public String updateStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationStatusUpdateDTO dto) {

        ApplicationStatus status =
                ApplicationStatus.valueOf(dto.getStatus().toUpperCase());

        applicationService.updateApplicationStatus(applicationId, status);

        return "Application status updated successfully";
    }

    // ✅ DOWNLOAD / VIEW RESUME (PDF) — UPDATED
    @GetMapping("/applications/{applicationId}/resume")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable Long applicationId) {

        Resource resource =
                applicationService.loadResumeAsResource(applicationId);

        String filename = resource.getFilename();

        if (filename == null) {
            filename = "resume.pdf"; // fallback
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\""
                )
                .body(resource);
    }
}
