package com.venly.io.tasks.service;


import com.venly.io.tasks.controller.v1.models.InverseDTO;
import com.venly.io.tasks.controller.v1.models.ResponseObjectInverse;
import com.venly.io.tasks.entity.WordRelationEntity;
import com.venly.io.tasks.exceptions.InvalidInputException;
import com.venly.io.tasks.repository.WordRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class WordService {
    @Autowired
    WordRelationRepository wordRelationRepository;

    public boolean verifyWords(String word1, String word2, String relation) {

        String pattern = "[a-z]+";
        return Pattern.matches(pattern, word1) && Pattern.matches(pattern, word2) && Pattern.matches(pattern, relation);

    }

    public boolean checkExistingWords(List<WordRelationEntity> wordRelationEntities, String word1, String word2, String relation) {

        wordRelationEntities.stream().forEach(wordRelationEntity -> {

                    if (word1.equalsIgnoreCase(wordRelationEntity.getWord1()) &&
                            word2.equalsIgnoreCase(wordRelationEntity.getWord2()) &&
                            relation.equalsIgnoreCase(wordRelationEntity.getRelation())) {
                        throw new InvalidInputException(word1 + " with " + word2 + " that has relation " + relation + " already exist");
                    } else {

                        if (word2.equalsIgnoreCase(wordRelationEntity.getWord1()) &&
                                word1.equalsIgnoreCase(wordRelationEntity.getWord2()) &&
                                relation.equalsIgnoreCase(wordRelationEntity.getRelation())) {
                            throw new InvalidInputException(word1 + " with " + word2 + " already has " + relation + " already exist in inverse order");
                        }
                    }

                }
        );
        return true;

    }

    public WordRelationEntity addNewWord(String word1, String word2, String relation) {

        if (!verifyWords(word1, word2, relation)) {
            throw new InvalidInputException("Words contain invalid characters only accept A to Z or a to Z");
        }
        List<WordRelationEntity> wordRelationEntities = (List<WordRelationEntity>) wordRelationRepository.findAll();
        checkExistingWords(wordRelationEntities, word1, word2, relation);
        WordRelationEntity wordRelation = new WordRelationEntity(word1.trim().toLowerCase(), word2.trim().toLowerCase(), relation.trim().toLowerCase());
        WordRelationEntity savedWordRelation = wordRelationRepository.save(wordRelation);
        return savedWordRelation;
    }


    public List<WordRelationEntity> getAll() {


        return (List<WordRelationEntity>) wordRelationRepository.findAll();
    }

    public List<WordRelationEntity> getAllRelated() {


        return (List<WordRelationEntity>) wordRelationRepository.findAllRelated("related");
    }


    public ResponseObjectInverse getAllIncludingInverse() {
        List<WordRelationEntity> wordsList = (List<WordRelationEntity>) wordRelationRepository.findAll();

        List<InverseDTO> mainList = new ArrayList<>();
        List<InverseDTO> inverseWordsList = new ArrayList<>();

        wordsList.stream().forEach(wordRelationEntity -> {
            WordRelationEntity word = new WordRelationEntity();

            mainList.add(new InverseDTO(wordRelationEntity.getWord2(), wordRelationEntity.getWord1(), wordRelationEntity.getRelation(), "yes"));
            inverseWordsList.add(new InverseDTO(wordRelationEntity.getWord1(), wordRelationEntity.getWord2(), wordRelationEntity.getRelation(), "no"));


        });

        return new ResponseObjectInverse(mainList, inverseWordsList);
    }


    public String getPath(String word1, String word2) {
        List<WordRelationEntity> wordsList = (List<WordRelationEntity>) wordRelationRepository.findAll();


        return getRoot(word1, word2, wordsList);
    }

    String getRoot(String word1, String baseWord, List<WordRelationEntity> wordsList) {
        WordRelationEntity word = null;
        int i = 0;
        for (i = 0; i < wordsList.size(); i++) {
            word = wordsList.get(i);
            if (word.getWord1().equalsIgnoreCase(word1) || word.getWord2().equalsIgnoreCase(word1))
                break;

        }

        if (word != null) {

            wordsList.remove(i);
            String secondWord = word.getWord1().equalsIgnoreCase(word1) ? word.getWord2() : word1;
            return " " + word1 + " ==" + word.getRelation() + "=>" +
                    (baseWord.equalsIgnoreCase(word.getWord1()) || baseWord.equalsIgnoreCase(word.getWord2())
                            ? secondWord : getRoot(secondWord, baseWord, wordsList));
        }

        return "";

    }
}
