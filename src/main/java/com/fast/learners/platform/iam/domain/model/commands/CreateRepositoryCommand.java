package com.fast.learners.platform.iam.domain.model.commands;

public record CreateRepositoryCommand(
        String name,
        String description,
        String visibility,
        boolean includeReadme,
        boolean includeGitignore,
        String collaborators
) {
}
