package dev.gmorikawa.toshokan.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.id <> ?2")
    Optional<User> findUserByUsername(String username, String ignoreId);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.id <> ?2")
    Optional<User> findUserByEmail(String email, String ignoreId);
}
