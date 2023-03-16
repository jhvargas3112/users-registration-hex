package com.company.signup.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ResultsPage<T> {

  List<T> elements;

  Long totalResults;

  Integer size;

  Integer currentPage;

  Integer totalPages;

}
