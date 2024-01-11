package com.esprit.springbootamazons3.post;


import com.esprit.springbootamazons3.post.PostViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/postView")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostViewController {
    private PostViewRepository postViewRepository;

    @Autowired
    public PostViewController(PostViewRepository postViewRepository) {
        this.postViewRepository = postViewRepository;
    }

    @PostMapping("/{userTel}/{userDistanceZero}/{lat}/{lng}")
    void createPostView(
            @PathVariable String userTel,
            @PathVariable double userDistanceZero,
            @PathVariable double lat,
            @PathVariable double lng) {
        postViewRepository.createPostView(userTel, userDistanceZero, lat, lng);
    }

    @DeleteMapping("/{userTel}")
    void deletePostView(@PathVariable String userTel) {
        postViewRepository.deletePostView(userTel);
    }
}
