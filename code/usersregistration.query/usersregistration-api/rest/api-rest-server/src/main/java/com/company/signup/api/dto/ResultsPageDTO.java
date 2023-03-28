package com.company.signup.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultsPageDTO<T> {

  List<T> elements;

  Long totalResults;

  Integer size;

  Integer currentPage;

  Integer totalPages;

}
