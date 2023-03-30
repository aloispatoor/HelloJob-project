package com.api.hellojob.repository;

import com.api.hellojob.entity.JobSeeker;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
}
