package com.esprit.springbootamazons3.repository;


import com.esprit.springbootamazons3.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
