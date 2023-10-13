package com.sparta.wantedpreonboardingbackend;

import com.sparta.wantedpreonboardingbackend.entity.Job;
import com.sparta.wantedpreonboardingbackend.repository.JobRepository;
import com.sparta.wantedpreonboardingbackend.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

@SpringBootTest
public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getJobsTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobRepository.findAll()).thenReturn(Collections.singletonList(job));

        List<Job> jobs = jobService.getJobs(null);
        assertEquals(1, jobs.size());
        assertEquals("Software Engineer", jobs.get(0).getPosition());
    }

    @Test
    public void getJobByIdTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        Job foundJob = jobService.getJobById(1L).orElse(null);
        assertEquals("Software Engineer", foundJob.getPosition());
    }

    @Test
    public void saveJobTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job savedJob = jobService.saveJob(job);
        assertEquals("Software Engineer", savedJob.getPosition());
    }

    @Test
    public void deleteJobTest() {
        doNothing().when(jobRepository).deleteById(1L);
        jobService.deleteJob(1L);
        verify(jobRepository, times(1)).deleteById(1L);
    }
}
