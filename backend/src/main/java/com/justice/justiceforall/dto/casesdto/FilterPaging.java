package com.justice.justiceforall.dto.casesdto;

public record FilterPaging(
        int pageNumber,
        int pageSize,
        String sortBy,
        String orderBy
) {
}
