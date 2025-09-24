package dev.gmorikawa.toshokan.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.gmorikawa.toshokan.app.web.shared.Meta;
import dev.gmorikawa.toshokan.app.web.shared.Page;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.UserService;

@Controller("web.user")
@RequestMapping(path = "users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = service.getUsers();
        
        model.addAttribute("meta", new Meta("List Users || Toshokan"));
        model.addAttribute("page", new Page("List Users"));
        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create User || Toshokan"));
        model.addAttribute("page", new Page("Create User"));
        model.addAttribute("user", new User());

        return "user/create";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        User user = service.getById(id);

        model.addAttribute("meta", new Meta("Update User || Toshokan"));
        model.addAttribute("page", new Page("Update User"));
        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        service.create(user);

        return "redirect:/users/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute User user) {
        service.update(id, user);

        return "redirect:/users/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute User user) {
        service.remove(id);

        return "redirect:/users/list";
    }
}
