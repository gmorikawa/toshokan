package dev.gmorikawa.toshokan.config.storage;

public enum StorageType {
    LOCAL("local"),
    MINIO("minio");

    private final String label;

    private StorageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StorageType fromString(String text) {
        for (StorageType type : StorageType.values()) {
            if (type.label.equalsIgnoreCase(text)) {
                return type;
            }
        }

        return null;
    }
}
