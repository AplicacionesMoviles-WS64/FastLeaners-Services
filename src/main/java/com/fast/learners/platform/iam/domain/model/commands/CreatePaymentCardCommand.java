package com.fast.learners.platform.iam.domain.model.commands;

public record CreatePaymentCardCommand(
        String titleName,
        String numberCard,
        String email,
        String dateExpire,
        String securityNumber,
        String cardNickname
) {

}