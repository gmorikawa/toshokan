package dev.gmorikawa.toshokan.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public User update(String id, User user) {
        User current = repository.getReferenceById(id);

        if(current != null) {
            current.setEmail(user.getEmail());
            current.setUsername(user.getUsername());
        }

        return repository.save(current);
    }

    public boolean remove(String id) {
        User user = repository.getReferenceById(id);
        repository.delete(user);

        return true;
    }
}
