package com.justice.justiceforall.service.userservice;

import com.justice.justiceforall.controllers.UserController;
import com.justice.justiceforall.dto.userdto.CreateUserCommand;
import com.justice.justiceforall.dto.userdto.User;
import com.justice.justiceforall.entity.userentity.UserEntity;
import com.justice.justiceforall.repository.UsersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Override
  public User createUser(CreateUserCommand createUserCommand) {
    logger.info(
        "Creating a new user of type {} and email {}",
        createUserCommand.type(),
        createUserCommand.email()
    );
    CreateUserValidator.validateNewUser(createUserCommand);
    var savedEntity = usersRepository.save(getUserEntity(createUserCommand));
    logger.info("Created a new User with ID {}", savedEntity.getId());
    return new User(savedEntity);
  }

  private UserEntity getUserEntity(CreateUserCommand createUserCommand) {
    return UserEntity.builder()
        .firstName(createUserCommand.firstName())
        .lastName(createUserCommand.lastName())
        .password(createUserCommand.password())
        .email(createUserCommand.email())
        .userType(createUserCommand.type())
        .cpf(createUserCommand.cpf())
        .oab(createUserCommand.oab())
        .build();
  }
}
