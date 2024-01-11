package com.esprit.springbootamazons3.post;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String postId;
    private String caption;
    private String imageId;
    private String imageId2;
    private String imageId3;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private String location;
    private String tags;
    private double lat;
    private double lng;
    private double distanceZero;
}
