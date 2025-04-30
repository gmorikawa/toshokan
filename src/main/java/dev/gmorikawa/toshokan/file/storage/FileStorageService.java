package dev.gmorikawa.toshokan.file.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Value("${storage.minio.endpoint}")
    private String endpoint;

    @Value("${storage.minio.access-key}")
    private String accessKey;

    @Value("${storage.minio.secret-key}")
    private String secretKey;

    @Value("${storage.minio.bucket}")
    private String bucket;

    @Value("${storage.local.root-directory}")
    private String rootDirectory;

    public void store(MultipartFile multipartFile) {
        new MinioStorage(endpoint, accessKey, secretKey, bucket).store(multipartFile);
        new LocalStorage(rootDirectory).store(multipartFile);
    }
}
