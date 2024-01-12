package com.esprit.springbootamazons3.post;


import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostMapper implements Function<PostRecord, Post> {
    @Override
    public Post apply(PostRecord postRecord) {
        return Post.builder()
                .postId(postRecord.postId())
                .userId(postRecord.userId())
                .userTel(postRecord.userTel())
                .caption(postRecord.caption())
                .imageId(postRecord.imageId())
                .imageId2(postRecord.imageId2())
                .imageId3(postRecord.imageId3())
                .imageUrl(postRecord.imageUrl())
                .imageUrl2(postRecord.imageUrl2())
                .imageUrl3(postRecord.imageUrl3())
                .location(postRecord.location())
                .tags(postRecord.tags())
                .latitude(postRecord.latitude())
                .longitude(postRecord.longitude())
                .distanceZero(postRecord.distanceZero())
                .build();
    }
}
