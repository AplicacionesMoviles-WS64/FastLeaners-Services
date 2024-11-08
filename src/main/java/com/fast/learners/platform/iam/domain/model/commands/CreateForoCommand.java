package com.fast.learners.platform.iam.domain.model.commands;

public record CreateForoCommand (
        String postTitle,
        String postBody,
        String authorName,
        int like,
        Long userId
) {}