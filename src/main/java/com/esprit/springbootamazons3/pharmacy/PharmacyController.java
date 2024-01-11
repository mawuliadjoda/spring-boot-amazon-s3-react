package com.esprit.springbootamazons3.pharmacy;


import com.esprit.springbootamazons3.exception.MyCrudException;
import com.esprit.springbootamazons3.exception.MyCrudExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/pharmacies")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PharmacyController {

    private  PharmacyRepository pharmacyRepository;
    private PharmacyRepositoryImpl pharmacyRepositoryImpl;

    @Autowired
    public PharmacyController(PharmacyRepository pharmacyRepository, PharmacyRepositoryImpl pharmacyRepositoryImpl) {
        this.pharmacyRepository = pharmacyRepository;
        this.pharmacyRepositoryImpl = pharmacyRepositoryImpl;
    }

    @GetMapping()
    public ResponseEntity<List<Pharmacy>> getAll() {
        pharmacyRepositoryImpl.createView();
        Collection<Pharmacy> allPharmacies = pharmacyRepository.findAllPharmacies();

        List<Object> objects = pharmacyRepository.selectObjects();
        System.out.println(objects);
        return new ResponseEntity<>(allPharmacies.stream().toList(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/{name}")
    void update(@PathVariable("id") Long id, @PathVariable("name") String name) {
        pharmacyRepository.updatePharmacy(id, name);
    }

    @PostMapping(value = "/{id}/{name}/{address}")
    void create(@PathVariable("id") Long id, @PathVariable("name") String name, @PathVariable("address") String address) {

        pharmacyRepositoryImpl.createNew(new PharmacyRecord(id,name, address));

    }

    @PostMapping()
    public ResponseEntity<Pharmacy> add(@RequestBody Pharmacy pharmacy) {
        return new ResponseEntity<>(pharmacyRepository.save(pharmacy), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pharmacy> update(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
        if (pharmacyRepository.existsById(id)) {
            throw new MyCrudException(MyCrudExceptionEnum.PHARMACY_NOT_FOUND, "No solution founded with id {}");
        }
        return new ResponseEntity<>(pharmacyRepository.save(pharmacy), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            pharmacyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}