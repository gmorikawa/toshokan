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
import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.category.CategoryService;
import dev.gmorikawa.toshokan.shared.PaginationComponent;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Controller("web.category")
@RequestMapping(path = "categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(
        Model model,
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(page, size);
        List<Category> categories = service.getAll(pagination);
        
        model.addAttribute("meta", new Meta("List Categories || Toshokan"));
        model.addAttribute("page", new Page("List Categories"));
        model.addAttribute("pagination", new PaginationComponent("/categories/list", pagination));
        model.addAttribute("categories", categories);

        return "category/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meta", new Meta("Create Category || Toshokan"));
        model.addAttribute("page", new Page("Create Category"));
        model.addAttribute("category", new Category());

        return "category/create";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        Category category = service.getById(id);

        model.addAttribute("meta", new Meta("Update Category || Toshokan"));
        model.addAttribute("page", new Page("Update Category"));
        model.addAttribute("category", category);

        return "category/update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category) {
        service.create(category);

        return "redirect:/categories/list";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable String id, @ModelAttribute Category category) {
        service.update(id, category);

        return "redirect:/categories/list";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable String id, @ModelAttribute Category category) {
        service.remove(id);

        return "redirect:/categories/list";
    }
}
