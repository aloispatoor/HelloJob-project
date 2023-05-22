package com.api.hellojob.auth;

import com.api.hellojob.entity.JobSeeker;
import com.api.hellojob.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobSeekerAuthService implements UserDetailsService {
    @Autowired
    private JobSeekerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JobSeeker> candidate = repository.findByEmail(username);
        return candidate.map(JobSeekerAuth::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
