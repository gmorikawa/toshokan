package dev.gmorikawa.toshokan.domain.file.storage;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.file.File;
import dev.gmorikawa.toshokan.domain.file.exception.FileNotFoundException;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
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
            MinioClient client = buildClient();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            PutObjectArgs args = PutObjectArgs.builder()
                                    .bucket(bucket)
                                    .object(file.getFilePath())
                                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                                    .build();
            client.putObject(args);
        } catch (MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    public InputStream retrive(File file) throws FileNotFoundException {
        try {
            MinioClient client = buildClient();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                throw new FileNotFoundException();
            }

            GetObjectArgs args = GetObjectArgs.builder()
                                    .bucket(bucket)
                                    .object(file.getFilePath())
                                    .build();

            return client.getObject(args);
        } catch (MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
            return null;
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private MinioClient buildClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
