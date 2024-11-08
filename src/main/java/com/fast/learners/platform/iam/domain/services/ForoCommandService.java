package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;

public interface ForoCommandService {
    void handle(CreateContentCommand command);
}
