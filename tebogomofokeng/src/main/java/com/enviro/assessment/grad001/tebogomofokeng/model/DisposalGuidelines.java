package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class DisposalGuidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "disposal guidelines must NOT be empty")
    @Column(nullable = false, unique = true)
    private String disposalGuideline;

    @ManyToMany
    @JoinTable(
            name = "disposalGuideline_wasteCategory",
            joinColumns = @JoinColumn(name = "disposalGuidelineId"),
            inverseJoinColumns = @JoinColumn(name = "wasteCategoryId")
    )
    @JsonIgnore
    private List<WasteCategory> wasteCategories;

    public DisposalGuidelines() {
    }

    public DisposalGuidelines(Long id, String disposalGuideline) {
        this.id = id;
        this.disposalGuideline = disposalGuideline;
    }

}
