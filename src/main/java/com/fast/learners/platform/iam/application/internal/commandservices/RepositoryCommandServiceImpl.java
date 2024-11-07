package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignInCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.domain.services.RepositoryCommandService;
import com.fast.learners.platform.iam.domain.services.UserCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Repository command service implementation
 * <p>
 *     This class implements the {@link RepositoryCommandService} interface and provides the implementation for the
 *     {@link CreateRepositoryCommand} and {@link CreateRepositoryCommand} commands.
 * </p>
 */

@Service
public class RepositoryCommandServiceImpl implements RepositoryCommandService {

    private RepositoryRepository repositoryRepository;

    public RepositoryCommandServiceImpl(RepositoryRepository repositoryRepository){
        this.repositoryRepository = repositoryRepository;
    }


    @Override
    public Optional<Repository> handle(CreateRepositoryCommand command) {

        var repository = new Repository(command);
        var repositoryResult = repositoryRepository.save(repository);

        return Optional.of(repositoryResult);
    }
}
