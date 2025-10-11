package dev.gmorikawa.toshokan.config.storage;

import org.springframework.beans.factory.annotation.Value;

import dev.gmorikawa.storage.MinioStorage;

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
