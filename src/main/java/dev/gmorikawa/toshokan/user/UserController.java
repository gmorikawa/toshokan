package dev.gmorikawa.toshokan.user;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<User> getUsers() {
        return service.getAll();
    }

    @PostMapping()
    public User create(
        @RequestAttribute("user") User requestor,
        @RequestBody User user
    ) {
        return service.create(user);
    }

    @PatchMapping("/{id}")
    public User update(
        @RequestAttribute("user") User requestor,
        @PathVariable UUID id,
        @RequestBody User user
    ) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public User remove(
        @RequestAttribute("user") User requestor,
        @PathVariable UUID id
    ) {
        return service.remove(id);
    }

}