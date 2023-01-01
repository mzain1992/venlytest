package com.venly.io.tasks.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wordrelation")
public class WordRelationEntity {
    @Id
    private String word1;
    private String word2;
    private String relation;

    public String getInverse() {
        return inverse;
    }

    public void setInverse(String inverse) {
        this.inverse = inverse;
    }

    private String inverse;

    public WordRelationEntity() {}

    public WordRelationEntity(String word1, String word2, String relation) {
        this.word1 = word1;
        this.word2 = word2;
        this.relation = relation;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}