package com.hrm.test.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingDTO<T> {

    private Iterable<T> content;

    private Integer pageNumber;

    private Integer totalPages;

    private Long totalElements;

}
