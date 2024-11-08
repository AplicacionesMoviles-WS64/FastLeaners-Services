package com.fast.learners.platform.iam.interfaces.rest.resources;

public record PaymentCardResource(
        Long id,
        String titleName,
        String numberCard,
        String email,
        String dateExpire,
        String securityNumber,
        String cardNickname
) {
}
