package com.api.hellojob.auth;

import com.api.hellojob.entity.Company;
import com.api.hellojob.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyAuthService implements UserDetailsService {
    @Autowired
    private CompanyRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Company> company = repository.findByEmail(username);
        return company.map(CompanyAuth::new)
                .orElseThrow(() -> new UsernameNotFoundException("Company not found " + username));
    }
}
