package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.commands.CreatePaymentCardCommand;
import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import com.fast.learners.platform.iam.domain.services.PaymentCardCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCardCommandServiceImpl implements PaymentCardCommandService {

    private final PaymentCardRepository paymentCardRepository;

    public PaymentCardCommandServiceImpl(PaymentCardRepository paymentCardRepository){
        this.paymentCardRepository = paymentCardRepository;
    }

    @Override
    public Optional<PaymentCard> handle(CreatePaymentCardCommand command) {

        var paymentCard = new PaymentCard(command);
        var paymentCardResult = paymentCardRepository.save(paymentCard);

        return Optional.of(paymentCardResult);
    }
}
