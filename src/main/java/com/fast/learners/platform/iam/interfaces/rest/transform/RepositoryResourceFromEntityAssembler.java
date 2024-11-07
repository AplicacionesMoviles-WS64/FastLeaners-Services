package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.aggregates.User;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.interfaces.rest.resources.RepositoryResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.UserResource;

public class RepositoryResourceFromEntityAssembler {

    public static RepositoryResource toResourceFromEntity(Repository repository) {
        return new RepositoryResource(
                repository.getId(),
                repository.getName(),
                repository.getDescription(),
                repository.getVisibility(),
                repository.isIncludeReadme(),
                repository.isIncludeGitignore(),
                repository.getCollaborators());
    }

}
