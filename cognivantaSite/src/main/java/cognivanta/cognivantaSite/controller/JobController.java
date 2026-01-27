package cognivanta.cognivantaSite.controller;

import cognivanta.cognivantaSite.entity.Job;
import cognivanta.cognivantaSite.service.JobService;
import cognivanta.cognivantaSite.dto.JobRequestDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // ✅ Get all active jobs (Public)
    @GetMapping
    public List<Job> getJobs() {
        return jobService.getAllActiveJobs();
    }

    // ✅ Create a new job (Admin)
    @PostMapping
    public Job createJob(@Valid @RequestBody JobRequestDTO jobDTO) {

        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setExperience(jobDTO.getExperience());
        job.setActive(jobDTO.isActive());

        return jobService.addJob(job);
    }

    // ✅ Update existing job (Admin)
    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDTO jobDTO) {

        Job job = jobService.getJobById(id);

        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setExperience(jobDTO.getExperience());
        job.setActive(jobDTO.isActive());

        return jobService.addJob(job);
    }

    // ✅ Delete job (Admin)
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }
}
