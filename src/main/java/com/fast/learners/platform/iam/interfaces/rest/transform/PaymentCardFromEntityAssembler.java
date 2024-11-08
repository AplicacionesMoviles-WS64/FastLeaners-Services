package com.fast.learners.platform.iam.interfaces.rest.transform;

import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import com.fast.learners.platform.iam.interfaces.rest.resources.PaymentCardResource;

public class PaymentCardFromEntityAssembler {

    public static PaymentCardResource toResourceFromEntity(PaymentCard paymentCard) {
        return new PaymentCardResource(
                paymentCard.getId(),
                paymentCard.getTitleName(),
                paymentCard.getNumberCard(),
                paymentCard.getEmail(),
                paymentCard.getDateExpire(),
                paymentCard.getSecurityNumber(),
                paymentCard.getCardNickname()
        );
    }

}
