package dev.gmorikawa.toshokan.core.author;

import java.util.List;
import java.util.StringTokenizer;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class AuthorQueryFilter {
    private FilterCriteria<String> fullname;

    public AuthorQueryFilter(List<String> fullnameFilters) {
        for (String filter : fullnameFilters) {
            if (this.fullname == null) {
                StringTokenizer tokenizer = new StringTokenizer(filter, "-");

                FilterOperator operator = FilterOperator.fromCode(tokenizer.nextToken());
                String value = tokenizer.nextToken();

                this.fullname = new FilterCriteria<>(operator, value);
            }
        }
    }

    public FilterCriteria<String> getFullname() {
        return fullname;
    }

    public void setFullname(FilterCriteria<String> fullname) {
        this.fullname = fullname;
    }
}
