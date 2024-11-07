package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignInCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateRepositoryResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.SignInResource;

public class CreateRepositoryCommandFromResourceAssembler {

    public static CreateRepositoryCommand toCommandFromResource(CreateRepositoryResource createRepositoryResource) {
        return new CreateRepositoryCommand(
                createRepositoryResource.name(),
                createRepositoryResource.description(),
                createRepositoryResource.visibility(),
                createRepositoryResource.includeReadme(),
                createRepositoryResource.includeGitignore(),
                createRepositoryResource.collaborators());
    }
}
