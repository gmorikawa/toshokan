package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.app.rest.dto.UserWithoutPasswordDTO;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.UserService;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<UserWithoutPasswordDTO> getUsers() {
        return service.getAll()
            .stream()
            .map(UserWithoutPasswordDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserWithoutPasswordDTO getById(
        @RequestAttribute("user") User client,
        @PathVariable UUID id
    ) {
        User user = service.getById(client, id);
        return user != null ? new UserWithoutPasswordDTO(user) : null;
    }

    @PostMapping()
    public UserWithoutPasswordDTO create(
        @RequestAttribute("user") User client,
        @RequestBody User user
    ) {
        return new UserWithoutPasswordDTO(service.create(client, user));
    }

    @PatchMapping("/{id}")
    public UserWithoutPasswordDTO update(
        @RequestAttribute("user") User client,
        @PathVariable UUID id,
        @RequestBody User user
    ) {
        return new UserWithoutPasswordDTO(service.update(client, id, user));
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute("user") User client,
        @PathVariable UUID id
    ) {
        return service.remove(client, id);
    }

}