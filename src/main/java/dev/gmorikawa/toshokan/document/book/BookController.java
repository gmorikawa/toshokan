package dev.gmorikawa.toshokan.document.book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
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

    @PostMapping("/upload")
    public boolean upload(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                File convertFile = new File(file.getOriginalFilename());
                convertFile.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                    fos.write(file.getBytes());
                }
            }

            return true;
        } catch (IOException exception) {
            return false;
        }
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
