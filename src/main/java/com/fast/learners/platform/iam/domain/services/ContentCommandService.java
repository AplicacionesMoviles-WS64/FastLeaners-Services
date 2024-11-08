package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;

public interface ContentCommandService {
    void handle(CreateContentCommand command);
}
