package com.esprit.springbootamazons3.repository;

import com.esprit.springbootamazons3.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
}


