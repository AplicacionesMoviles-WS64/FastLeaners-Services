package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.aggregates.User;
import com.fast.learners.platform.iam.domain.model.commands.SetUserMembershipCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignInCommand;
import com.fast.learners.platform.iam.domain.model.commands.SignUpCommand;
import com.fast.learners.platform.iam.domain.model.commands.UpdateUserCommand;
import com.fast.learners.platform.iam.domain.model.entities.Membership;
import com.fast.learners.platform.iam.domain.services.UserCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.MembershipRepository;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Profile command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    /* private final HashingService hashingService; */

    private final MembershipRepository membershipRepository;

    public UserCommandServiceImpl(UserRepository userRepository /*, HashingService hashingService , TokenService tokenService */, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        /* this.hashingService = hashingService;*/
        this.membershipRepository = membershipRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {

        System.out.println(command.username());

        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("Profile not found");

        if (user.get().getPassword().equalsIgnoreCase(command.password())){
            return Optional.of(ImmutablePair.of(user.get(), ""));
        }else{
            return Optional.empty();
        }

    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        var memberships = command
                .memberships()
                .stream()
                .map(membership ->
                        membershipRepository
                                .findByName(membership.getName())
                                .orElseThrow(() -> new RuntimeException("Membership name not found")))
                .toList();
        var user = new User(command.username(), command.email(), command.password(), memberships);

        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<User> handle(SetUserMembershipCommand command) {

        var user = userRepository.findById(command.id()).get();

        Membership membership = Membership.toMembershipFromName(command.memberships().get(0));

        Set<Membership> memberships = new HashSet<>();

        memberships.add(membership);
        user.setMemberships(memberships);

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {

        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("Profile not found");

        var userResult = user.get();

        userResult.setUsername(command.username());
        userResult.setEmail(command.email());

        return Optional.of(userRepository.save(userResult));
    }
}