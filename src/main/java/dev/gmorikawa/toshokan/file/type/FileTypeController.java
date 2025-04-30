package dev.gmorikawa.toshokan.file.type;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/file-types")
public class FileTypeController {

    private final FileTypeService service;

    public FileTypeController(FileTypeService service) {
        this.service = service;
    }

    @GetMapping()
    public List<FileType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FileType getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public FileType create(@RequestBody FileType fileType) {
        return service.insert(fileType);
    }

    @PatchMapping("/{id}")
    public FileType update(@PathVariable String id, @RequestBody FileType fileType) {
        return service.update(id, fileType);
    }

    @DeleteMapping("/{id}")
    public FileType remove(@PathVariable String id) {
        return service.remove(id);
    }
}
