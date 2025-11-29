
package com.anushree.cloudeagle.controller;


import com.anushree.cloudeagle.service.CalendlyIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserFetchController {

    private final CalendlyIntegrationService calendly;

    @GetMapping("/fetch/calendly")
    public String fetch() throws Exception {
        return calendly.fetchUsers();
    }
}
