package com.sparta.wantedpreonboardingbackend.service;

import com.sparta.wantedpreonboardingbackend.entity.Application;
import com.sparta.wantedpreonboardingbackend.entity.Job;
import com.sparta.wantedpreonboardingbackend.entity.User;
import com.sparta.wantedpreonboardingbackend.repository.ApplicationRepository;
import com.sparta.wantedpreonboardingbackend.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobService {
    private JobRepository jobRepository;
    private ApplicationRepository applicationRepository;

    public List<Job> getJobs(String search) {
        if (search != null) {
            return jobRepository.findByCompany_NameContaining(search);
        }
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public Application applyToJob(User user, Job job) {
        Optional<Application> existingApplication = applicationRepository.findByUserAndJob(user, job);
        if(existingApplication.isPresent()) {
            throw new RuntimeException("이미 지원한 회사 입니다.");
        }
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        return applicationRepository.save(application);
    }


}