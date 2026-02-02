package dev.gmorikawa.toshokan.shared.query;

public enum FilterOperator {
    CONTAINS("contains"),
    EQUALS("eq"),
    NOT_EQUALS("ne"),
    GREATER_THAN("gt"),
    LESS_THAN("lt"),
    GREATER_THAN_OR_EQUALS("gte"),
    LESS_THAN_OR_EQUALS("lte"),
    IN("in"),
    NOT_IN("nin");

    private final String code;

    FilterOperator(String code) {
        this.code = code;
    }

    public String getOperatorCode() {
        return code;
    }

    public static FilterOperator fromCode(String code) {
        for (FilterOperator operator : FilterOperator.values()) {
            if (operator.getOperatorCode().equalsIgnoreCase(code)) {
                return operator;
            }
        }

        throw new IllegalArgumentException("Unknown operator code: " + code);
    }
}