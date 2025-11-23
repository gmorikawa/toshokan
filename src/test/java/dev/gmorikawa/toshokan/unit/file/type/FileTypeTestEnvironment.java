package dev.gmorikawa.toshokan.unit.file.type;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.domain.file.type.FileType;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeRepository;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeService;

public abstract class FileTypeTestEnvironment {

    @Autowired
    protected FileTypeRepository repository;

    @Autowired
    protected FileTypeService service;

    protected void clean(FileType fileType) {
        repository.delete(fileType);
    }

}
