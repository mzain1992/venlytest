package com.venly.io.tasks.controller.v1.models;

import com.venly.io.tasks.entity.WordRelationEntity;

public class InverseDTO extends WordRelationEntity {

    public String getInverse() {
        return inverse;
    }

    public void setInverse(String inverse) {
        this.inverse = inverse;
    }

    public InverseDTO(String inverse) {
        this.inverse = inverse;
    }

    public InverseDTO(String word1, String word2, String relation, String inverse) {
        super(word1, word2, relation);
        this.inverse = inverse;
    }

    private String inverse;
}
