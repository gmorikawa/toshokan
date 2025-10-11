package dev.gmorikawa.toshokan.config.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.gmorikawa.storage.Storage;

@Configuration
public class StorageConfiguration {

    @Value("${storage.type}")
    private String type;

    @Bean
    public Storage appStorage() {
        return switch (StorageType.fromString(type)) {
            case LOCAL -> new LocalStorageConfiguration().configure();
            case MINIO -> new MinioStorageConfiguration().configure();
            default -> null;
        };
    }
}
