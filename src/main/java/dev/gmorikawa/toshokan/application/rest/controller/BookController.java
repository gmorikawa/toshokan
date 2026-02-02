package dev.gmorikawa.toshokan.application.rest.controller;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.core.document.book.Book;
import dev.gmorikawa.toshokan.core.document.book.BookService;
import dev.gmorikawa.toshokan.core.document.file.DocumentFile;
import dev.gmorikawa.toshokan.core.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.book")
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService service;
    private final DocumentFileService documentFileService;

    public BookController(BookService service, DocumentFileService documentFileService) {
        this.service = service;
        this.documentFileService = documentFileService;
    }

    @GetMapping()
    public List<Book> getAll(
        @RequestAttribute(required = false) Pagination pagination,
        @RequestParam(required = false) String query
    ) {
        if (pagination == null) {
            if (query != null && !query.isEmpty()) {
                return service.searchByTitle(query);
            } else {
                return service.getAll();
            }
        }

        if (query != null && !query.isEmpty()) {
            return service.searchByTitle(query, pagination);
        } else {
            return service.getAll(pagination);
        }
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/files")
    public List<DocumentFile> getFiles(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        Book book = service.getById(id);
        return documentFileService.getFilesByDocument(book);
    }

    @GetMapping("/{id}/files/{documentFileId}")
    public DocumentFile getFile(
        @PathVariable UUID id,
        @PathVariable UUID documentFileId
    ) {
        return documentFileService.getById(documentFileId);
    }

    @GetMapping("/{id}/files/{documentFileId}/download")
    public ResponseEntity<InputStreamResource> downloadFile(
        @PathVariable UUID id,
        @PathVariable UUID documentFileId
    ) {
        DocumentFile documentFile = documentFileService.getById(documentFileId);
        InputStream binary = documentFileService.downloadFileById(documentFile.getFile().getId());
        InputStreamResource resource = new InputStreamResource(binary);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", documentFile.getFile().getFilename()));
        headers.add(HttpHeaders.CONTENT_TYPE, documentFile.getFile().getType().getMimeType());

        return ResponseEntity.ok()
            .headers(headers)
            .body(resource);
    }

    @PostMapping()
    public Book create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Book entity
    ) {
        return service.create(loggedUser, entity);
    }

    @PostMapping("/{id}/files/upload")
    public DocumentFile upload(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestParam MultipartFile binary,
        @RequestParam String description
    ) {
        Book book = service.getById(id);
        return documentFileService.create(loggedUser, book, binary, description);
    }

    @PatchMapping("/{id}")
    public Book update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Book entity
    ) {
        return service.update(loggedUser, id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }

    @DeleteMapping("/{id}/files/{documentFileId}")
    public boolean removeFile(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @PathVariable UUID documentFileId
    ) {
        return documentFileService.remove(documentFileId);
    }
}
