package com.justice.justiceforall.helper.cases;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.entity.casesentity.Alegation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateCaseCommandFixture {
	public CreateCaseCommand correctCaseCommand() {
	    return new CreateCaseCommand(
				10L,
	        "Trafico de drogas na favela do novo engenho",
	        "Trafico",
	        "Estudante do terceiro ano do ensino médio que"
	        + "trabalha de jovem aprendiz esta preso por trafico"
	        + "sem provas conclusivas",
	        Alegation.INNOCENT,
	        "https://cdn.filestackcontent.com/AFrHW1QRsWxmu5ZLU2qg",
	        "4243243243-56.2023.4.04.7000",
	        true
	    );
	  }
	
	public CreateCaseCommand createWithInvalidFileUrl() {
		return correctCaseCommand().withEvidencesPDF("http://localhost:4200");
	}
	
	public CreateCaseCommand createWithInvalidTitle() {
		return correctCaseCommand().withTitle(null);
	}
}
