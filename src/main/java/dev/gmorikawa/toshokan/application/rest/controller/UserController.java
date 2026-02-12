package dev.gmorikawa.toshokan.application.rest.controller;

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

import dev.gmorikawa.toshokan.application.rest.dto.UserWithoutPasswordDTO;
import dev.gmorikawa.toshokan.core.user.User;
import dev.gmorikawa.toshokan.core.user.UserService;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.user")
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<UserWithoutPasswordDTO> getUsers(
        @RequestAttribute(required = false) Pagination pagination
    ) {
        if (pagination == null) {
            return service.getAll()
                .stream()
                .map(UserWithoutPasswordDTO::new)
                .collect(Collectors.toList());
        }

        return service.getAll(pagination)
            .stream()
            .map(UserWithoutPasswordDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public UserWithoutPasswordDTO getById(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        User user = service.getById(loggedUser, id);
        return user != null
            ? new UserWithoutPasswordDTO(user)
            : null;
    }

    @PostMapping()
    public UserWithoutPasswordDTO create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody User user
    ) {
        return new UserWithoutPasswordDTO(
            service.create(loggedUser, user)
        );
    }

    @PatchMapping("/{id}")
    public UserWithoutPasswordDTO update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody User user
    ) {
        return new UserWithoutPasswordDTO(
            service.update(loggedUser, id, user)
        );
    }

    @DeleteMapping("/{id}")
    public boolean remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }

}