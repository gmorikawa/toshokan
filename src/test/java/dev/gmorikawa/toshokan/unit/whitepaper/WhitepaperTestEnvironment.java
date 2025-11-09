package dev.gmorikawa.toshokan.unit.whitepaper;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.domain.document.whitepaper.Whitepaper;
import dev.gmorikawa.toshokan.domain.document.whitepaper.WhitepaperRepository;
import dev.gmorikawa.toshokan.domain.document.whitepaper.WhitepaperService;

public abstract class WhitepaperTestEnvironment {

    @Autowired
    protected WhitepaperRepository repository;

    @Autowired
    protected WhitepaperService service;

    protected void clean(Whitepaper whitepaper) {
        repository.delete(whitepaper);
    }
}