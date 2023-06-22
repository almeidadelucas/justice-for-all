package com.justice.justiceforall.helper.cases;

import com.justice.justiceforall.dto.casesdto.Case;
import com.justice.justiceforall.entity.casesentity.Alegation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CaseFixture {
	public Case correctCase() {
	    return new Case(
	    		1L,
				10L,
				null,
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
	
	public Case[] correctCases() {
		Case case1 = correctCase().withCaseId(1l);
		Case case2 = correctCase().withCaseId(2l).withCategory("furto");
		Case case3 = correctCase().withCaseId(3l);
		
		Case[] cases = {case1, case2, case3};
		return cases;
	}
	
	public Case[] sameCategoryCases() {
		Case case1 = correctCase().withCaseId(1l).withCategory("furto");
		Case case2 = correctCase().withCaseId(2l).withCategory("furto");
		Case case3 = correctCase().withCaseId(3l).withCategory("furto");
		
		Case[] cases = {case1, case2, case3};
		return cases;
	}
}
