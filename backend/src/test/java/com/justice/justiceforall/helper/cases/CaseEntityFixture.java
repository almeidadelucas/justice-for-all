package com.justice.justiceforall.helper.cases;


import com.justice.justiceforall.entity.casesentity.Alegation;
import com.justice.justiceforall.entity.casesentity.CaseEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CaseEntityFixture {
	public CaseEntity caseWithoutId() {
	    return new CaseEntity(
	    		null,
	    		"Trafico de drogas na favela do novo engenho",
		        "Trafico",
		        "Estudante do terceiro ano do ensino médio que"
		        + "trabalha de jovem aprendiz esta preso por trafico"
		        + "sem provas conclusivas",
		        Alegation.INNOCENT,
		        "https://cdn.filestackcontent.com/AFrHW1QRsWxmu5ZLU2qg",
		        "https://cdn.filestackcontent.com/AFrH432W1Q432u5ZL432",
		        "4243243243-56.2023.4.04.7000",
		        true
	    );
	  }

	  public CaseEntity caseWithId() {
	    return new CaseEntity(
	    		1L,
	    		"Trafico de drogas na favela do novo engenho",
		        "Trafico",
		        "Estudante do terceiro ano do ensino médio que"
		        + "trabalha de jovem aprendiz esta preso por trafico"
		        + "sem provas conclusivas",
		        Alegation.INNOCENT,
		        "https://cdn.filestackcontent.com/AFrHW1QRsWxmu5ZLU2qg",
		        "https://cdn.filestackcontent.com/AFrH432W1Q432u5ZL432",
		        "4243243243-56.2023.4.04.7000",
		        true
	    );
	  }
	  
	  public CaseEntity[] casesWithId() {
		  CaseEntity case1 = caseWithId().withId(1L);
		  CaseEntity case2 = caseWithId().withId(2L).withCategory("furto");
		  CaseEntity case3 = caseWithId().withId(3L);
		  
		  CaseEntity[] cases = {case1, case2, case3};
		  
		  return cases;
	  }
	  
	  public CaseEntity[] casesFurto() {
		  CaseEntity case1 = caseWithId().withId(1L).withCategory("furto");
		  CaseEntity case2 = caseWithId().withId(2L).withCategory("furto");
		  CaseEntity case3 = caseWithId().withId(3L).withCategory("furto");
		  
		  CaseEntity[] cases = {case1, case2, case3};
		  
		  return cases;
	  }
}
