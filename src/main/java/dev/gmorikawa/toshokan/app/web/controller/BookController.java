package dev.gmorikawa.toshokan.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.gmorikawa.toshokan.app.web.shared.Meta;
import dev.gmorikawa.toshokan.app.web.shared.Page;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.domain.category.CategoryService;
import dev.gmorikawa.toshokan.domain.document.book.Book;
import dev.gmorikawa.toshokan.domain.document.book.BookService;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.domain.topic.TopicService;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.book")
@RequestMapping(path = "books")
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final TopicService topicService;

    public BookController(
            BookService service,
            AuthorService authorService,
            PublisherService publisherService,
            CategoryService categoryService,
            TopicService topicService
    ) {
        this.service = service;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.topicService = topicService;
    }

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
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
}
