package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.SetUserMembershipCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.SetUserMembershipResource;

public class SetUserMembershipCommandFromResourceAssembler {

    public static SetUserMembershipCommand toCommandFromResource(SetUserMembershipResource setUserMembershipResource) {
        return new SetUserMembershipCommand(
                setUserMembershipResource.id(),
                setUserMembershipResource.memberships());
    }
}
