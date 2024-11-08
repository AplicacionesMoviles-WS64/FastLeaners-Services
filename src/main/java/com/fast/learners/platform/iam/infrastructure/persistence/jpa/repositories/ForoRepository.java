package com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories;

import com.fast.learners.platform.iam.domain.model.entities.Foro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForoRepository extends JpaRepository<Foro, Long> {
    List<Foro> findForosByTitleContainingOrBodyContaining(String titleKeyword, String bodyKeyword);
    List<Foro> findForosByUserId(Long userId);
    //List<Foro> findForosByUserName(String username);
}
