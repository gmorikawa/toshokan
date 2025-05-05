package dev.gmorikawa.toshokan.document;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.file.File;
import dev.gmorikawa.toshokan.file.FileService;
import dev.gmorikawa.toshokan.file.enumerator.FileState;

@Service
public class DocumentFileService {

    private final FileService fileService;
    private final DocumentFileRepository repository;

    public DocumentFileService(FileService fileService, DocumentFileRepository repository) {
        this.fileService = fileService;
        this.repository = repository;
    }

    public List<File> getFilesByDocument(Document document) {
        return repository.getFilesByDocument(document);
    }

    public InputStream downloadFileById(String fileId) {
        return fileService.download(fileId);
    }

    public DocumentFile create(Document document, MultipartFile binary, String description) {
        File file = fileService.upload(
            fileService.create(binary, document.getFilePath()).getId(),
            binary
        );

        if (file.getState() == FileState.AVAILABLE) {
            DocumentFile documentFile = new DocumentFile(document, file, description);

            return repository.save(documentFile);
        } else {
            return null;
        }
    }

    public boolean remove(String id) {
        Optional<DocumentFile> documentFile = repository.findById(id);

        if (documentFile.isEmpty()) {
            return false;
        }

        repository.delete(documentFile.get());

        return true;
    }
}
