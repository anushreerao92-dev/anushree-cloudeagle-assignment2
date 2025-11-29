
package com.anushree.cloudeagle.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExternalUser {
    @Id @GeneratedValue
    private Long id;
    private String integrationKey;
    private String userId;
    private String email;
}
