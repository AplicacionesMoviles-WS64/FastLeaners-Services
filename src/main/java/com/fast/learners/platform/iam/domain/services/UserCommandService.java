package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.aggregates.User;
import com.fast.learners.platform.iam.domain.model.commands.SetUserMembershipCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignInCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.commands.UpdateUserCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {

    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    Optional<User> handle(SignUpCommand command);

    Optional<User> handle(SetUserMembershipCommand command);

    Optional<User> handle(UpdateUserCommand command);
}
