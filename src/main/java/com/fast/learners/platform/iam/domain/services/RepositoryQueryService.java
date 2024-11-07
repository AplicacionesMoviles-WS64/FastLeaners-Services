package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.domain.model.queries.GetAllRepositoriesQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetRepositoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RepositoryQueryService {

    List<Repository> handle(GetAllRepositoriesQuery query);

    Optional<Repository> handle(GetRepositoryByIdQuery query);
}
