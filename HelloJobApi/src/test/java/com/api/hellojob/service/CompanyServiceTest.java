package com.api.hellojob.service;

import com.api.hellojob.entity.Company;
import com.api.hellojob.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void save_NominalCase(){
        Company company = new Company();
        company.setEmail("test@test.com");
        company.setPassword("test123test");
        company.setName("Test Company");
        company.setLocation("Test City");
        companyRepository.save(company);
        System.out.println("Test save end");
    }

    @Test
    public void find_NominalCase(){
        Company company = companyRepository.findById(1L).get();
        assertThat(company.getName()).as("Company not found").isEqualTo("Test Company");
    }

    @Test
    public void find_allCases(){
        List<Company> companies = companyRepository.findAll();
        assertThat(companies).as("Not all companies were found").hasSize(1);
        companies.forEach(System.out::println);
    }
}
