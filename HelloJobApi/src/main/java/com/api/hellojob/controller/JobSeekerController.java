package com.api.hellojob.controller;

import com.api.hellojob.entity.JobSeeker;
import com.api.hellojob.entity.Offer;
import com.api.hellojob.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/candidates")
public class JobSeekerController {
    @Autowired
    private JobSeekerService jobSeekerService;

//    METHODS GET
    @GetMapping("")
    public List<JobSeeker> getAllCandidates(){
        return jobSeekerService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getCandidateById(@PathVariable Long id){
        return jobSeekerService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/applications")
    public List<Offer> getAllCandidateApplications(@PathVariable Long id){
        return jobSeekerService.getAllCandidateApplication(id);
    }

//    METHODS POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JobSeeker registerCandidate(@RequestBody JobSeeker candidate){
        return jobSeekerService.addCandidate(candidate);
    }

//    METHOD PUT
    @PutMapping("/edit/{id}")
    public ResponseEntity<JobSeeker> updateCompany(@PathVariable Long id, @RequestBody JobSeeker candidate){
        return jobSeekerService.getCandidateById(id)
                .map(savedCandidate -> {
                    savedCandidate.setEmail(candidate.getEmail());
                    savedCandidate.setPassword(candidate.getPassword());
                    savedCandidate.setFirstName(candidate.getFirstName());
                    savedCandidate.setLastName(candidate.getLastName());
                    savedCandidate.setCv(candidate.getCv());
                    savedCandidate.setTitle(candidate.getTitle());
                    savedCandidate.setAvailable(candidate.getAvailable());
                    savedCandidate.setContractTypes(candidate.getContractTypes());
                    JobSeeker newCandidate = jobSeekerService.updateCandidate(savedCandidate);
                    return new ResponseEntity<>(newCandidate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    METHOD DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id){
        jobSeekerService.deleteCandidate(id);
        return new ResponseEntity<String>("Candidate removed successfully", HttpStatus.OK);
    }
}
