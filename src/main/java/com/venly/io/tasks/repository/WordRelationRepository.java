package com.venly.io.tasks.repository;

import com.venly.io.tasks.entity.WordRelationEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRelationRepository extends CrudRepository<WordRelationEntity, Long> {

    @Query(value="select * FROM wordrelation w WHERE w.relation = ?1", nativeQuery = true)
    List<WordRelationEntity> findAllRelated(String related);

}