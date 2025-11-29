package com.anushree.cloudeagle.service;

import com.anushree.cloudeagle.config.IntegrationConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericExternalApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Executes ANY external API request dynamically based on IntegrationConfig
     */
    public JsonNode call(IntegrationConfig cfg) throws Exception {

        String url = cfg.getBaseUrl() + cfg.getEndpoint();
        log.info("Calling external API: {}", url);

        // Build headers dynamically
        HttpHeaders headers = new HttpHeaders();
        if (cfg.getHeaders() != null) {
            for (Map.Entry<String, String> h : cfg.getHeaders().entrySet()) {
                headers.add(h.getKey(), h.getValue());
            }
        }

        HttpEntity<?> entity;

        // For POST/PUT, body may exist
        if (cfg.getRequestBody() != null) {
            entity = new HttpEntity<>(cfg.getRequestBody(), headers);
        } else {
            entity = new HttpEntity<>(headers);
        }

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.valueOf(cfg.getMethodType().toUpperCase()),
                    entity,
                    String.class
            );

            log.info("External API Response: {}", response.getStatusCode());
            return mapper.readTree(response.getBody());

        } catch (HttpClientErrorException e) {
            log.error("External API Failed: {} {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("API call failed: " + e.getResponseBodyAsString());
        }
    }
}
