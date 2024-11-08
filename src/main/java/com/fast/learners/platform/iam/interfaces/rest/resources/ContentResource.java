package com.fast.learners.platform.iam.interfaces.rest.resources;

public record ContentResource (
        Long id,
        String titleContent,
        String contentType,
        String description,
        String visibility,
        String collaborators,
        Long repositoryId
){}