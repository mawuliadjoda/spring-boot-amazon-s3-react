package com.esprit.springbootamazons3.post;


/*
@RestController
@RequestMapping(value = "/api/posts")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

 */
public class PostController {

    /*
    private PostRecordRepository postRecordRepository;

    @Autowired
    public PostController(PostRecordRepository postRecordRepository) {
        this.postRecordRepository = postRecordRepository;
    }


    @GetMapping("")
    List<PostRecord> findAll() {
        return postRecordRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<PostRecord> findById(@PathVariable String id){
        return Optional.ofNullable(postRecordRepository.findById(id)
                .orElseThrow(PostNotFoundException::new));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    PostRecord create(@RequestBody @Validated PostRecord postRecord) {
        return postRecordRepository.save(postRecord);
    }

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        postRecordRepository.deleteById(id);
    }

     */
}
