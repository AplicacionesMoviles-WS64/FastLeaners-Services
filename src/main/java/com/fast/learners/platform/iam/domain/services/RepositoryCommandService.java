package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.iam.domain.model.entities.Repository;

import java.util.Optional;

public interface RepositoryCommandService {

    Optional<Repository> handle(CreateRepositoryCommand command);
}
