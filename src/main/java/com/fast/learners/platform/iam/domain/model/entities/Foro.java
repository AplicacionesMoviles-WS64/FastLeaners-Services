package com.fast.learners.platform.iam.domain.model.entities;

import com.fast.learners.platform.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String body;
    @Column
    private int like;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relación con la entidad User

    public Foro(String title, String body, int Like, User user) {
        this.title = title;
        this.body = body;
        this.like = Like;
        this.user = user;
    }
}
