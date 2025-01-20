package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "waste category must NOT be empty")
    @Column(nullable = false, unique = true)
    private String wasteCategory;

    @ManyToMany(mappedBy = "wasteCategories")
    private List<DisposalGuidelines> disposalGuidelines;

    @ManyToMany(mappedBy = "wasteCategories")
    private List<RecyclingTips> recyclingTips;

    public WasteCategory() {
    }

    public WasteCategory(Long id, String wasteCategory) {
        this.id = id;
        this.wasteCategory = wasteCategory;
    }

}
