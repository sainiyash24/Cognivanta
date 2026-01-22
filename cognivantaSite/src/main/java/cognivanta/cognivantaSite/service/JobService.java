package cognivanta.cognivantaSite.service;

import cognivanta.cognivantaSite.entity.Job;
import cognivanta.cognivantaSite.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllActiveJobs() {
        return jobRepository.findByActiveTrue();
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }
}
