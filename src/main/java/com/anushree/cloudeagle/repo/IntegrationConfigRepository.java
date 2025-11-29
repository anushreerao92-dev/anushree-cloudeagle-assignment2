
package com.anushree.cloudeagle.repo;


import com.anushree.cloudeagle.config.IntegrationConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntegrationConfigRepository extends JpaRepository<IntegrationConfig, Long> {
    Optional<IntegrationConfig> findByIntegrationKey(String key);
}
