package com.fast.learners.platform.iam.application.internal.queryservices;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.queries.GetAllContentByRepositoryId;
import com.fast.learners.platform.iam.domain.services.ContentQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentQueryServiceImpl implements ContentQueryService {

    private final ContentRepository contentRepository;

    public ContentQueryServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<Content> handle(GetAllContentByRepositoryId query) {
        // Obtener todos los contenidos asociados al repositoryId proporcionado en el query
        return contentRepository.findAllByRepositoryId(query.id());
    }
}