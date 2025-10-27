package dev.gmorikawa.toshokan.domain.document.file;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.document.Document;
import dev.gmorikawa.toshokan.domain.file.File;
import dev.gmorikawa.toshokan.domain.file.FileService;
import dev.gmorikawa.toshokan.domain.file.enumerator.FileState;

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

    public InputStream downloadFileById(UUID fileId) {
        return fileService.download(fileId);
    }

    public DocumentFile create(Document document, MultipartFile binary, String label) {
        File file = fileService.upload(
            fileService.create(binary, document.getTitle()).getId(),
            binary
        );

        if (file.getState() == FileState.AVAILABLE) {
            DocumentFile documentFile = new DocumentFile(document, file, label);

            return repository.save(documentFile);
        } else {
            return null;
        }
    }

    public boolean remove(UUID id) {
        Optional<DocumentFile> documentFile = repository.findById(id);

        if (documentFile.isEmpty()) {
            return false;
        }

        repository.delete(documentFile.get());
        fileService.remove(documentFile.get().getFile().getId());

        return true;
    }
}
