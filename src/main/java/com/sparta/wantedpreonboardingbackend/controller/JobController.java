package com.sparta.wantedpreonboardingbackend.controller;

import com.sparta.wantedpreonboardingbackend.entity.Application;
import com.sparta.wantedpreonboardingbackend.entity.Job;
import com.sparta.wantedpreonboardingbackend.entity.User;
import com.sparta.wantedpreonboardingbackend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    @GetMapping
    public List<Job> getJobs(@RequestParam(required = false) String search) {
        return jobService.getJobs(search);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .orElseThrow(() -> new RuntimeException("채용 공고를 찾을 수 없습니다"));
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobService.getJobById(id).map(job -> {
            job.setPosition(updatedJob.getPosition());
            job.setReward(updatedJob.getReward());
            job.setDescription(updatedJob.getDescription());
            job.setTechnology(updatedJob.getTechnology());
            return jobService.saveJob(job);
        }).orElseThrow(() -> new RuntimeException("채용 공고를 찾을 수 없습니다"));
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }

    @PostMapping("/{id}/apply")
    public Application applyToJob(@PathVariable Long id, @RequestBody User user) {
        Job job = jobService.getJobById(id)
                .orElseThrow(() -> new RuntimeException("채용 공고를 찾을 수 없습니다"));
        return jobService.applyToJob(user, job);
    }
}