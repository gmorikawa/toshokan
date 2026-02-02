package dev.gmorikawa.toshokan.unit.user;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.user.User;
import dev.gmorikawa.toshokan.core.user.UserRepository;
import dev.gmorikawa.toshokan.core.user.UserService;

public abstract class UserTestEnvironment {

    @Autowired
    protected UserRepository repository;

    @Autowired
    protected UserService service;

    protected void clean(User user) {
        repository.delete(user);
    }

}
