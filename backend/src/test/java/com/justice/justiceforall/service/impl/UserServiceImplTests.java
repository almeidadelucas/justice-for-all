package com.justice.justiceforall.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.exception.InvalidUserFieldException;
import com.justice.justiceforall.helper.CreateUserCommandFixture;
import com.justice.justiceforall.helper.UserEntityFixture;
import com.justice.justiceforall.helper.UserFixture;
import com.justice.justiceforall.repository.UsersRepository;
import com.justice.justiceforall.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTests {

  @Autowired
  private UserService userService;

  @MockBean
  private UsersRepository usersRepository;

  @Test
  void ensureTheUsersRepositoryIsProperlyCalledWhenCreatingAUser() {
    var createUserCommand = CreateUserCommandFixture.correctClientCommand();
    when(usersRepository.save(any())).thenReturn(UserEntityFixture.userWithId());
    var response = userService.createUser(createUserCommand);
    verify(usersRepository, times(1)).save(UserEntityFixture.userWithoutId());
    assertEquals(UserFixture.correctUser(), response);
  }

  @ParameterizedTest
  @MethodSource("invalidCreateUserCommands")
  void ensureACommandWithInvalidValuesThrowsException(CreateUserCommand invalidCommand) {
    assertThrows(InvalidUserFieldException.class, () -> userService.createUser(invalidCommand));
    verify(usersRepository, times(0)).save(any());
  }

  private static List<CreateUserCommand> invalidCreateUserCommands() {
    return List.of(
        CreateUserCommandFixture.commandWithInvalidFirstName(),
        CreateUserCommandFixture.commandWithInvalidLastName(),
        CreateUserCommandFixture.commandWithInvalidEmail(),
        CreateUserCommandFixture.commandWithInvalidPassword(),
        CreateUserCommandFixture.commandWithInvalidCPF(),
        CreateUserCommandFixture.commandWithInvalidOAB()
    );
  }
}
