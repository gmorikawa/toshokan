package dev.gmorikawa.toshokan.domain.file.storage;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.storage.Storage;
import dev.gmorikawa.toshokan.domain.file.File;

@Service
public class FileStorageService {
    private final Storage storage;

    public FileStorageService(Storage storage) {
        this.storage = storage;
    }

    public void store(File file, MultipartFile binary) {
        try {
            storage.write(file.getFilePath(), binary.getInputStream(), (int)binary.getSize());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public InputStream retrive(File file) {
        return storage.read(file.getFilePath());
    }
}
