package dev.gmorikawa.toshokan.application.web.controller;

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

import dev.gmorikawa.toshokan.application.web.shared.Meta;
import dev.gmorikawa.toshokan.application.web.shared.Page;
import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.author")
@RequestMapping(path = "app/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Author> authors = service.getAll(pagination);
        
        model.addAttribute("meta", new Meta("List Authors || Toshokan"));
        model.addAttribute("page", new Page("Authors", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/authors/list", pagination));
        model.addAttribute("authors", authors);

        return "author/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Author || Toshokan"));
        model.addAttribute("page", new Page("Author", "Create"));
        model.addAttribute("author", new Author());

        return "author/create";
    }

    @GetMapping("/{id}/edit")
    public String update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        Model model
    ) {
        Author author = service.getById(id);

        model.addAttribute("meta", new Meta("Update Author || Toshokan"));
        model.addAttribute("page", new Page("Author", "Update"));
        model.addAttribute("author", author);

        return "author/update";
    }

    @PostMapping("/create")
    public String create(
        @RequestAttribute LoggedUser loggedUser,
        @ModelAttribute Author author
    ) {
        service.create(loggedUser, author);

        return "redirect:/app/authors/list";
    }

    @PostMapping("/{id}/update")
    public String update(
        @RequestAttribute LoggedUser loggedUser,
        @ModelAttribute Author author,
        @PathVariable UUID id
    ) {
        service.update(loggedUser, id, author);

        return "redirect:/app/authors/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(
        @RequestAttribute LoggedUser loggedUser,
        @ModelAttribute Author author,
        @PathVariable UUID id
    ) {
        service.remove(loggedUser, id);

        return "redirect:/app/authors/list";
    }
}
