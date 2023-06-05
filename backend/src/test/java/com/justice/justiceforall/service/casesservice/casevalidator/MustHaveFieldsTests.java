package com.justice.justiceforall.service.casesservice.casevalidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.exception.InvalidCaseFieldException;
import com.justice.justiceforall.helper.cases.CreateCaseCommandFixture;

public class MustHaveFieldsTests {
	
	@Test
	void  ensure_null_and_short_title_raise_exception() {
		String title = "abcd";
		CreateCaseCommand input = CreateCaseCommandFixture.correctCaseCommand().withTitle(null);
		assertThrows(InvalidCaseFieldException.class,
                () -> new MustHaveFields().validate(input));
		
		CreateCaseCommand input2 = CreateCaseCommandFixture.correctCaseCommand().withTitle(title);
		assertThrows(InvalidCaseFieldException.class,
                () -> new MustHaveFields().validate(input2));
	}
}
