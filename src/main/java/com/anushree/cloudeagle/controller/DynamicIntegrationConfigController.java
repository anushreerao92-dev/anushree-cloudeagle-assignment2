
package com.anushree.cloudeagle.controller;

import com.anushree.cloudeagle.config.IntegrationConfig;
import com.anushree.cloudeagle.repo.IntegrationConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class DynamicIntegrationConfigController {

    private final IntegrationConfigRepository repo;

    @PostMapping
    public IntegrationConfig save(@RequestBody IntegrationConfig cfg) {
        return repo.save(cfg);
    }
}
