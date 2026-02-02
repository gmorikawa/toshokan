package dev.gmorikawa.toshokan.shared.query;

public class FilterCriteria<Value> {
    private FilterOperator operator;
    private Value value;

    public FilterCriteria(FilterOperator operator, Value value) {
        this.operator = operator;
        this.value = value;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public void setOperator(FilterOperator operator) {
        this.operator = operator;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
