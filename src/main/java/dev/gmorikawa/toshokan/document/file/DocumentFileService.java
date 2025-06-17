package dev.gmorikawa.toshokan.document.file;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.document.Document;
import dev.gmorikawa.toshokan.file.File;
import dev.gmorikawa.toshokan.file.FileService;
import dev.gmorikawa.toshokan.file.enumerator.FileState;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

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

    public DocumentFile create(User requestor, Document document, MultipartFile binary, String description) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

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
