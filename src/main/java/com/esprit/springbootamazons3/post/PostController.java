package com.esprit.springbootamazons3.post;


import com.esprit.springbootamazons3.exception.MyCrudException;
import com.esprit.springbootamazons3.exception.MyCrudExceptionEnum;
import com.esprit.springbootamazons3.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private PostRepository postRepository;
    private PostMapper postMapper;

    @Autowired
    public PostController(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAll() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Post> add(@RequestBody PostRecord postRecord) {
        return new ResponseEntity<>(postRepository.save(postMapper.apply(postRecord)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        if (postRepository.existsById(id)) {
            throw new MyCrudException(MyCrudExceptionEnum.PHARMACY_NOT_FOUND, "No solution founded with id {}");
        }
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable String id){
        return Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(NotFoundException::new));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        try {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PutMapping("/{id}")
    PostRecord update(@PathVariable String id,@RequestBody @Validated PostRecord postRecord) {
        Optional<PostRecord> existing = postRecordRepository.findById(id);
        if(existing.isPresent()) {
            PostRecord updated = new PostRecord(
                    existing.get().postId(),
                    postRecord.caption(),
                    postRecord.imageId(),
                    postRecord.imageId2(),
                    postRecord.imageId3(),
                    postRecord.imageUrl(),
                    postRecord.imageUrl2(),
                    postRecord.imageUrl3(),
                    postRecord.location(),
                    postRecord.tags(),
                    postRecord.lat(),
                    postRecord.lng(),
                    postRecord.distanceZero()
            );
            return postRecordRepository.save(updated);
        } else {
            throw new PostNotFoundException();
        }

    }
     */
}
