package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.entities.Foro;
import com.fast.learners.platform.iam.interfaces.rest.resources.ForoResource;

public class ForoResourceFromEntityAssembler {
    public static ForoResource toResourceFromEntity(Foro foro) {
        return new ForoResource(
                foro.getId(),
                foro.getTitle(),
                foro.getBody(),
                foro.getUser().getId(),
                foro.getUser().getUsername()
        );
    }
}
