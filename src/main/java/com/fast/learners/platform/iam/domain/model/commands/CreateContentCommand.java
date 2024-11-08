package com.fast.learners.platform.iam.domain.model.commands;

public record CreateContentCommand (
    String titleContent,
    String contentType,
    String description,
    String visibility,
    String collaborators,
    Long repositoryId

){}
