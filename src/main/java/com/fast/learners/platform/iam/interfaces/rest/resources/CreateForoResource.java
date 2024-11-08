package com.fast.learners.platform.iam.interfaces.rest.resources;

public record CreateForoResource (
        String postTitle,
        String postBody,
        Long userId
) {}
