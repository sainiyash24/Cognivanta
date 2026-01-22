package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // ✅ APPLY FOR JOB WITH RESUME (PDF upload)
    @PostMapping(
            value = "/{jobId}/upload",
            consumes = "multipart/form-data"
    )
    public String applyWithResume(
            @PathVariable Long jobId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam("resume") MultipartFile resume) throws Exception {

        applicationService.applyForJobWithResume(
                jobId,
                name,
                email,
                resume
        );

        return "Application submitted successfully with resume";
    }
}
