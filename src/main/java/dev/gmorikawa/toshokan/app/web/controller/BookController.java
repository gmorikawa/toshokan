package dev.gmorikawa.toshokan.app.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;
import jakarta.servlet.http.HttpServletResponse;

@Controller("web.book")
@RequestMapping(path = "books")
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
            @PathVariable String id,
            Model model
    ) {
        Book book = service.getById(id);

        model.addAttribute("meta", new Meta("Book Details || Toshokan"));
        model.addAttribute("page", new Page(book.getTitle()));
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
        model.addAttribute("page", new Page("List Books"));
        model.addAttribute("pagination", new PaginationComponent("/books/list", pagination));
        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Book || Toshokan"));
        model.addAttribute("page", new Page("Create Book"));
        model.addAttribute("book", new Book());
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "book/create";
    }

    @GetMapping("/{id}/upload")
    public String upload(
        @PathVariable String id,
        Model model
    ) {
        model.addAttribute("meta", new Meta("Upload Book || Toshokan"));
        model.addAttribute("page", new Page("Upload Book"));
        model.addAttribute("book", service.getById(id));
        model.addAttribute("documentFile", new DocumentFile());

        return "book/upload";
    }

     @GetMapping("/{id}/download/{fileId}")
    public void download(
        @PathVariable String id,
        @PathVariable String fileId,
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
    public String update(@PathVariable String id, Model model) {
        Book book = service.getById(id);

        model.addAttribute("meta", new Meta("Update Book || Toshokan"));
        model.addAttribute("page", new Page("Update Book"));
        model.addAttribute("book", book);
        model.addAttribute("authors", this.authorService.getAll());
        model.addAttribute("publishers", this.publisherService.getAll());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("topics", this.topicService.getAll());

        return "book/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        service.create(book);

        return "redirect:/books/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute Book book) {
        service.update(id, book);

        return "redirect:/books/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute Book book) {
        service.remove(id);

        return "redirect:/books/list";
    }

    @PostMapping("/{id}/upload")
    public String upload(
        @PathVariable String id,
        @RequestParam MultipartFile file,
        @RequestParam String label,
        RedirectAttributes redirectAttributes
    ) {
        documentFileService.create(service.getById(id), file, label);

        return String.format("redirect:/books/%s", id);
    }
}
