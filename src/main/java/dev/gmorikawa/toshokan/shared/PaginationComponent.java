package dev.gmorikawa.toshokan.shared;

import dev.gmorikawa.toshokan.shared.query.Pagination;

public class PaginationComponent {

    public final Boolean hasPrevious;
    public final Boolean hasNext;

    public final PaginationLink previousPage;
    public final PaginationLink currentPage;
    public final PaginationLink nextPage;

    public PaginationComponent(String basePath, Pagination pagination) {
        this.hasPrevious = pagination.page > 1;
        this.hasNext = true;

        this.previousPage = new PaginationLink(basePath, pagination.getPrevious());
        this.currentPage = new PaginationLink(basePath, pagination);
        this.nextPage = new PaginationLink(basePath, pagination.getNext());
    }
}
