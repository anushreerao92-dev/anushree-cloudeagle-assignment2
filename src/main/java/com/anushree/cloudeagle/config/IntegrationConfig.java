package com.anushree.cloudeagle.config;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "integration_config")
public class IntegrationConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique key identifying SaaS system: calendly, dropbox, etc.
    @Column(unique = true, nullable = false)
    private String integrationKey;

    @Column(nullable = false)
    private String baseUrl;       // e.g. https://api.calendly.com

    @Column(nullable = false)
    private String endpoint;      // e.g. /users

    @Column(nullable = false)
    private String methodType;    // GET, POST, PUT, DELETE

    // Dynamic headers stored as JSON in DB (H2, MySQL, Postgres)
    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> headers;

    // Optional JSON body for POST/PUT
    @Column(columnDefinition = "TEXT")
    private String requestBody;
}
