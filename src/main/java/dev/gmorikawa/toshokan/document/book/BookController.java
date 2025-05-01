package dev.gmorikawa.toshokan.document.book;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.document.DocumentFile;
import dev.gmorikawa.toshokan.document.DocumentFileService;

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
    public Book getByYear(@PathVariable Integer year) {
        return service.getByYear(year);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Book create(@RequestBody Book entity) {
        return service.insert(entity);
    }

    @PostMapping("/{id}/upload")
    public DocumentFile upload(@PathVariable String id, @RequestParam("file") MultipartFile binary, @RequestParam("description") String description) {
        Book book = service.getById(id);
        return documentFileService.create(book, binary, description);
    }

    @PatchMapping("/{id}")
    public Book update(@PathVariable String id, @RequestBody Book entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable String id) {
        return service.remove(id);
    }
}
