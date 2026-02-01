package dev.gmorikawa.toshokan.infrastructure.storage.core;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import dev.gmorikawa.toshokan.domain.file.Storage;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;

public class MinioStorage implements Storage {

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

    @Override
    public void write(String path, InputStream stream, Integer length, Integer skip) {
        try {
            MinioClient client = buildClient();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(path)
                            .stream(stream, length, skip)
                            .build()
            );
        } catch (MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write(String path, InputStream stream, Integer length) {
        write(path, stream, length, -1);
    }

    @Override
    public InputStream read(String path) {
        try {
            MinioClient client = buildClient();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                throw new IOException("File not found");
            }

            return client.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(path)
                            .build()
            );
        } catch (MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
            return null;
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void remove(String path) {
        try {
            MinioClient client = buildClient();
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                throw new IOException("File not found");
            }

            client.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build()
            );
        } catch (MinioException e) {
            System.out.println(e.getMessage());
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    private MinioClient buildClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
