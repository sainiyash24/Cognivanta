package cognivanta.cognivantaSite.service;

import cognivanta.cognivantaSite.entity.Job;
import cognivanta.cognivantaSite.repository.JobRepository;
import cognivanta.cognivantaSite.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // ✅ Fetch all active jobs (Public)
    public List<Job> getAllActiveJobs() {
        return jobRepository.findByActiveTrue();
    }

    // ✅ Create or Update job
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    // ✅ Get job by ID (used for update & delete)
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));
    }

    // ✅ Delete job
    public void deleteJob(Long id) {
        Job job = getJobById(id);
        jobRepository.delete(job);
    }
}
