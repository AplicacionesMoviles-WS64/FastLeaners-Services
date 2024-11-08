package com.fast.learners.platform.iam.domain.model.entities;

import com.fast.learners.platform.iam.domain.model.aggregates.User;
import com.fast.learners.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Foro extends AuditableAbstractAggregateRoot<Foro> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String body;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Foro(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }
}
