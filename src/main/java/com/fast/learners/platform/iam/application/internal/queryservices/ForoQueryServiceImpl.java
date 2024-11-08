package com.fast.learners.platform.iam.application.internal.queryservices;

import com.fast.learners.platform.iam.domain.model.entities.Foro;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByTitleContainingOrBodyContainingQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserNameQuery;
import com.fast.learners.platform.iam.domain.services.ForoQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.ForoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForoQueryServiceImpl implements ForoQueryService {

    private final ForoRepository foroRepository;

    public ForoQueryServiceImpl(ForoRepository foroRepository) {
        this.foroRepository = foroRepository;
    }

    @Override
    public List<Foro> handle(GetAllForosByTitleContainingOrBodyContainingQuery query) {
        return foroRepository.findForosByTitleContainingOrBodyContaining(query.titleKeyword(), query.bodyKeyword());
    }

    @Override
    public List<Foro> handle(GetAllForosByUserIdQuery query) {
        return foroRepository.findForosByUserId(query.id());
    }
/*
    @Override
    public List<Foro> handle(GetAllForosByUserNameQuery query) {
        return foroRepository.findForosByUserName(query.username());
    }

 */
}