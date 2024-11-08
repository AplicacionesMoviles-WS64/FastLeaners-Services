package com.fast.learners.platform.iam.application.internal.queryservices;

import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.domain.model.queries.GetAllRepositoriesQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetRepositoryByIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetRepositoryNameByIdQuery;
import com.fast.learners.platform.iam.domain.services.RepositoryQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryQueryServiceImpl implements RepositoryQueryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryQueryServiceImpl(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Override
    public List<Repository> handle(GetAllRepositoriesQuery query) {
        return repositoryRepository.findAll();
    }

    @Override
    public Optional<Repository> handle(GetRepositoryByIdQuery query) {
        return repositoryRepository.findById(query.id());
    }
    @Override
    public Optional<String> handle(GetRepositoryNameByIdQuery query) {
        return repositoryRepository.findById(query.id())
                .map(Repository::getName);
    }
}
