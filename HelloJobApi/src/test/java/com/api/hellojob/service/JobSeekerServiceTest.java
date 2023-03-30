package com.api.hellojob.service;

import com.api.hellojob.entity.JobSeeker;
import com.api.hellojob.repository.JobSeekerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JobSeekerServiceTest {
    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Test
    public void save_NominalCase(){
        JobSeeker candidate = new JobSeeker();
        candidate.setEmail("candidate@test.fr");
        candidate.setPassword("123test123");
        candidate.setFirstName("Gerard");
        candidate.setLastName("Test");
        candidate.setTitle("Testeur de test");
        candidate.setAvailable(false);
        jobSeekerRepository.save(candidate);
        System.out.println("Test save end");
    }

    @Test
    public void find_NominalCase(){
        JobSeeker candidate = jobSeekerRepository.findById(1L).get();
        assertThat(candidate.getFirstName()).as("Candidate not found").isEqualTo("Gerard");
    }

    @Test
    public void find_allCases(){
        List<JobSeeker> candidates = jobSeekerRepository.findAll();
        assertThat(candidates).as("Not all candidates were found").hasSize(1);
        candidates.forEach(System.out::println);
    }
}
