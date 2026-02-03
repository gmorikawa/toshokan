package dev.gmorikawa.toshokan.core.author;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class AuthorQueryFilter {
    private FilterCriteria<String> fullname;

    public AuthorQueryFilter(
        String fullname
    ) {
        if (fullname != null) {
            this.fullname = new FilterCriteria<>(FilterOperator.CONTAINS, fullname);
        }
    }

    public FilterCriteria<String> getFullname() {
        return fullname;
    }
}
