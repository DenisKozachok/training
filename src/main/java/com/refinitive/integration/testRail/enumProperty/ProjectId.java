package com.refinitive.integration.testRail.enumProperty;

import lombok.Getter;

@Getter
public enum ProjectId {

    CODEBOOK(1);


    private final int projectId;

    ProjectId(int projectId) {
        this.projectId = projectId;
    }


}
