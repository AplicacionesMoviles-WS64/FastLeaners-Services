package com.fast.learners.platform.iam.interfaces.rest;

import com.fast.learners.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.fast.learners.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.fast.learners.platform.iam.domain.services.UserCommandService;
import com.fast.learners.platform.iam.domain.services.UserQueryService;
import com.fast.learners.platform.iam.interfaces.rest.resources.SetUserMembershipResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.UpdateUserResource;
import com.fast.learners.platform.iam.interfaces.rest.resources.UserResource;
import com.fast.learners.platform.iam.interfaces.rest.transform.SetUserMembershipCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.UpdateResourceCommandFromResourceAssembler;
import com.fast.learners.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a REST controller that exposes the profiles resource.
 * It includes the following operations:
 * - GET /api/v1/profiles: returns all the profiles
 * - GET /api/v1/profiles/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Profile Management Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PutMapping("/setMembership")
    public ResponseEntity<UserResource> updateMembershipUser(@RequestBody SetUserMembershipResource setUserMembershipResource) {

        System.out.println(setUserMembershipResource);
        var user = userQueryService.handle(new GetUserByIdQuery(setUserMembershipResource.id()));

        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var setUserMembershipCommand = SetUserMembershipCommandFromResourceAssembler
                .toCommandFromResource(setUserMembershipResource);

        var userResult = UserResourceFromEntityAssembler
                .toResourceFromEntity(userCommandService.handle(setUserMembershipCommand).get());

        return ResponseEntity.ok(userResult);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserResource> updateUser(@RequestBody UpdateUserResource updateUserResource) {

        var user = userQueryService.handle(new GetUserByUsernameQuery(updateUserResource.username()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updateUserCommand = UpdateResourceCommandFromResourceAssembler.toCommandFromResource(updateUserResource);

        var updatedUser = userCommandService.handle(updateUserCommand);

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser.get());

        return ResponseEntity.ok(userResource);
    }


    /**
     * This method returns all the profiles.
     * @return a list of user resources
     * @see UserResource
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserResource
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}