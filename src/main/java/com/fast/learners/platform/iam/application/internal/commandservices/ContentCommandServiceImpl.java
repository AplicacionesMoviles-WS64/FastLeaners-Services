package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;
import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.domain.services.ContentCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.ContentRepository;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.RepositoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContentCommandServiceImpl implements ContentCommandService {

    private final ContentRepository contentRepository;
    private final RepositoryRepository repositoryRepository;

    public ContentCommandServiceImpl(ContentRepository contentRepository, RepositoryRepository repositoryRepository) {
        this.contentRepository = contentRepository;
        this.repositoryRepository = repositoryRepository;
    }

    @Override
    @Transactional
    public void handle(CreateContentCommand command) {
        Repository repository = repositoryRepository.findById(command.repositoryId())
                .orElseThrow(() -> new IllegalArgumentException("Repository not found"));

        Content content = new Content(command, repository);

        contentRepository.save(content);
    }
}