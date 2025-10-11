package dev.gmorikawa.toshokan.config.storage;

import org.springframework.beans.factory.annotation.Value;

import dev.gmorikawa.storage.LocalStorage;

public class LocalStorageConfiguration {

    @Value("${storage.local.root-directory}")
    private String rootDirectory;

    public LocalStorage configure() {
        return new LocalStorage(rootDirectory);
    }
}
