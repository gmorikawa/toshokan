package dev.gmorikawa.toshokan.domain.file.storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.file.File;

public class LocalStorage {
    private final String rootDirectory;
    
    public LocalStorage(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void store(File file, MultipartFile multipartFile) {
        try {
            String filepath = buildFilepath(rootDirectory, file.getFilePath());

            Files.createDirectories(Paths.get(rootDirectory.concat(file.getPath())));

            java.io.File binary = new java.io.File(filepath);
            binary.createNewFile();

            try (FileOutputStream fos = new FileOutputStream(binary)) {
                fos.write(multipartFile.getBytes());
            }
        } catch (IOException e) {
            System.out.println("LinuxStorage: IOException => " + e.getMessage());
        }
    }

    private String buildFilepath(String root, String path) {
        return new StringBuilder().append(root).append(path).toString();
    }
}
