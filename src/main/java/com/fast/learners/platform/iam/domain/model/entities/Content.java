package com.fast.learners.platform.iam.domain.model.entities;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleContent;
    private String contentType;
    private String description;
    private String visibility;
    private String collaborators;

    @ManyToOne
    @JoinColumn(name = "repository_id", nullable = false)
    private Repository repository;

    public Content(CreateContentCommand command, Repository repository) {
        this.titleContent = command.titleContent();
        this.contentType = command.contentType();
        this.description = command.description();
        this.visibility = command.visibility();
        this.collaborators = command.collaborators();
        this.repository = repository;
    }

    protected Content() {}
}