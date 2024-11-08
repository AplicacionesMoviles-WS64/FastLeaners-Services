package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.services.ForoCommandService;
import com.fast.learners.platform.iam.domain.services.ForoQueryService;
import com.fast.learners.platform.iam.domain.model.commands.CreateForoCommand;
import com.fast.learners.platform.iam.domain.model.entities.Foro;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByTitleContainingOrBodyContainingQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetAllForosByUserNameQuery;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateForoResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.ForoResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.CreateForoCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.ForoResourceFromEntityAssembler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/foros")
public class ForoController {

    private final ForoCommandService foroCommandService;
    private final ForoQueryService foroQueryService;

    public ForoController(ForoCommandService foroCommandService, ForoQueryService foroQueryService) {
        this.foroCommandService = foroCommandService;
        this.foroQueryService = foroQueryService;
    }

    /**
     * Crea un nuevo post en el foro.
     */
    @PostMapping
    public ResponseEntity<ForoResource> createForo(@RequestBody @Valid CreateForoResource createForoResource) {
        // Convierte CreateForoResource a CreateForoCommand
        CreateForoCommand command = CreateForoCommandFromResourceAssembler.toCommandFromResource(createForoResource);
        Foro foro = foroCommandService.handle(command);

        // Convierte la entidad Foro a ForoResource para la respuesta
        ForoResource foroResource = ForoResourceFromEntityAssembler.toResourceFromEntity(foro);
        return ResponseEntity.ok(foroResource);
    }


    /**
     * Busca posts que contengan una palabra clave en el título o el cuerpo.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ForoResource>> searchForosByTitleOrBody(@RequestParam String keyword) {
        var query = new GetAllForosByTitleContainingOrBodyContainingQuery(keyword, keyword);
        List<Foro> foros = foroQueryService.handle(query);
        List<ForoResource> foroResources = foros.stream()
                .map(ForoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(foroResources);
    }

    /**
     * Obtiene todos los posts creados por un usuario específico usando su ID.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ForoResource>> getForosByUserId(@PathVariable Long userId) {
        var query = new GetAllForosByUserIdQuery(userId);
        List<Foro> foros = foroQueryService.handle(query);
        List<ForoResource> foroResources = foros.stream()
                .map(ForoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(foroResources);
    }
/*
    @GetMapping("/username/{username}")
    public ResponseEntity<List<ForoResource>> getForosByUserName(@PathVariable String username) {
        var query = new GetAllForosByUserNameQuery(username);
        List<Foro> foros = foroQueryService.handle(query);
        List<ForoResource> foroResources = foros.stream()
                .map(ForoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(foroResources);
    }

 */
}