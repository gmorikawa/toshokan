package dev.gmorikawa.toshokan.infrastructure.storage.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.gmorikawa.toshokan.core.file.Storage;

@Configuration
public class StorageConfiguration {

    private final LocalStorageConfiguration localStorageConfiguration;
    private final MinioStorageConfiguration minioStorageConfiguration;

    @Value("${storage.type}")
    private String type;

    public StorageConfiguration(
        LocalStorageConfiguration localStorageConfiguration,
        MinioStorageConfiguration minioStorageConfiguration
    ) {
        this.localStorageConfiguration = localStorageConfiguration;
        this.minioStorageConfiguration = minioStorageConfiguration;
    }

    @Bean
    public Storage appStorage() {
        return switch (StorageType.fromString(type)) {
            case LOCAL -> localStorageConfiguration.configure();
            case MINIO -> minioStorageConfiguration.configure();
            default -> null;
        };
    }
}
