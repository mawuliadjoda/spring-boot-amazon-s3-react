package com.esprit.springbootamazons3.pharmacy;


import com.esprit.springbootamazons3.pharmacy.Pharmacy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    @Query(
            value = "SELECT * FROM Pharmacy p ",
            nativeQuery = true)
    Collection<Pharmacy> findAllPharmacies();


    @Modifying
    @Query(value = "update Pharmacy set name = :name where id = :id",
            nativeQuery = true)
    int updatePharmacy(Long id, String name);

    @Query(value =  "SELECT * FROM PharmacyView ",
            nativeQuery = true)
    List<Object> selectObjects();

}
