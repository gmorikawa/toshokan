package dev.gmorikawa.toshokan.shared;

import dev.gmorikawa.toshokan.shared.query.Pagination;

public class PaginationLink {
    public final String basePath;
    public final Pagination pagination;

    public PaginationLink(String basePath, Pagination pagination) {
        this.basePath = basePath;
        this.pagination = pagination;
    }

    public String getLink() {
        return basePath
            .concat(String.format("?page=%d&size=%d", pagination.page, pagination.size));
    }
}
