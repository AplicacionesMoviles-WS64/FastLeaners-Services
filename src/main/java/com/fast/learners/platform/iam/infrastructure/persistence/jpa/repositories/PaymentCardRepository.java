package com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories;

import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
}
