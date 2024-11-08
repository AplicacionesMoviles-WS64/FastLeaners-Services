package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.queries.GetAllByTitleContainingOrBodyContainingQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserNameQuery;

import java.util.List;

public interface ForoQueryService {
    List<Content> handle(GetAllForosByUserNameQuery query);
    List<Content> handle(GetAllForosByUserIdQuery query);
    List<Content> handle(GetAllByTitleContainingOrBodyContainingQuery query);

}
