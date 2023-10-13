package com.sparta.wantedpreonboardingbackend;

import com.sparta.wantedpreonboardingbackend.controller.JobController;
import com.sparta.wantedpreonboardingbackend.entity.Job;
import com.sparta.wantedpreonboardingbackend.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobControllerTest {

    @InjectMocks
    private JobController jobController;

    @MockBean
    private JobService jobService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getJobsTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobService.getJobs(null)).thenReturn(Collections.singletonList(job));

        List<Job> jobs = jobController.getJobs(null);
        assertEquals(1, jobs.size());
        assertEquals("Software Engineer", jobs.get(0).getPosition());
    }

    @Test
    public void getJobByIdTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobService.getJobById(1L)).thenReturn(Optional.of(job));

        Job foundJob = jobController.getJobById(1L);
        assertEquals("Software Engineer", foundJob.getPosition());
    }

    @Test
    public void createJobTest() {
        Job job = new Job();
        job.setPosition("Software Engineer");
        when(jobService.saveJob(any(Job.class))).thenReturn(job);

        Job createdJob = jobController.createJob(job);
        assertEquals("Software Engineer", createdJob.getPosition());
    }

    @Test
    public void updateJobTest() {
        Job job = new Job();
        job.setPosition("Software Engineer Updated");
        when(jobService.getJobById(1L)).thenReturn(Optional.of(new Job()));
        when(jobService.saveJob(any(Job.class))).thenReturn(job);

        Job updatedJob = jobController.updateJob(1L, job);
        assertEquals("Software Engineer Updated", updatedJob.getPosition());
    }

    @Test
    public void deleteJobTest() {
        doNothing().when(jobService).deleteJob(1L);
        jobController.deleteJob(1L);
        verify(jobService, times(1)).deleteJob(1L);
    }

    @Test
    public void jobNotFoundExceptionTest() {
        when(jobService.getJobById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> jobController.getJobById(1L));
    }


}
