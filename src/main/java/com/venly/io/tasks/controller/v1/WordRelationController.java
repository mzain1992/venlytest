package com.venly.io.tasks.controller.v1;

import com.venly.io.tasks.controller.v1.models.InverseDTO;
import com.venly.io.tasks.controller.v1.models.ResponseObjectInverse;
import com.venly.io.tasks.entity.WordRelationEntity;
import com.venly.io.tasks.exceptions.InvalidInputException;
import com.venly.io.tasks.repository.WordRelationRepository;
import com.venly.io.tasks.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/words")
public class WordRelationController {

    @Autowired
    WordService wordService;



    @PostMapping("/add/{word1}/{word2}/{relation}")
    public ResponseEntity<WordRelationEntity> createWordRelation(@PathVariable String word1,
                                                                 @PathVariable String word2, @PathVariable String relation) {


        return ResponseEntity.ok(wordService.addNewWord(word1,word2,relation));
    }

    @GetMapping("/path/{word1}/{word2}")
    public ResponseEntity<String> getPath(@PathVariable String word1,
                                                                 @PathVariable String word2) {


        return ResponseEntity.ok(wordService.getPath(word1,word2));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<WordRelationEntity>> getAllWords() {

        return ResponseEntity.ok(wordService.getAll());
    }

    @GetMapping(path = "/inverse")
    public ResponseEntity<ResponseObjectInverse> getAllWithInverse() {
             return ResponseEntity.ok(wordService.getAllIncludingInverse());
    }

    @GetMapping(path = "/related")
    public ResponseEntity<List<WordRelationEntity>> getOnlyRelated() {

        return ResponseEntity.ok(wordService.getAllRelated());
    }
}
