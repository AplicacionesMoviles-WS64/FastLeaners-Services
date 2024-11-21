package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.UpdateUserCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.interfaces.rest.resources.UpdateUserResource;

import java.util.ArrayList;

public class EditResourceCommandFromResourceAssembler {

    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        var memberships = resource.memberships() != null ? resource.memberships().stream().map(name -> Membership.toMembershipFromName(name)).toList() : new ArrayList<Membership>();
        return new UpdateUserCommand(resource.username(), resource.email(), resource.password(), memberships);
    }
}
