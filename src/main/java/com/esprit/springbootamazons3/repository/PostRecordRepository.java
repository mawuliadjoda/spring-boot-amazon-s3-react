package com.esprit.springbootamazons3.repository;

import com.esprit.springbootamazons3.record.PostRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;




/*
@Repository
@Transactional

 */
public interface PostRecordRepository extends ListCrudRepository<PostRecord, String> {

}
