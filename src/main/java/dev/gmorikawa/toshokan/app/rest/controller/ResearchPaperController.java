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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.gmorikawa.toshokan.domain.document.file.DocumentFile;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaperService;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.shared.query.Pagination;

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
    public List<ResearchPaper> getAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "0") Integer size
    ) {
        if (page == 0 && size == 0) {
            return service.getAll();
        }
        Pagination pagination = new Pagination(page, size);
        return service.getAll(pagination);
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public ResearchPaper getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/files")
    public List<DocumentFile> getFiles(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        ResearchPaper researchPaper = service.getById(id);
        return documentFileService.getFilesByDocument(researchPaper);
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

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }

    @PostMapping()
    public ResearchPaper create(
        @RequestAttribute User user,
        @RequestBody ResearchPaper entity
    ) {
        return service.create(user, entity);
    }

    @PostMapping("/{id}/files/upload")
    public DocumentFile upload(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestParam MultipartFile binary,
        @RequestParam String version,
        @RequestParam String description,
        @RequestParam Integer publishingYear
    ) {
        ResearchPaper researchPaper = service.getById(id);
        return documentFileService.create(user, researchPaper, binary, version, description, publishingYear);
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

    @DeleteMapping("/{id}/files/{documentFileId}")
    public boolean removeFile(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @PathVariable UUID documentFileId
    ) {
        return documentFileService.remove(documentFileId);
    }
}
