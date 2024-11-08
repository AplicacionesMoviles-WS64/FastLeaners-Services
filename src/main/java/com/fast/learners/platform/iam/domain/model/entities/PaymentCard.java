package com.fast.learners.platform.iam.domain.model.entities;

import com.fast.learners.platform.iam.domain.model.commands.CreatePaymentCardCommand;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter

public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titleName;

    @Column(nullable = false, unique = true)
    private String numberCard;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String dateExpire;

    @Column(nullable = false)
    private String securityNumber;

    @Column
    private String cardNickname;

    protected PaymentCard(){}
    // Constructor que utiliza el comando para crear una nueva tarjeta
    public PaymentCard(CreatePaymentCardCommand command) {
        this.titleName = command.titleName();
        this.numberCard = command.numberCard();
        this.email = command.email();
        this.dateExpire = command.dateExpire();
        this.securityNumber = command.securityNumber();
        this.cardNickname = command.cardNickname();
    }
}
