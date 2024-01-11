package com.esprit.springbootamazons3.pharmacy;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "pharmacy")
@Data
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private Double lat;

    private Double lng;
}
