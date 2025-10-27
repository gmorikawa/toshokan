package dev.gmorikawa.toshokan.config.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import dev.gmorikawa.storage.LocalStorage;

@Configuration
public class LocalStorageConfiguration {

    @Value("${storage.local.root-directory}")
    private String rootDirectory;

    public LocalStorage configure() {
        return new LocalStorage(rootDirectory);
    }
}
