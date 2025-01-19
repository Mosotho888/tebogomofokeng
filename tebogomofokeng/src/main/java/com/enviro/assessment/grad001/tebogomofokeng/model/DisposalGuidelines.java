package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Setter
@Getter
public class DisposalGuidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "disposal guidelines must NOT be empty")
    @Column(nullable = false, unique = true)
    private String disposalGuideline;
    @ManyToOne
    @JoinColumn(name = "wasteCategoryId")
    @JsonBackReference
    private WasteCategory wasteCategory;

    public DisposalGuidelines() {
    }

    public DisposalGuidelines(Long id, String disposalGuideline) {
        this.id = id;
        this.disposalGuideline = disposalGuideline;
    }

}
