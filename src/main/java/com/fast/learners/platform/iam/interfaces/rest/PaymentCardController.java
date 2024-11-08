package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.commands.CreatePaymentCardCommand;
import com.fast.learners.platform.iam.domain.model.entities.PaymentCard;
import com.fast.learners.platform.iam.domain.model.queries.GetAllPaymentCardQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetPaymentCardByIdQuery;
import com.fast.learners.platform.iam.domain.services.PaymentCardCommandService;
import com.fast.learners.platform.iam.domain.services.PaymentCardQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreatePaymentCardResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.PaymentCardResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.CreatePaymentCardCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.PaymentCardFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/paymentCards")
@Tag(name = "Payment Cards", description = "Operations related to payment cards")
public class PaymentCardController {

    private final PaymentCardCommandService paymentCardCommandService;
    private final PaymentCardQueryService paymentCardQueryService;

    public PaymentCardController(PaymentCardCommandService commandService,
                                 PaymentCardQueryService queryService) {
        this.paymentCardCommandService = commandService;
        this.paymentCardQueryService = queryService;
    }

    /**
     * GET /api/v1/paymentCards
     * Obtiene todas las tarjetas de pago.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<PaymentCardResource>> getAllPaymentCards() {
        GetAllPaymentCardQuery query = new GetAllPaymentCardQuery();
        List<PaymentCard> paymentCards = paymentCardQueryService.handle(query);

        List<PaymentCardResource> resources = paymentCards.stream()
                .map(PaymentCardFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    /**
     * GET /api/v1/paymentCards/{id}
     * Obtiene una tarjeta de pago por su ID.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentCardResource> getPaymentCardById(@PathVariable Long id) {
        GetPaymentCardByIdQuery query = new GetPaymentCardByIdQuery(id);
        Optional<PaymentCard> paymentCard = paymentCardQueryService.handle(query);

        if (paymentCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PaymentCardResource resource = PaymentCardFromEntityAssembler.toResourceFromEntity(paymentCard.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * POST /api/v1/paymentCards
     * Crea una nueva tarjeta de pago.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarjeta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<PaymentCardResource> createPaymentCard(@RequestBody CreatePaymentCardResource createResource) {
        CreatePaymentCardCommand command = CreatePaymentCardCommandFromResourceAssembler.toCommandFromResource(createResource);
        Optional<PaymentCard> paymentCard = paymentCardCommandService.handle(command);

        if (paymentCard.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PaymentCardResource resource = PaymentCardFromEntityAssembler.toResourceFromEntity(paymentCard.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
