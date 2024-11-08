package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.CreateForoCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.ForoResource;

public class CreateForoCommandFromResourceAssembler {
    public static ForoResource toCommandFromResoure(CreateForoCommand createForoCommand, Long userId) {
        return new ForoResource(
                createForoCommand.userId(),
                createForoCommand.postTitle(),
                createForoCommand.postBody(),
                createForoCommand.userId(),
                createForoCommand.authorName()
        );
    }
}
