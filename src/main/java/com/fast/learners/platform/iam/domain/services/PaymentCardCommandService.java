package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.commands.CreatePaymentCardCommand;
import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;

import java.util.Optional;

public interface PaymentCardCommandService {

    Optional<PaymentCard> handle(CreatePaymentCardCommand command);
}
