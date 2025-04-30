package dev.gmorikawa.toshokan.file.storage;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class LocalStorage {
    private final String rootDirectory;
    
    public LocalStorage(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void store(MultipartFile multipartFile) {
        try {
            String filepath = buildFilepath(rootDirectory, multipartFile.getOriginalFilename());
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
