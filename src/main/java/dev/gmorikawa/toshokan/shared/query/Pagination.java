package dev.gmorikawa.toshokan.shared.query;

public class Pagination {
    public final Integer page;
    public final Integer size;

    public Pagination(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Pagination getPrevious() {
        return new Pagination(page - 1, size);
    }

    public Pagination getNext() {
        return new Pagination(page + 1, size);
    }
}
