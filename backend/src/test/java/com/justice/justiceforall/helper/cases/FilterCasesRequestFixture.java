package com.justice.justiceforall.helper.cases;

import com.justice.justiceforall.dto.casesdto.FilterCasesRequest;
import com.justice.justiceforall.dto.casesdto.FilterPaging;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.StringUtils;

import java.util.Random;

@UtilityClass
public class FilterCasesRequestFixture {

    public FilterCasesRequest getRandom() {
        return new FilterCasesRequest(
                new Random().nextBoolean(),
                new Random().nextLong(),
                new Random().nextLong(),
                "furto",
                "caso furto",
                new FilterPaging(
                        new Random().nextInt(1, 20),
                        new Random().nextInt(1, 200),
                        "case_id",
                        "asc"
                )
        );
    }
}
