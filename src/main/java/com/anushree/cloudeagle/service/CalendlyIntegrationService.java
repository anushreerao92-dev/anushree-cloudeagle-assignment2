
package com.anushree.cloudeagle.service;


import com.anushree.cloudeagle.config.IntegrationConfig;
import com.anushree.cloudeagle.model.ExternalUser;
import com.anushree.cloudeagle.repo.IntegrationConfigRepository;
import com.anushree.cloudeagle.repo.ExternalUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendlyIntegrationService {

    private final IntegrationConfigRepository cfgRepo;
    private final GenericExternalApiService api;
    private final ExternalUserRepository userRepo;
    private IntegrationConfig cfg;

    public String fetchUsers() throws Exception {

        IntegrationConfig cfg = cfgRepo.findByIntegrationKey("calendly")
                .orElseThrow(() -> new RuntimeException("Calendly config missing"));

        JsonNode root = api.call(cfg);


        if (root.has("resource")) {
            JsonNode u = root.get("resource");

            userRepo.save(ExternalUser.builder()
                    .integrationKey("calendly")
                    .userId(u.path("uri").asText())
                    .email(u.path("email").asText())
                    .build());

            return "Fetched 1 user";
        }

        return "No users found";
    }

}
