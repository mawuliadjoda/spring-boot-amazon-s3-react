package com.esprit.springbootamazons3.post;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class NearByPostService implements Function<NearByPostInputRecord, List<Pair<String, Double>>> {
    private PostViewRepository postViewRepository;

    @Autowired
    public NearByPostService(PostViewRepository postViewRepository) {
        this.postViewRepository = postViewRepository;
    }

    @Override
    public List<Pair<String, Double>> apply(NearByPostInputRecord nearByPostInputRecord) {

        postViewRepository.createPostView(
                nearByPostInputRecord.userTel(),
                nearByPostInputRecord.userDistanceZero()
        );

        List<Pair<String, Double>> nearByPost = postViewRepository.findNearByPost(
                nearByPostInputRecord.userTel(),
                nearByPostInputRecord.userDistanceZero(),
                nearByPostInputRecord.limit()
        );
        postViewRepository.dropPostView(nearByPostInputRecord.userTel());
        return nearByPost;
    }
}
