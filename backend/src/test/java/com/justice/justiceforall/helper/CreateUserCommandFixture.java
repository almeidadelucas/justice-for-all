package com.justice.justiceforall.helper;

import com.justice.justiceforall.dto.command.CreateUserCommand;
import com.justice.justiceforall.entity.UserType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateUserCommandFixture {

  public CreateUserCommand correctCommand() {
    return new CreateUserCommand(
        "First Name",
        "Last Name",
        "Email@email.com",
        "password",
        UserType.CLIENT,
        "10439284154",
        null
    );
  }

  public CreateUserCommand commandWithInvalidFirstName() {
    return correctCommand().withFirstName("a");
  }

  public CreateUserCommand commandWithInvalidLastName() {
    return correctCommand().withLastName("a");
  }

  public CreateUserCommand commandWithInvalidEmail() {
    return correctCommand().withEmail("email.com");
  }

  public CreateUserCommand commandWithInvalidPassword() {
    return correctCommand().withPassword("pass");
  }

  public CreateUserCommand commandWithInvalidCPF() {
    return correctCommand().withCpf("123").withType(UserType.CLIENT);
  }

  public CreateUserCommand commandWithInvalidOAB() {
    return correctCommand().withOab("ab23").withType(UserType.LAWYER);
  }
}
