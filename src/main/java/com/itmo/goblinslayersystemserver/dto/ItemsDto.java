package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class ItemsDto<T> {
    @NonNull
    private Integer currentPage;
    @NonNull
    private Long totalItems;
    @NonNull
    private Integer totalPages;
    @NonNull
    private List<T> items;
}
