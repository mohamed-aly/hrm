package com.hrm.test.shared.util;

import com.hrm.test.business.dto.PagingDTO;

import java.util.List;

public class Util {
    public static String buildQuery(String query) {
        if (query != null && !query.isEmpty()) {
            query = "%" + query + "%";
        }

        return query;
    }

    public static <S> PagingDTO<S> buildPagingDto(List<S> data, int pageNumber, int totalPages, long totalElements) {
        return PagingDTO.<S>builder().totalPages(totalPages).pageNumber(pageNumber)
                .content(data).totalElements(totalElements).build();
    }
}
