package com.fast.learners.platform.iam.domain.services;

import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import com.fast.learners.platform.iam.domain.model.queries.GetAllPaymentCardQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetPaymentCardByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentCardQueryService {

    List<PaymentCard> handle(GetAllPaymentCardQuery query);

    Optional<PaymentCard> handle(GetPaymentCardByIdQuery query);
}
