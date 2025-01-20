package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Recycling tips must NOT be empty")
    @Column(nullable = false, unique = true)
    private String recyclingTip;

//    @ManyToOne
//    @JoinColumn(name = "wasteCategoryId")
//    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "recyclingTip_wasteCategory",
            joinColumns = @JoinColumn(name = "recyclingTipId"),
            inverseJoinColumns = @JoinColumn(name = "wasteCategoryId")
    )
    @JsonIgnore
    private List<WasteCategory> wasteCategories;

    public RecyclingTips() {
    }

    public RecyclingTips(Long id, String recyclingTip) {
        this.id = id;
        this.recyclingTip = recyclingTip;
    }
}
