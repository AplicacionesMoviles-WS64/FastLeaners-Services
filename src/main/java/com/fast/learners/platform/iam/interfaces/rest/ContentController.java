package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.commands.CreateContentCommand;
import com.fast.learners.platform.iam.domain.model.entities.Content;
import com.fast.learners.platform.iam.domain.model.queries.GetAllContentByRepositoryId;
import com.fast.learners.platform.iam.domain.services.ContentCommandService;
import com.fast.learners.platform.iam.domain.services.ContentQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateContentResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.ContentResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.CreateContentCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.ContentResourceFromEntityAssembler;

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
@RequestMapping("/api/v1/repositories/{repositoryId}/contents")
@Tag(name = "Contents", description = "Operations related to repository contents")
public class ContentController {

    private final ContentCommandService contentCommandService;
    private final ContentQueryService contentQueryService;

    public ContentController(ContentCommandService commandService,
                             ContentQueryService queryService) {
        this.contentCommandService = commandService;
        this.contentQueryService = queryService;
    }

    /**
     * GET /api/v1/repositories/{repositoryId}/contents
     * Obtiene todos los contenidos de un repositorio específico.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de contenidos obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ContentResource>> getAllContentsByRepositoryId(@PathVariable Long repositoryId) {
        GetAllContentByRepositoryId query = new GetAllContentByRepositoryId(repositoryId);
        List<Content> contents = contentQueryService.handle(query);
        List<ContentResource> resources = contents.stream()
                .map(ContentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    /**
     * POST /api/v1/repositories/{repositoryId}/contents
     * Crea un nuevo contenido en un repositorio específico.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contenido creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ContentResource> createContent(@PathVariable Long repositoryId, @RequestBody CreateContentResource createResource) {
        CreateContentCommand command = CreateContentCommandFromResourceAssembler
                .toCommandFromResource(createResource, repositoryId);

        contentCommandService.handle(command);

        ContentResource resource = ContentResourceFromEntityAssembler
                .toResourceFromEntity(new Content(command, null)); // Repository se asigna en el servicio
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}