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
import dev.gmorikawa.toshokan.domain.topic.Topic;
import dev.gmorikawa.toshokan.domain.topic.TopicService;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.topic")
@RequestMapping(path = "app/topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Topic> topics = service.getAll(pagination);
        
        model.addAttribute("meta", new Meta("List Topics || Toshokan"));
        model.addAttribute("page", new Page("Topics", "List"));
        model.addAttribute("pagination", new PaginationComponent("/app/topics/list", pagination));
        model.addAttribute("topics", topics);

        return "topic/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Topic || Toshokan"));
        model.addAttribute("page", new Page("Topic", "Create"));
        model.addAttribute("topic", new Topic());

        return "topic/create";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        Topic topic = service.getById(id);

        model.addAttribute("meta", new Meta("Update Topic || Toshokan"));
        model.addAttribute("page", new Page("Topic", "Update"));
        model.addAttribute("topic", topic);

        return "topic/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Topic topic) {
        service.create(topic);

        return "redirect:/app/topics/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute Topic topic) {
        service.update(id, topic);

        return "redirect:/app/topics/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute Topic topic) {
        service.remove(id);

        return "redirect:/app/topics/list";
    }
}
