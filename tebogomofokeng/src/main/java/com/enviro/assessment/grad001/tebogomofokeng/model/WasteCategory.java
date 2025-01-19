package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "waste category must NOT be empty")
    @Column(nullable = false, unique = true)
    private String wasteCategory;

    @OneToMany(mappedBy = "wasteCategory")
    @JsonManagedReference
    private List<DisposalGuidelines> disposalGuidelines;

    @OneToMany(mappedBy = "wasteCategory")
    @JsonManagedReference
    private List<RecyclingTips> recyclingTips;

    public WasteCategory() {
    }

    public WasteCategory(Long id, String wasteCategory) {
        this.id = id;
        this.wasteCategory = wasteCategory;
    }

}
