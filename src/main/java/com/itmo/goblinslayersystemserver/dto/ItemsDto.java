package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.Page;

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
