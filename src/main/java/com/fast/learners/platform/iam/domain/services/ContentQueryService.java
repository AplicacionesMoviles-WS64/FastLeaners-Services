package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.queries.GetAllContentByRepositoryId;

import java.util.List;

public interface ContentQueryService {
    List<Content> handle(GetAllContentByRepositoryId query);
}
