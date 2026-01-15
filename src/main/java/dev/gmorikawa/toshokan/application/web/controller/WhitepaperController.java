package dev.gmorikawa.toshokan.application.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.gmorikawa.toshokan.application.web.shared.Meta;
import dev.gmorikawa.toshokan.application.web.shared.Page;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.domain.category.CategoryService;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFile;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.domain.document.whitepaper.Whitepaper;
import dev.gmorikawa.toshokan.domain.document.whitepaper.WhitepaperService;
import dev.gmorikawa.toshokan.domain.file.File;
import dev.gmorikawa.toshokan.domain.file.FileService;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.domain.topic.TopicService;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;
import jakarta.servlet.http.HttpServletResponse;

@Controller("web.whitepaper")
@RequestMapping(path = "app/whitepapers")
public class WhitepaperController {

    private final WhitepaperService service;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final TopicService topicService;
    private final FileService fileService;
    private final DocumentFileService documentFileService;

    public WhitepaperController(
            WhitepaperService service,
            AuthorService authorService,
            PublisherService publisherService,
            CategoryService categoryService,
            TopicService topicService,
            FileService fileService,
            DocumentFileService documentFileService
    ) {
        this.service = service;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.topicService = topicService;
        this.fileService = fileService;
        this.documentFileService = documentFileService;
    }

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Whitepaper> whitepapers = service.getAll(pagination);

        model.addAttribute("meta", new Meta("List Whitepapers || Toshokan"));
        model.addAttribute("page", new Page("Whitepapers", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/whitepapers/list", pagination));
        model.addAttribute("whitepapers", whitepapers);

        return "whitepaper/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Whitepaper || Toshokan"));
        model.addAttribute("page", new Page("Whitepaper", "Create"));
        model.addAttribute("whitepaper", new Whitepaper());
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "whitepaper/create";
    }

    @GetMapping("/{id}")
    public String details(
            @PathVariable UUID id,
            Model model
    ) {
        Whitepaper whitepaper = service.getById(id);

        model.addAttribute("meta", new Meta("Whitepaper Details || Toshokan"));
        model.addAttribute("page", new Page("Whitepaper", whitepaper.getTitle()));
        model.addAttribute("whitepaper", whitepaper);

        return "whitepaper/details";
    }

    @GetMapping("/{id}/upload")
    public String upload(
        @PathVariable UUID id,
        Model model
    ) {
        Whitepaper whitepaper = service.getById(id);

        model.addAttribute("meta", new Meta("Upload Whitepaper || Toshokan"));
        model.addAttribute("page", new Page("Whitepaper", "Upload: " + whitepaper.getTitle()));
        model.addAttribute("whitepaper", whitepaper);
        model.addAttribute("documentFile", new DocumentFile());

        return "whitepaper/upload";
    }

     @GetMapping("/{id}/download/{fileId}")
    public void download(
        @PathVariable UUID id,
        @PathVariable UUID fileId,
        HttpServletResponse response
    ) {
        File file = fileService.getById(fileId);

        try (
            InputStream inputStream = fileService.download(fileId);
            OutputStream outputStream = response.getOutputStream();
        ) {
            response.setHeader("Content-Transfer-Encoding", "binary;");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\"");

            inputStream.transferTo(outputStream);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable UUID id, Model model) {
        Whitepaper whitepaper = service.getById(id);

        model.addAttribute("meta", new Meta("Update Whitepaper || Toshokan"));
        model.addAttribute("page", new Page("Whitepaper", "Update"));
        model.addAttribute("whitepaper", whitepaper);
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "whitepaper/update";
    }

    @PostMapping("/create")
    public String create(
        @RequestAttribute LoggedUser loggedUser,
        @ModelAttribute Whitepaper whitepaper
    ) {
        service.create(loggedUser, whitepaper);

        return "redirect:/app/whitepapers/list";
    }

    @PostMapping("/{id}/update")
    public String update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @ModelAttribute Whitepaper whitepaper
    ) {
        service.update(loggedUser, id, whitepaper);

        return "redirect:/app/whitepapers/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @ModelAttribute Whitepaper whitepaper
    ) {
        service.remove(loggedUser, id);

        return "redirect:/app/whitepapers/list";
    }

    @PostMapping("/{id}/upload")
    public String upload(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestParam MultipartFile file,
        @RequestParam String description,
        RedirectAttributes redirectAttributes
    ) {
        documentFileService.create(loggedUser, service.getById(id), file, description);

        return String.format("redirect:/app/whitepapers/%s", id);
    }
}
