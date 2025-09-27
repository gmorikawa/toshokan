package dev.gmorikawa.toshokan.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void testGetUsers() {
        List<User> list = new ArrayList<User>();
        list.add(new User("test1", "user1", "password1", "user1@email.com", UserRole.ADMIN, "User Uno"));
        list.add(new User("test2", "user2", "password2", "user2@email.com", UserRole.LIBRARIAN, "User Dos"));
        list.add(new User("test3", "user3", "password3", "user3@email.com", UserRole.READER, "User Tres"));
        list.add(new User("test4", "user4", "password4", "user4@email.com", UserRole.READER, "User Cuatro"));

        Mockito.when(repository.findAll()).thenReturn(list);

        List<User> users = service.getAll();

        assert users.size() == 4 : "Expected 4 users, but got " + users.size();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}
