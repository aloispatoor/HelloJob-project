package com.api.hellojob.controller;

import com.api.hellojob.entity.Offer;
import com.api.hellojob.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("")
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/company/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Offer addOffer(@PathVariable("id") Long companyId, @RequestBody Offer offer){
        return offerService.addOffer(offer, companyId);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer offer){
        return offerService.getOfferById(id)
                .map(savedOffer -> {
                    savedOffer.setContractType(offer.getContractType());
                    savedOffer.setSalary(offer.getSalary());
                    savedOffer.setDescription(offer.getDescription());
                    savedOffer.setTitle(offer.getTitle());

                    Offer newOffer = null;
                    try {
                        newOffer = offerService.updateOffer(savedOffer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return new ResponseEntity<>(newOffer, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) throws Exception {
        offerService.deleteOffer(id);
        return new ResponseEntity<String>("Offer removed successfully!", HttpStatus.OK);
    }
}

