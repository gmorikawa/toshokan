package dev.gmorikawa.toshokan.domain.topic;

import java.util.List;
import java.util.StringTokenizer;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class TopicQueryFilter {
    private FilterCriteria<String> name;

    public TopicQueryFilter(List<String> nameFilters) {
        for (String filter : nameFilters) {
            if (this.name == null) {
                StringTokenizer tokenizer = new StringTokenizer(filter, "-");

                FilterOperator operator = FilterOperator.fromCode(tokenizer.nextToken());
                String value = tokenizer.nextToken();

                this.name = new FilterCriteria<>(operator, value);
            }
        }
    }

    public FilterCriteria<String> getName() {
        return name;
    }

    public void setName(FilterCriteria<String> name) {
        this.name = name;
    }
}
