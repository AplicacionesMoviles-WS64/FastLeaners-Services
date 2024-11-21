package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.UpdateUserCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.UpdateUserResource;

import java.util.ArrayList;

public class UpdateResourceCommandFromResourceAssembler {

    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(resource.username(), resource.email());
    }
}
