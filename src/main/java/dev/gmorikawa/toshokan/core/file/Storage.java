package dev.gmorikawa.toshokan.core.file;

import java.io.InputStream;

public interface Storage {
    void write(String path, InputStream stream, Integer length, Integer skip);
    void write(String path, InputStream stream, Integer length);
    InputStream read(String path);

    void remove(String path);
}
