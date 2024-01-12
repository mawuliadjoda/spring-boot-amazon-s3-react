package com.esprit.springbootamazons3.post;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "post")

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Post {
    @Id
    private String postId;
    private String userId;
    private String userTel;
    private String caption;
    private String imageId;
    private String imageId2;
    private String imageId3;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private String location;
    private List<String> tags;
    private double latitude;
    private double longitude;
    private double distanceZero;
}
