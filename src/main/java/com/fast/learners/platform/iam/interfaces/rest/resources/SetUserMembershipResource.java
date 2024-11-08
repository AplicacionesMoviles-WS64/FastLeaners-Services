package com.fast.learners.platform.iam.interfaces.rest.resources;

import com.fast.learners.platform.iam.domain.model.entities.Membership;

import java.util.List;
import java.util.Set;

public record SetUserMembershipResource(Long id, List<String> memberships) {
}
