package com.midas.yoon.config.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SimplePageRequest {
    private final int MAX_PAGE_SIZE = 20;

    private final int page;
    private final int size;

    public SimplePageRequest(int page, int size) {
        if (page < 1 || page > Integer.MAX_VALUE) page = 1;
        if (size < 1 || size > MAX_PAGE_SIZE) size = MAX_PAGE_SIZE;

        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public Pageable makePageable(String sortColumn) {
        return PageRequest.of(page - 1, size, Sort.by(sortColumn).descending());
    }
}
