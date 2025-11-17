package dev.gmorikawa.toshokan.app.web.controller;

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

import dev.gmorikawa.toshokan.app.web.shared.Meta;
import dev.gmorikawa.toshokan.app.web.shared.Page;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.domain.category.CategoryService;
import dev.gmorikawa.toshokan.domain.document.book.Book;
import dev.gmorikawa.toshokan.domain.document.book.BookService;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFile;
import dev.gmorikawa.toshokan.domain.document.file.DocumentFileService;
import dev.gmorikawa.toshokan.domain.file.File;
import dev.gmorikawa.toshokan.domain.file.FileService;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.domain.topic.TopicService;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;
import jakarta.servlet.http.HttpServletResponse;

@Controller("web.book")
@RequestMapping(path = "app/books")
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final TopicService topicService;
    private final FileService fileService;
    private final DocumentFileService documentFileService;

    public BookController(
            BookService service,
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

    @GetMapping("/{id}")
    public String details(
            @PathVariable UUID id,
            Model model
    ) {
        Book book = service.getById(id);

        model.addAttribute("meta", new Meta("Book Details || Toshokan"));
        model.addAttribute("page", new Page("Book", book.getTitle()));
        model.addAttribute("book", book);

        return "book/details";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            Model model
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Book> books = service.getAll(pagination);

        model.addAttribute("meta", new Meta("List Books || Toshokan"));
        model.addAttribute("page", new Page("Books", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/books/list", pagination));
        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Book || Toshokan"));
        model.addAttribute("page", new Page("Book", "Create"));
        model.addAttribute("book", new Book());
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "book/create";
    }

    @GetMapping("/{id}/file/upload")
    public String upload(
        @PathVariable UUID id,
        Model model
    ) {
        Book book = service.getById(id);
        model.addAttribute("meta", new Meta("Upload Book || Toshokan"));
        model.addAttribute("page", new Page("Book", "Upload: " + book.getTitle()));
        model.addAttribute("book", book);
        model.addAttribute("documentFile", new DocumentFile());

        return "book/upload";
    }

    @GetMapping("/{id}/file/{fileId}/download")
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

    @GetMapping("/{id}/file/{fileId}/remove")
    public String removeFile(
        @PathVariable UUID id,
        @PathVariable UUID fileId,
        HttpServletResponse response
    ) {
        documentFileService.remove(fileId);

        return String.format("redirect:/app/books/%s", id);
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable UUID id, Model model) {
        Book book = service.getById(id);

        model.addAttribute("meta", new Meta("Update Book || Toshokan"));
        model.addAttribute("page", new Page("Book", "Update"));
        model.addAttribute("book", book);
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "book/update";
    }

    @PostMapping("/create")
    public String create(
        @RequestAttribute User user,
        @ModelAttribute Book book
    ) {
        service.create(user, book);

        return "redirect:/app/books/list";
    }

    @PostMapping("/{id}/update")
    public String update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @ModelAttribute Book book
    ) {
        service.update(user, id, book);

        return "redirect:/app/books/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @ModelAttribute Book book
    ) {
        service.remove(user, id);

        return "redirect:/app/books/list";
    }

    @PostMapping("/{id}/file/upload")
    public String upload(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestParam MultipartFile file,
        @RequestParam String version,
        @RequestParam String description,
        @RequestParam Integer publishingYear,
        RedirectAttributes redirectAttributes
    ) {
        documentFileService.create(user, service.getById(id), file, version, description, publishingYear);

        return String.format("redirect:/app/books/%s", id);
    }
}
