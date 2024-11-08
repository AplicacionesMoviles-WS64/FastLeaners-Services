package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreateForoCommand;
import com.fast.learners.platform.iam.domain.model.entities.Foro;

public interface ForoCommandService {
    Foro handle(CreateForoCommand command);
}
