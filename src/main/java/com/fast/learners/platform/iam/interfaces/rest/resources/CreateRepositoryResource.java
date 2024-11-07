package com.fast.learners.platform.iam.interfaces.rest.resources;

public record CreateRepositoryResource(
        String name,
        String description,
        String visibility,
        boolean includeReadme,
        boolean includeGitignore,
        String collaborators
) {
}
