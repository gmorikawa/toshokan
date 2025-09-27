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
import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.author")
@RequestMapping(path = "authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Author> authors = service.getAll();
        
        model.addAttribute("meta", new Meta("List Authors || Toshokan"));
        model.addAttribute("page", new Page("List Authors"));
        model.addAttribute("pagination", new PaginationComponent("/authors/list", pagination));
        model.addAttribute("authors", authors);

        return "author/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Author || Toshokan"));
        model.addAttribute("page", new Page("Create Author"));
        model.addAttribute("author", new Author());

        return "author/create";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        Author author = service.getById(id);

        model.addAttribute("meta", new Meta("Update Author || Toshokan"));
        model.addAttribute("page", new Page("Update Author"));
        model.addAttribute("author", author);

        return "author/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Author author) {
        service.create(author);

        return "redirect:/authors/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute Author author) {
        service.update(id, author);

        return "redirect:/authors/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute Author author) {
        service.remove(id);

        return "redirect:/authors/list";
    }
}
