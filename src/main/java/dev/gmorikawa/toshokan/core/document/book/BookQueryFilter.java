package dev.gmorikawa.toshokan.core.document.book;

import java.util.List;
import java.util.StringTokenizer;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class BookQueryFilter {
    private FilterCriteria<String> title;

    public BookQueryFilter(List<String> titleFilters) {
        for (String filter : titleFilters) {
            if (this.title == null) {
                StringTokenizer tokenizer = new StringTokenizer(filter, "-");

                FilterOperator operator = FilterOperator.fromCode(tokenizer.nextToken());
                String value = tokenizer.nextToken();

                this.title = new FilterCriteria<>(operator, value);
            }
        }
    }

    public FilterCriteria<String> getTitle() {
        return title;
    }

    public void setTitle(FilterCriteria<String> title) {
        this.title = title;
    }
}
