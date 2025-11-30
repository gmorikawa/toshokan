package dev.gmorikawa.toshokan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.application.rest.controller.BookController;
import dev.gmorikawa.toshokan.application.rest.controller.PublisherController;
import dev.gmorikawa.toshokan.application.rest.controller.TopicController;
import dev.gmorikawa.toshokan.application.rest.controller.UserController;
import dev.gmorikawa.toshokan.application.rest.controller.WhitepaperController;
import dev.gmorikawa.toshokan.auth.AuthenticationController;

@SpringBootTest
public class SmokeTest {

    @Autowired
    UserController userController;

    @Autowired
    BookController bookController;

    @Autowired
    WhitepaperController whitepaperController;

    @Autowired
    PublisherController publisherController;

    @Autowired
    TopicController topicController;

    @Autowired
    AuthenticationController authenticationController;

    @Test
    void contextLoads() throws Exception {
        assert userController != null : "UserController bean should be loaded";
        assert bookController != null : "BookController bean should be loaded";
        assert whitepaperController != null : "WhitepaperController bean should be loaded";
        assert publisherController != null : "PublisherController bean should be loaded";
        assert topicController != null : "TopicController bean should be loaded";
        assert authenticationController != null : "AuthenticationController bean should be loaded";
    }

}
