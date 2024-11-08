package com.fast.learners.platform.iam.interfaces.rest.resources;

public record CreatePaymentCardResource (String titleName,
                                                String numberCard,
                                                String email,
                                                String dateExpire,
                                                String securityNumber,
                                                String cardNickname){
}
