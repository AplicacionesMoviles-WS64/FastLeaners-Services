package com.fast.learners.platform.iam.application.internal.queryservices;

import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import com.fast.learners.platform.iam.domain.model.queries.GetAllPaymentCardQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetPaymentCardByIdQuery;
import com.fast.learners.platform.iam.domain.services.PaymentCardQueryService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentCardQueryServiceImpl implements PaymentCardQueryService {

    private final PaymentCardRepository paymentCardRepository;

    public PaymentCardQueryServiceImpl(PaymentCardRepository paymentCardRepository){
        this.paymentCardRepository = paymentCardRepository;
    }


    @Override
    public List<PaymentCard> handle(GetAllPaymentCardQuery query) {
        return paymentCardRepository.findAll();
    }

    @Override
    public Optional<PaymentCard> handle(GetPaymentCardByIdQuery query) {
        return paymentCardRepository.findById(query.id());
    }
}
