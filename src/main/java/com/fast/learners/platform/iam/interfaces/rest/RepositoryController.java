package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.commands.CreateRepositoryCommand;
import com.fast.learners.platform.iam.domain.model.entities.Repository;
import com.fast.learners.platform.iam.domain.model.queries.GetAllRepositoriesQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetRepositoryByIdQuery;
import com.fast.learners.platform.iam.domain.services.RepositoryCommandService;
import com.fast.learners.platform.iam.domain.services.RepositoryQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.CreateRepositoryResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.RepositoryResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.CreateRepositoryCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.RepositoryResourceFromEntityAssembler;

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
@RequestMapping("/api/v1/repository")
@Tag(name = "Repositories", description = "Operations related to repositories")

public class RepositoryController {

    private final RepositoryCommandService repositoryCommandService;
    private final RepositoryQueryService repositoryQueryService;

    public RepositoryController(RepositoryCommandService commandService,
                                RepositoryQueryService queryService) {
        this.repositoryCommandService = commandService;
        this.repositoryQueryService = queryService;
    }

    /**
     * GET /api/v1/repository
     * Obtiene todos los repositorios.
     */

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de repositorios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Repository>> getAllRepositories() {
        GetAllRepositoriesQuery query = new GetAllRepositoriesQuery();

        List<Repository> repositories = repositoryQueryService.handle(query);
        return ResponseEntity.ok(repositories);
    }

    /**
     * GET /api/v1/repository/{id}
     * Obtiene un repositorio por su ID.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Repositorio obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Repositorio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RepositoryResource> getRepositoryById(@PathVariable Long id) {

        GetRepositoryByIdQuery query = new GetRepositoryByIdQuery(id);
        Optional<Repository> repository = repositoryQueryService.handle(query);

        if (repository.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        RepositoryResource resource = RepositoryResourceFromEntityAssembler
                    .toResourceFromEntity(repository.get());
        return ResponseEntity.ok(resource);

    }

    /**
     * POST /api/v1/repository
     * Crea un nuevo repositorio.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repositorio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<RepositoryResource> createRepository(@RequestBody CreateRepositoryResource createResource) {

        CreateRepositoryCommand command = CreateRepositoryCommandFromResourceAssembler
                .toCommandFromResource(createResource);

        Optional<Repository> repository = repositoryCommandService.handle(command);

        if (repository.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RepositoryResource resource = RepositoryResourceFromEntityAssembler
                .toResourceFromEntity(repository.get());

        return ResponseEntity.ok(resource);

    }
}
