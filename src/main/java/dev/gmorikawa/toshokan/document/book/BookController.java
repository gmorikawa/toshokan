package dev.gmorikawa.toshokan.document.book;

import java.io.InputStream;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.document.file.DocumentFile;
import dev.gmorikawa.toshokan.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.file.File;
import dev.gmorikawa.toshokan.user.User;

@RestController
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

    @GetMapping("/year/{year}")
    public List<Book> getByYear(@PathVariable Integer year) {
        return service.getByYear(year);
    }

    @GetMapping("/{id}/files")
    public List<File> getFiles(@PathVariable String id) {
        Book book = service.getById(id);
        return documentFileService.getFilesByDocument(book);
    }

    @GetMapping("/{id}/files/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(
        @PathVariable("id") String id,
        @PathVariable("fileId") String fileId
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
    public Book getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Book create(
        @RequestAttribute("user") User requestor,
        @RequestBody Book entity
    ) {
        return service.create(requestor, entity);
    }

    @PostMapping("/{id}/upload")
    public DocumentFile upload(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestParam("file") MultipartFile binary,
        @RequestParam("description") String description
    ) {
        Book book = service.getById(id);
        return documentFileService.create(requestor, book, binary, description);
    }

    @PatchMapping("/{id}")
    public Book update(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestBody Book entity
    ) {
        return service.update(requestor, id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute("user") User requestor,
        @PathVariable String id
    ) {
        return service.remove(requestor, id);
    }
}
