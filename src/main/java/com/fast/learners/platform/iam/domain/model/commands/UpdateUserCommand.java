package com.fast.learners.platform.iam.domain.model.commands;

import com.fast.learners.platform.iam.domain.model.entities.Membership;

import java.util.List;
import java.util.Set;

public record UpdateUserCommand(String username,
                                String email) {
}
