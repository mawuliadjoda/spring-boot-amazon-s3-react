package com.esprit.springbootamazons3.post;

import java.util.List;

public record PostRecord(

        String postId,
        String userId,
        String userTel,
        String caption,
        String imageId,
        String imageId2,
        String imageId3,
        String imageUrl,
        String imageUrl2,
        String imageUrl3,
        String location,
        String tags,
        double latitude,
        double longitude,
        double distanceZero) {
}
