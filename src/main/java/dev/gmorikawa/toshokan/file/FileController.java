package dev.gmorikawa.toshokan.file;

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

import dev.gmorikawa.toshokan.file.storage.FileStorageService;

@RestController
@RequestMapping(path = "api/files")
public class FileController {

    private final FileService service;
    private final FileStorageService storageService;

    public FileController(FileService service, FileStorageService storageService) {
        this.service = service;
        this.storageService = storageService;
    }

    @GetMapping()
    public List<File> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public File getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public File create(@RequestBody File file) {
        return service.insert(file);
    }

    @PostMapping("/upload")
    public boolean upload(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                // java.io.File convertFile = new java.io.File(file.getOriginalFilename());
                // file.transferTo(convertFile);
                // convertFile.createNewFile();
                // try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                //     fos.write(file.getBytes());

                // }
                storageService.store(file);
            }

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @PatchMapping("/{id}")
    public File update(@PathVariable String id, @RequestBody File file) {
        return service.update(id, file);
    }

    @DeleteMapping("/{id}")
    public File remove(@PathVariable String id) {
        return service.remove(id);
    }
}
