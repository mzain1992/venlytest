package com.venly.io.tasks.controller.v1.models;

import com.venly.io.tasks.entity.WordRelationEntity;

import java.util.List;

public class ResponseObjectInverse {
    private List<InverseDTO> mainList;
    private List<InverseDTO> inverseList;

    public List<InverseDTO> getMainList() {
        return mainList;
    }

    public void setMainList(List<InverseDTO> mainList) {
        this.mainList = mainList;
    }

    public List<InverseDTO> getInverseList() {
        return inverseList;
    }

    public void setInverseList(List<InverseDTO> inverseList) {
        this.inverseList = inverseList;
    }

    public ResponseObjectInverse(List<InverseDTO> mainList, List<InverseDTO> inverseList) {
        this.mainList = mainList;
        this.inverseList = inverseList;
    }
}
