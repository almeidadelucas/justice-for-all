package com.justice.justiceforall.service.casesservice.casevalidator;

import com.justice.justiceforall.dto.casesdto.CreateCaseCommand;
import com.justice.justiceforall.exception.InvalidCaseFieldException;
import com.justice.justiceforall.service.util.BaseCreatorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFormatValidator extends BaseCreatorHandler<CreateCaseCommand>{

	private static final String FILESTACK_REGEX = "^https?://cdn\\.filestackcontent\\.com/[A-Za-z0-9]+$";
	
	@Override
	public void validate(CreateCaseCommand input) {
		if(!this.isFileStackURL(input.evidencesPDF())) {
			throw new InvalidCaseFieldException("The files url are not in the rigth format.");
		};
	    toNext(input);
	}
	private  boolean isFileStackURL(String url) {
        Pattern pattern = Pattern.compile(FILESTACK_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
