package dev.gmorikawa.toshokan.file;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final FileRepository repository;

    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public List<File> getAll() {
        return repository.findAll();
    }

    public File getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public File insert(File entity) {
        return repository.save(entity);
    }

    public File update(String id, File entity) {
        Optional<File> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        File file = result.get();

        file.setFilename(entity.getFilename());
        file.setType(entity.getType());

        return repository.save(file);
    }

    public File remove(String id) {
        Optional<File> file = repository.findById(id);

        if(!file.isEmpty()) {
            repository.delete(file.get());
        }

        return file.orElse(null);
    }
}
