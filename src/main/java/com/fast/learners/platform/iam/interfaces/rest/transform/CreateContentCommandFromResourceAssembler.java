package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateContentResource;

public class CreateContentCommandFromResourceAssembler {
    public static CreateContentCommand toCommandFromResource(CreateContentResource createContentresource, Long repositoryId) {
        return new CreateContentCommand(
                createContentresource.titleContent(),
                createContentresource.contentType(),
                createContentresource.description(),
                createContentresource.visibility(),
                createContentresource.collaborators(),
                createContentresource.repositoryId()
        );
    }
}
