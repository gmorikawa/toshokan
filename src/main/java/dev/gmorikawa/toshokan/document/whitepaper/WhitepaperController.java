package dev.gmorikawa.toshokan.document.whitepaper;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/whitepapers")
public class WhitepaperController {
    private final WhitepaperService service;

    public WhitepaperController(WhitepaperService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Whitepaper> getAll() {
        return service.getAll();
    }

    @GetMapping("/year/{year}")
    public Whitepaper getByYear(@PathVariable Integer year) {
        return service.getByYear(year);
    }

    @GetMapping("/{id}")
    public Whitepaper getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Whitepaper create(@RequestBody Whitepaper entity) {
        return service.insert(entity);
    }

    @PatchMapping("/{id}")
    public Whitepaper update(@PathVariable String id, @RequestBody Whitepaper entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public Whitepaper remove(@PathVariable String id) {
        return service.remove(id);
    }
}
