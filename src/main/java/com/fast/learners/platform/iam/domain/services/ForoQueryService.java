package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.entities.Foro;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByTitleContainingOrBodyContainingQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserNameQuery;

import java.util.List;

public interface ForoQueryService {
   // List<Foro> handle(GetAllForosByUserNameQuery query);
    List<Foro> handle(GetAllForosByUserIdQuery query);
    List<Foro> handle(GetAllForosByTitleContainingOrBodyContainingQuery query);

}
