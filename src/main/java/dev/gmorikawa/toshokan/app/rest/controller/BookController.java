package dev.gmorikawa.toshokan.app.rest.controller;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.document.book.Book;
import dev.gmorikawa.toshokan.domain.document.book.BookService;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.domain.user.User;

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
    public List<Book> getAll() {
        return service.getAll();
    }

    // @GetMapping("/{id}/files")
    // public List<File> getFiles(@PathVariable String id) {
    //     Book book = service.getById(id);
    //     return documentFileService.getFilesByDocument(book);
    // }

    @GetMapping("/{id}/files/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(
        @PathVariable("id") UUID id,
        @PathVariable("fileId") UUID fileId
    ) {
        InputStream binary = documentFileService.downloadFileById(fileId);

        InputStreamResource resource = new InputStreamResource(binary);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.txt");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping()
    public Book create(
        @RequestAttribute("user") User requestor,
        @RequestBody Book entity
    ) {
        return service.create(entity);
    }

    // @PostMapping("/{id}/upload")
    // public DocumentFile upload(
    //     @RequestAttribute("user") User requestor,
    //     @PathVariable String id,
    //     @RequestParam("file") MultipartFile binary,
    //     @RequestParam("description") String description
    // ) {
    //     Book book = service.getById(id);
    //     return documentFileService.create(requestor, book, binary, description);
    // }

    @PatchMapping("/{id}")
    public Book update(
        @RequestAttribute("user") User requestor,
        @PathVariable UUID id,
        @RequestBody Book entity
    ) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute("user") User requestor,
        @PathVariable UUID id
    ) {
        return service.remove(id);
    }
}
