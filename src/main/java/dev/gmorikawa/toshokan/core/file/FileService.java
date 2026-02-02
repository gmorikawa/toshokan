package dev.gmorikawa.toshokan.core.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.core.file.enumerator.FileState;
import dev.gmorikawa.toshokan.core.file.exception.FileNotFoundException;
import dev.gmorikawa.toshokan.core.file.type.FileTypeService;

@Service
public class FileService {

    private final FileTypeService typeService;
    private final FileRepository repository;
    private final Storage storage;

    public FileService(FileTypeService typeService, FileRepository repository, Storage storage) {
        this.typeService = typeService;
        this.repository = repository;
        this.storage = storage;
    }

    public List<File> getAll() {
        return repository.findAll();
    }

    public File getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public File create(MultipartFile binary, String filepath) {
        File file = new File();

        String filename = Optional.of(binary.getOriginalFilename()).orElse("");

        file.setFilename(filename);
        
        String[] splitedFilename = filename.split("\\.");
        if (splitedFilename.length > 0) {
            String extension = splitedFilename[splitedFilename.length - 1];
            file.setType(typeService.getByExtension(extension));
        }

        file.setPath(filepath);
        file.setState(FileState.UPLOADING);

        return insert(file);
    }

    public File insert(File entity) {
        return repository.save(entity);
    }

    public File update(UUID id, File entity) {
        Optional<File> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        File file = result.get();

        file.setFilename(entity.getFilename());
        file.setType(entity.getType());

        return repository.save(file);
    }

    public File remove(UUID id) {
        Optional<File> file = repository.findById(id);

        if(!file.isEmpty()) {
            storage.remove(file.get().getFilePath());
            repository.delete(file.get());
        }

        return file.orElse(null);
    }

    public File upload(UUID id, MultipartFile binary) {
        File file = repository.findById(id).orElse(null);

        try {
            storage.write(file.getFilePath(), binary.getInputStream(), (int)binary.getSize());

            file.setState(FileState.AVAILABLE);

            return repository.save(file);
        } catch(IOException e) {
            file.setState(FileState.CORRUPTED);

            return repository.save(file);
        }
    }

    public InputStream download(UUID id) {
        File file = repository.findById(id).orElseThrow(FileNotFoundException::new);

        return storage.read(file.getFilePath());
    }
}
