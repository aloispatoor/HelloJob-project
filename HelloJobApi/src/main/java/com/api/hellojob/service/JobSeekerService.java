package com.api.hellojob.service;

import com.api.hellojob.entity.JobSeeker;
import com.api.hellojob.entity.Offer;
import com.api.hellojob.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<JobSeeker> getAllCandidates(){
        return jobSeekerRepository.findAll();
    }

    public Optional<JobSeeker> getCandidateById(Long id){
        return jobSeekerRepository.findById(id);
    }

    public JobSeeker addCandidate(JobSeeker candidate){
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        return jobSeekerRepository.save(candidate);
    }

    public void deleteCandidate(Long id){
        jobSeekerRepository.findById(id).orElse(null);
        jobSeekerRepository.deleteById(id);
        System.out.println("Candidate " + id + " deleted successfully");
    }

    public JobSeeker updateCandidate(JobSeeker candidate){
        JobSeeker newCandidate = jobSeekerRepository.findById(candidate.getId()).orElse(null);
        if(newCandidate != null){
            newCandidate.setEmail(candidate.getEmail());
            newCandidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
            newCandidate.setFirstName(candidate.getFirstName());
            newCandidate.setLastName(candidate.getLastName());
            newCandidate.setAvailable(candidate.getAvailable());
            newCandidate.setContractTypes(candidate.getContractTypes());
            newCandidate.setTitle(candidate.getTitle());
            newCandidate.setCv(candidate.getCv());
            jobSeekerRepository.save(newCandidate);
        }
        return newCandidate;
    }

//    RELATIONSHIP BETWEEN CANDIDATES AND OFFERS
    public void candidateApplication(Long id, List<Offer> offers){
        JobSeeker candidate = jobSeekerRepository.findById(id).get();
        candidate.setApplications(offers);
        jobSeekerRepository.save(candidate);
        System.out.println("Application made by " + candidate.getFirstName());
    }

    public List<Offer> getAllCandidateApplication(Long id){
        return jobSeekerRepository.findById(id).get().getApplications();
    }
}
