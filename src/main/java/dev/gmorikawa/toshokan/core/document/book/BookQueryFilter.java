package dev.gmorikawa.toshokan.core.document.book;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class BookQueryFilter {
    private FilterCriteria<String> title;

    public BookQueryFilter(
        String title
    ) {
        if (title != null) {
            this.title = new FilterCriteria<>(FilterOperator.CONTAINS, title);
        }
    }

    public FilterCriteria<String> getTitle() {
        return title;
    }
}
