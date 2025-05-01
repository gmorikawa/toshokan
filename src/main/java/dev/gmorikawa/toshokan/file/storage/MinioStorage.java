package dev.gmorikawa.toshokan.file.storage;

import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.file.File;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;

public class MinioStorage {
    private final String endpoint;
    private final String accessKey;
    private final String secretKey;
    private final String bucket;

    public MinioStorage(String endpoint, String accessKey, String secretKey, String bucket) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
    }

    public void store(File file, MultipartFile multipartFile) {
        try {
            MinioClient client = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if(!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            PutObjectArgs putCommand =
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(file.getFilePath())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .build();
            client.putObject(putCommand);
        } catch(MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch(Exception e) {
            System.out.println("GENERIC: " + e.getClass().toString());
        }
    }
}
