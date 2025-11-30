package dev.gmorikawa.toshokan.infrastructure.config.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import dev.gmorikawa.toshokan.infrastructure.storage.MinioStorage;

@Configuration
public class MinioStorageConfiguration {

    @Value("${storage.minio.endpoint}")
    private String endpoint;

    @Value("${storage.minio.access-key}")
    private String accessKey;

    @Value("${storage.minio.secret-key}")
    private String secretKey;

    @Value("${storage.minio.bucket}")
    private String bucket;

    public MinioStorage configure() {
        return new MinioStorage(endpoint, accessKey, secretKey, bucket);
    }
}
