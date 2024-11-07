package com.fast.learners.platform.iam.interfaces.rest.resources;

public record RepositoryResource (
        Long id,
        String name,
        String description,
        String visibility,
        boolean includeReadme,
        boolean includeGitignore,
        String collaborators
){
}
