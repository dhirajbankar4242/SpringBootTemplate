package com.prime.app.module.common.dto;

import com.prime.app.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PaginationRequest {

    private String search;

    private Integer pageSize;

    private Integer offset;

    private String sortBy;

    private String sortDir;

    public String getSortBy() {

        if (StringUtils.isEmpty(sortBy)) {
            return "created_date";
        }

        return sortBy;
    }

    public String getSortDir() {
        if (StringUtils.isEmpty(sortDir)) {
            return "DESC";
        }

        return sortDir;
    }

    public Sort getSort() {
        return getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(getSortBy()).ascending() : Sort.by(getSortBy()).descending();
    }

    public Pageable getPageable() {
        return PageRequest.of(this.offset, this.pageSize, getSort());
    }

}
