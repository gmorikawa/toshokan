package dev.gmorikawa.toshokan.app.web.controller;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.gmorikawa.toshokan.app.web.auth.AuthorizationCookie;
import dev.gmorikawa.toshokan.app.web.shared.Meta;
import dev.gmorikawa.toshokan.auth.AuthenticationService;
import dev.gmorikawa.toshokan.auth.Credential;
import dev.gmorikawa.toshokan.auth.TokenCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller("web.authentication")
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    private SecurityContextRepository securityContextRepository
            = new HttpSessionSecurityContextRepository();

    @GetMapping("/login")
    public String login(
        Model model
    ) {
        model.addAttribute("meta", new Meta("Login || Toshokan"));
        model.addAttribute("credential", new Credential());

        return "auth/login";
    }

    @PostMapping("/login")
    public String login(
        @ModelAttribute Credential credential,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        TokenCredential tokenCredential = service.login(credential);
        response.addCookie(new AuthorizationCookie(tokenCredential.getAccessToken()));

        return "redirect:/users/list";
    }

    @PostMapping("/logout")
    public String logout(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        response.addCookie(new AuthorizationCookie(null));

        return "redirect:/auth/login";
    }
}
