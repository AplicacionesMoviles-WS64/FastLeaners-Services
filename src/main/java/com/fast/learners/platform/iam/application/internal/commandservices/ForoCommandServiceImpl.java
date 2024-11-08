package com.fast.learners.platform.iam.application.internal.commandservices;

import com.fast.learners.platform.iam.domain.model.commands.CreateForoCommand;
import com.fast.learners.platform.iam.domain.model.entities.Foro;
import com.fast.learners.platform.iam.domain.model.aggregates.User;
import com.fast.learners.platform.iam.domain.services.ForoCommandService;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.ForoRepository;
import com.fast.learners.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForoCommandServiceImpl implements ForoCommandService {

    private final ForoRepository foroRepository;
    private final UserRepository userRepository;

    public ForoCommandServiceImpl(ForoRepository foroRepository, UserRepository userRepository) {
        this.foroRepository = foroRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Foro handle(CreateForoCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + command.userId()));


        Foro foro = new Foro(command.postTitle(), command.postBody(), user);


        return foroRepository.save(foro);
    }
}