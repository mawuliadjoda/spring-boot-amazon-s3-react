package com.esprit.springbootamazons3.record;
import org.springframework.data.annotation.Id;
public record PostRecord(
        @Id
        String postId,
        String caption,
        String imageId,
        String imageId2,
        String imageId3,
        String imageUrl,
        String imageUrl2,
        String imageUrl3,
        String location,
        String tags,
        double lat,
        double lng,
        double distanceZero) {
}
