package com.esprit.springbootamazons3.post;


import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/postView")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostViewController {
    private PostViewRepository postViewRepository;

    @Autowired
    public PostViewController(PostViewRepository postViewRepository) {
        this.postViewRepository = postViewRepository;
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
        return postViewRepository.findNearByPost(userTel, userDistanceZero, limit);
    }
}
