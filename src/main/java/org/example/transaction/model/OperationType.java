package org.example.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OperationType")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationTypeId;

    @Column(nullable = false)
    private String description;

    @Column(name = "is_credit")
    private boolean credit;

}