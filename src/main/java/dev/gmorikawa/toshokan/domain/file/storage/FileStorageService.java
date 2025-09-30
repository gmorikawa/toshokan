package dev.gmorikawa.toshokan.domain.file.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.file.File;

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

    public void store(File file, MultipartFile binary) {
        // new MinioStorage(endpoint, accessKey, secretKey, bucket).store(file, binary);
        new LocalStorage(rootDirectory).store(file, binary);
    }

    public InputStream retrive(File file) {
        // return new MinioStorage(endpoint, accessKey, secretKey, bucket).retrive(file);
        return new LocalStorage(rootDirectory).retrive(file);
    }
}
