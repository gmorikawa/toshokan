package dev.gmorikawa.toshokan.infrastructure.storage.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import dev.gmorikawa.toshokan.core.file.Storage;

public class LocalStorage implements Storage {

    private final String rootDirectory;

    public LocalStorage(String rootDirectory) {
        this.rootDirectory = rootDirectory.charAt(rootDirectory.length() - 1) == '/'
                ? rootDirectory.substring(0, rootDirectory.length() - 1)
                : rootDirectory;
    }

    @Override
    public void write(String path, InputStream stream, Integer length, Integer skip) {
        try {
            String filepath = buildFilepath(rootDirectory, path);
            String fileDirectory = filepath.substring(0, filepath.lastIndexOf('/'));
            Files.createDirectories(Paths.get(fileDirectory));

            File binary = new File(filepath);
            binary.createNewFile();

            try (FileOutputStream output = new FileOutputStream(binary)) {
                output.write(stream.readNBytes(length));
            }
        } catch (FileNotFoundException e) {
            System.out.println("LinuxStorage: FileNotFoundException => " + e.getMessage());
        } catch (IOException e) {
            System.out.println("LinuxStorage: IOException => " + e.getMessage());
        }
    }

    @Override
    public void write(String path, InputStream stream, Integer length) {
        write(path, stream, length, -1);
    }

    @Override
    public InputStream read(String path) {
        try {
            String filepath = buildFilepath(rootDirectory, path);

            FileInputStream stream = new FileInputStream(filepath);

            return stream;
        } catch (FileNotFoundException e) {
            System.out.println("LinuxStorage: FileNotFoundException => " + e.getMessage());
            return null;
        }
    }

    @Override
    public void remove(String path) {
        String filepath = buildFilepath(rootDirectory, path);

        File binary = new File(filepath);

        binary.delete();
    }

    private String buildFilepath(String root, String path) {
        return new StringBuilder().append(root).append(path).toString();
    }
}
