package com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    //List<Content> GetAllByRepositoryId(Long id);

    List<Content> findAllByRepositoryId(Long id);
    //Optional<Content> findByTitle(String title);
    //Optional<Content> findByName(String name);
}
