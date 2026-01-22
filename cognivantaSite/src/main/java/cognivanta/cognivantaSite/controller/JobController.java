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

    @GetMapping
    public List<Job> getJobs() {
        return jobService.getAllActiveJobs();
    }

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
}
