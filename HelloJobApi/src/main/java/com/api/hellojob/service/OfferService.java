package com.api.hellojob.service;

import com.api.hellojob.entity.Company;
import com.api.hellojob.entity.Offer;
import com.api.hellojob.repository.CompanyRepository;
import com.api.hellojob.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(Long id){
        return offerRepository.findById(id);
    }

    public Offer addOffer(Offer offer, Long id){
        Company company = companyRepository.findById(id).get();
        offer.setAuthor(company);
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Offer offer){
        Offer newOffer = offerRepository.findById(offer.getId()).orElse(null);
        if(newOffer != null){
            newOffer.setTitle(offer.getTitle());
            newOffer.setSalary(offer.getSalary());
            newOffer.setDescription(offer.getDescription());
            newOffer.setContractType(offer.getContractType());
            offerRepository.save(newOffer);
        }
        return newOffer;
    }

    public void deleteOffer(Long id){
        Offer offer = offerRepository.findById(id).orElse(null);
        Company currentCompany = new Company();
        Company company = offer.getAuthor();
        if(currentCompany.equals(company)){
            offerRepository.deleteById(id);
            System.out.println("Offer " + id + " deleted successfully");
        }
    }
}
