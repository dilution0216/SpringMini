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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
}
