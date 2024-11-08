package com.fast.learners.platform.iam.interfaces.rest.resources;

public record ForoResource (
        Long id,
        String postTitle,
        String postBody,
        Long userId,
        String  username
) {}