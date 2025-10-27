package dev.gmorikawa.toshokan.domain.file;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.file.enumerator.FileState;
import dev.gmorikawa.toshokan.domain.file.exception.FileNotFoundException;
import dev.gmorikawa.toshokan.domain.file.storage.FileStorageService;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeService;

@Service
public class FileService {

    private final FileTypeService typeService;
    private final FileRepository repository;
    private final FileStorageService storageService;

    public FileService(FileTypeService typeService, FileRepository repository, FileStorageService storageService) {
        this.typeService = typeService;
        this.repository = repository;
        this.storageService = storageService;
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
            storageService.remove(file.get());
            repository.delete(file.get());
        }

        return file.orElse(null);
    }

    public File upload(UUID id, MultipartFile binary) {
        File file = repository.findById(id).orElse(null);

        try {
            storageService.store(file, binary);

            file.setState(FileState.AVAILABLE);

            return repository.save(file);
        } catch(Exception e) {
            file.setState(FileState.CORRUPTED);

            return repository.save(file);
        }
    }

    public InputStream download(UUID id) {
        File file = repository.findById(id).orElseThrow(FileNotFoundException::new);

        return storageService.retrive(file);
    }
}
