package com.demo.demo.responseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Getter
public class PageResponse<T> implements Serializable {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private T items;
}
