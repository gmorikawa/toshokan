package dev.gmorikawa.toshokan.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.gmorikawa.toshokan.shared.web.Meta;
import dev.gmorikawa.toshokan.shared.web.Page;
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

        return "user/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        service.create(user);

        return "redirect:/users/list";
    }
}
