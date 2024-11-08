package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.commands.CreatePaymentCardCommand;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreatePaymentCardResource;

public class CreatePaymentCardCommandFromResourceAssembler {

    public static CreatePaymentCardCommand toCommandFromResource(CreatePaymentCardResource resource) {
        return new CreatePaymentCardCommand(
                resource.titleName(),
                resource.numberCard(),
                resource.email(),
                resource.dateExpire(),
                resource.securityNumber(),
                resource.cardNickname()
        );
    }

}
