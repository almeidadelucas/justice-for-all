package com.justice.justiceforall.helper;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.entity.UserType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateUserCommandFixture {

  public CreateUserCommand correctClientCommand() {
    return new CreateUserCommand(
        "First Name",
        "Last Name",
        "Email@email.com",
        "password",
        UserType.CLIENT,
        "32227526726",
        null
    );
  }

  public CreateUserCommand correctLawyerCommand() {
    return new CreateUserCommand(
            "First Name",
            "Last Name",
            "Email@email.com",
            "password",
            UserType.LAWYER,
            null,
            "AB123456"
    );
  }

  public CreateUserCommand commandWithInvalidFirstName() {
    return correctClientCommand().withFirstName("a");
  }

  public CreateUserCommand commandWithInvalidLastName() {
    return correctClientCommand().withLastName("a");
  }

  public CreateUserCommand commandWithInvalidEmail() {
    return correctClientCommand().withEmail("email.com");
  }

  public CreateUserCommand commandWithInvalidPassword() {
    return correctClientCommand().withPassword("pass");
  }

  public CreateUserCommand commandWithInvalidCPF() {
    return correctClientCommand().withCpf("123").withType(UserType.CLIENT);
  }

  public CreateUserCommand commandWithInvalidOAB() {
    return correctClientCommand().withOab("ab23").withType(UserType.LAWYER);
  }
}
