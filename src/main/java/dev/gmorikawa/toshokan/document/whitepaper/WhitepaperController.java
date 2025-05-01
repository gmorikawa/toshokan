package dev.gmorikawa.toshokan.document.whitepaper;

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
@RequestMapping(path = "api/whitepapers")
public class WhitepaperController {

    private final WhitepaperService service;
    private final DocumentFileService documentFileService;

    public WhitepaperController(WhitepaperService service, DocumentFileService documentFileService) {
        this.service = service;
        this.documentFileService = documentFileService;
    }

    @GetMapping()
    public List<Whitepaper> getAll() {
        return service.getAll();
    }

    @GetMapping("/year/{year}")
    public Whitepaper getByYear(@PathVariable Integer year) {
        return service.getByYear(year);
    }

    @GetMapping("/{id}")
    public Whitepaper getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Whitepaper create(@RequestBody Whitepaper entity) {
        return service.insert(entity);
    }

    @PostMapping("/{id}/upload")
    public DocumentFile upload(@PathVariable String id, @RequestParam("file") MultipartFile binary, @RequestParam("description") String description) {
        Whitepaper whitepaper = service.getById(id);
        return documentFileService.create(whitepaper, binary, description);
    }

    @PatchMapping("/{id}")
    public Whitepaper update(@PathVariable String id, @RequestBody Whitepaper entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable String id) {
        return service.remove(id);
    }
}
