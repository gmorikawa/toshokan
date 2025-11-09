package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;

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

import dev.gmorikawa.toshokan.domain.document.file.DocumentFile;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaperService;
import dev.gmorikawa.toshokan.domain.user.User;

@RestController("api.researchpaper")
@RequestMapping(path = "api/research-papers")
public class ResearchPaperController {

    private final ResearchPaperService service;
    private final DocumentFileService documentFileService;

    public ResearchPaperController(ResearchPaperService service, DocumentFileService documentFileService) {
        this.service = service;
        this.documentFileService = documentFileService;
    }

    @GetMapping()
    public List<ResearchPaper> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResearchPaper getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping()
    public ResearchPaper create(
        @RequestAttribute User user,
        @RequestBody ResearchPaper entity
    ) {
        return service.create(user, entity);
    }

    @PostMapping("/{id}/upload")
    public DocumentFile upload(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestParam("file") MultipartFile binary,
        @RequestParam("description") String description
    ) {
        ResearchPaper researchpaper = service.getById(id);
        return documentFileService.create(researchpaper, binary, description);
    }

    @PatchMapping("/{id}")
    public ResearchPaper update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestBody ResearchPaper entity
    ) {
        return service.update(user, id, entity);
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        return service.remove(user, id);
    }
}
