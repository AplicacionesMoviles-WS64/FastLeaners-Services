package com.fast.learners.platform.iam.domain.model.entities;


import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
public class Repository extends AuditableAbstractAggregateRoot<Repository> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String visibility;

    @Column
    private boolean includeReadme;

    @Column
    private boolean includeGitignore;

    @Column
    private String collaborators;

    protected Repository(){}

    public Repository(CreateRepositoryCommand command){
        this.name = command.name();
        this.description = command.description();
        this.visibility = command.visibility();

        this.includeReadme = command.includeReadme();
        this.includeGitignore = command.includeGitignore();

        this.collaborators = command.collaborators();
    }
}
