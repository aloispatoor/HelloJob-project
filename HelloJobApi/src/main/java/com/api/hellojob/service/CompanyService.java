package com.api.hellojob.service;

import com.api.hellojob.entity.Company;
import com.api.hellojob.entity.Offer;
import com.api.hellojob.repository.CompanyRepository;
import com.api.hellojob.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OfferRepository offerRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).get();
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id){
        companyRepository.findById(id).orElse(null);
        companyRepository.deleteById(id);
        System.out.println("Company " + id + " deleted successfully");
    }

    public Company updateCompany(Company company){
        Company newCompany = companyRepository.findById(company.getId()).orElse(null);
        if(newCompany != null){
            newCompany.setEmail(company.getEmail());
            newCompany.setName(company.getName());
            newCompany.setLocation(company.getLocation());
            companyRepository.save(newCompany);
        }
        return newCompany;
    }

//    RELATIONSHIPS BETWEEN COMPANY AND OFFERS
    public Offer addOffer(Long id, Offer offer){
        Company company = companyRepository.findById(id).get();
        company.addOffer(offer);
        System.out.println("Offer added by company " + company.getName());
        return offerRepository.save(offer);
    }

    public List<Offer> getCompanyOffers(Long id){
        return companyRepository.findById(id).get().getOffers();
    }
}
