package com.venly.io.tasks.controller.v1;

import com.venly.io.tasks.entity.WordRelationEntity;
import com.venly.io.tasks.repository.WordRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/words")
public class WordRelationController {

    @Autowired
    WordRelationRepository wordRelationRepository;

    @PostMapping("/add/{word1}/{word2}/{relation}")
    public ResponseEntity<WordRelationEntity> createWordRelation(@PathVariable String word1,
                                                                 @PathVariable String word2, @PathVariable String relation) {
        WordRelationEntity wordRelation = new WordRelationEntity(word1, word2, relation);
        WordRelationEntity savedWordRelation = wordRelationRepository.save(wordRelation);
        return ResponseEntity.ok(savedWordRelation);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<WordRelationEntity>> getAllWords() {
        List<WordRelationEntity> words = (List<WordRelationEntity>) wordRelationRepository.findAll();

        return ResponseEntity.ok(words);
    }
    @GetMapping(path = "/related")
    public ResponseEntity<List<WordRelationEntity>> getAllWithInverse() {
        List<WordRelationEntity> wordsList = (List<WordRelationEntity>) wordRelationRepository.findAll();

        List<WordRelationEntity> InverseWordsList=
                wordsList.stream().map(wordRelationEntity -> {WordRelationEntity word=new WordRelationEntity();


           word.setWord1(wordRelationEntity.getWord2());
            word.setWord1(wordRelationEntity.getWord1());
            word.setInverse("yes");
            wordRelationEntity.setInverse("no");
        return word;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(wordsList);
    }

    @GetMapping(path = "/inverse")
    public ResponseEntity<List<WordRelationEntity>> getOnlyRelated() {
        List<WordRelationEntity> words = (List<WordRelationEntity>) wordRelationRepository.findAllRelated("related");

        return ResponseEntity.ok(words);
    }
}
