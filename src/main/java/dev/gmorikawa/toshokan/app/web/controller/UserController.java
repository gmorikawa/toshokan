package dev.gmorikawa.toshokan.app.web.controller;

import java.util.List;
import java.util.UUID;

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
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.UserService;

@Controller("web.user")
@RequestMapping(path = "app/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<User> users = service.getAll(pagination);
        
        model.addAttribute("meta", new Meta("List Users || Toshokan"));
        model.addAttribute("page", new Page("Users", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/users/list", pagination));
        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create User || Toshokan"));
        model.addAttribute("page", new Page("User", "Create"));
        model.addAttribute("user", new User());

        return "user/create";
    }

    @GetMapping("/{id}/edit")
    public String update(
        @PathVariable UUID id,
        Model model
    ) {
        User user = service.getById(id);

        model.addAttribute("meta", new Meta("Update User || Toshokan"));
        model.addAttribute("page", new Page("User", "Update"));
        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        service.create(user);

        return "redirect:/app/users/list";
    }

    @PostMapping("/{id}/update")
    public String update(
        @PathVariable UUID id,
        @ModelAttribute User user
    ) {
        service.update(id, user);

        return "redirect:/app/users/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(
        @PathVariable UUID id,
        @ModelAttribute User user
    ) {
        service.remove(id);

        return "redirect:/app/users/list";
    }
}
