package com.esprit.springbootamazons3.post;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class NearByPostService implements Function<NearByPostRecord, List<Pair<String, Double>>> {
    private PostViewRepository postViewRepository;

    @Autowired
    public NearByPostService(PostViewRepository postViewRepository) {
        this.postViewRepository = postViewRepository;
    }

    @Override
    public List<Pair<String, Double>> apply(NearByPostRecord nearByPostRecord) {

        postViewRepository.createPostView(
                nearByPostRecord.userTel(),
                nearByPostRecord.userDistanceZero()
        );

        List<Pair<String, Double>> nearByPost = postViewRepository.findNearByPost(
                nearByPostRecord.userTel(),
                nearByPostRecord.userDistanceZero(),
                nearByPostRecord.limit()
        );
        postViewRepository.dropPostView(nearByPostRecord.userTel());
        return nearByPost;
    }
}
