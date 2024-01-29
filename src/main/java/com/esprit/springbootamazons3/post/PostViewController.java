package com.esprit.springbootamazons3.post;


import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping(value = "/api/postView")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostViewController {
    private PostViewRepository postViewRepository;
    private NearByPostService nearByPostService;

    @Autowired
    public PostViewController(PostViewRepository postViewRepository, NearByPostService nearByPostService) {
        this.postViewRepository = postViewRepository;
        this.nearByPostService = nearByPostService;
    }

    @PostMapping("/{userTel}/{userDistanceZero}")
    void createPostView(
            @PathVariable String userTel,
            @PathVariable double userDistanceZero) {
        postViewRepository.createPostView(userTel, userDistanceZero);
    }

    @DeleteMapping("/{userTel}")
    void deletePostView(@PathVariable String userTel) {
        postViewRepository.dropPostView(userTel);
    }

    @GetMapping("/findNearByPost/{userTel}/{userDistanceZero}/{limit}")
    List<Pair<String, Double>> findNearByPost(@PathVariable String userTel,
                                               @PathVariable double userDistanceZero,
                                               @PathVariable Integer limit) {
        Supplier<NearByPostInputRecord> nearByPostInputRecordSupplier = () -> new NearByPostInputRecord(userTel, userDistanceZero, limit);
        return nearByPostService.apply(nearByPostInputRecordSupplier.get());
    }
}
