package dev.gmorikawa.toshokan.file.type;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class FileTypeService {

    private final FileTypeRepository repository;

    public FileTypeService(FileTypeRepository repository) {
        this.repository = repository;
    }

    public List<FileType> getAll() {
        return repository.findAll();
    }

    public FileType getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public FileType insert(FileType entity) {
        return repository.save(entity);
    }

    public FileType update(String id, FileType entity) {
        Optional<FileType> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        FileType fileType = result.get();

        fileType.setName(entity.getName());
        fileType.setExtension(entity.getExtension());

        return repository.save(fileType);
    }

    public FileType remove(String id) {
        Optional<FileType> fileType = repository.findById(id);

        if(!fileType.isEmpty()) {
            repository.delete(fileType.get());
        }

        return fileType.orElse(null);
    }
}
