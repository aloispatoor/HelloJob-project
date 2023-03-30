package com.api.hellojob.controller;

import com.api.hellojob.entity.Company;
import com.api.hellojob.entity.Offer;
import com.api.hellojob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

//    METHODS GET
    @GetMapping("")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/offers")
    public List<Offer> getCompanyOffers(@PathVariable Long id){
        return companyService.getCompanyOffers(id);
    }

//    METHODS POST
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PostMapping("/{id}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    public Offer createOffer(@PathVariable("id") Long id, @RequestBody Offer offer){
        return companyService.addOffer(id, offer);
    }

//    METHOD PUT
    @PutMapping("/edit/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.getCompanyById(id)
                .map(savedCompany -> {
                    savedCompany.setEmail(company.getEmail());
                    savedCompany.setPassword(company.getPassword());
                    savedCompany.setLocation(company.getLocation());
                    savedCompany.setName(company.getName());
                    Company newCompany = companyService.updateCompany(savedCompany);
                    return new ResponseEntity<>(newCompany, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    METHOD DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<String>("Company removed successfully", HttpStatus.OK);
    }

}
