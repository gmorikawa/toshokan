package dev.gmorikawa.toshokan.domain.file.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

            FileOutputStream stream = new FileOutputStream(binary);
            stream.write(multipartFile.getBytes());
            stream.close();
        } 
        catch(FileNotFoundException e) {
            System.out.println("LinuxStorage: FileNotFoundException => " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("LinuxStorage: IOException => " + e.getMessage());
        }
    }

    public InputStream retrive(File file) {
        try {
            String filepath = buildFilepath(rootDirectory, file.getFilePath());
            
            FileInputStream stream = new FileInputStream(filepath);

            return stream;
        }
        catch(FileNotFoundException e) {
            System.out.println("LinuxStorage: FileNotFoundException => " + e.getMessage());
            return null;
        }
    }

    private String buildFilepath(String root, String path) {
        return new StringBuilder().append(root).append(path).toString();
    }
}
