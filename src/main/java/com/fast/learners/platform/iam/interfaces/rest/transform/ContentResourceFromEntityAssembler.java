package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.interfaces.rest.resources.ContentResource;

public class ContentResourceFromEntityAssembler {
    public static ContentResource toResourceFromEntity(Content repository){
        return new ContentResource(
                repository.getId(),
                repository.getTitleContent(),
                repository.getContentType(),
                repository.getDescription(),
                repository.getVisibility(),
                repository.getCollaborators(),
                repository.getId()
        );
    }
}
