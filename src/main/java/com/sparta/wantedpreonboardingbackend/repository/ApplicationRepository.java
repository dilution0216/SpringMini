package com.sparta.wantedpreonboardingbackend.repository;

import com.sparta.wantedpreonboardingbackend.entity.Application;
import com.sparta.wantedpreonboardingbackend.entity.Job;
import com.sparta.wantedpreonboardingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserAndJob(User user, Job job);
}
