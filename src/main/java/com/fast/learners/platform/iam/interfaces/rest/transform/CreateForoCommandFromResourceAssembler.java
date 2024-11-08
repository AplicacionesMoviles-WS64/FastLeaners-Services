package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.CreateForoCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateForoResource;

public class CreateForoCommandFromResourceAssembler {

    public static CreateForoCommand toCommandFromResource(CreateForoResource resource) {
        return new CreateForoCommand(
                resource.postTitle(),
                resource.postBody(),
                resource.userId()
        );
    }
}