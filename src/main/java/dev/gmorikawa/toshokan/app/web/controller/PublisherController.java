package dev.gmorikawa.toshokan.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.gmorikawa.toshokan.app.web.shared.Meta;
import dev.gmorikawa.toshokan.app.web.shared.Page;
import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.publisher")
@RequestMapping(path = "app/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Publisher> publishers = service.getAll(pagination);
        
        model.addAttribute("meta", new Meta("List Publishers || Toshokan"));
        model.addAttribute("page", new Page("Publishers", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/publishers/list", pagination));
        model.addAttribute("publishers", publishers);

        return "publisher/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Publisher || Toshokan"));
        model.addAttribute("page", new Page("Publisher", "Create"));
        model.addAttribute("publisher", new Publisher());

        return "publisher/create";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        Publisher publisher = service.getById(id);

        model.addAttribute("meta", new Meta("Update Publisher || Toshokan"));
        model.addAttribute("page", new Page("Publisher", "Update"));
        model.addAttribute("publisher", publisher);

        return "publisher/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Publisher publisher) {
        service.create(publisher);

        return "redirect:/app/publishers/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute Publisher publisher) {
        service.update(id, publisher);

        return "redirect:/app/publishers/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute Publisher publisher) {
        service.remove(id);

        return "redirect:/app/publishers/list";
    }
}
