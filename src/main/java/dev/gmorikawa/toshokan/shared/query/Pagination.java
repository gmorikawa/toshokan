package dev.gmorikawa.toshokan.shared.query;

public class Pagination {
    public final Integer page;
    public final Integer limit;

    public Pagination(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public Pagination getPrevious() {
        return new Pagination(page - 1, limit);
    }

    public Pagination getNext() {
        return new Pagination(page + 1, limit);
    }
}
