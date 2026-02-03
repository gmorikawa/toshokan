package dev.gmorikawa.toshokan.core.topic;

import dev.gmorikawa.toshokan.shared.query.FilterCriteria;
import dev.gmorikawa.toshokan.shared.query.FilterOperator;

public class TopicQueryFilter {

    private FilterCriteria<String> name;

    public TopicQueryFilter(
        String name
    ) {
        if (name != null) {
            this.name = new FilterCriteria<>(FilterOperator.CONTAINS, name);
        }
    }

    public FilterCriteria<String> getName() {
        return name;
    }
}
