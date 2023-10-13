package com.sparta.wantedpreonboardingbackend.repository;

import com.sparta.wantedpreonboardingbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompany_NameContaining(String companyName);
}