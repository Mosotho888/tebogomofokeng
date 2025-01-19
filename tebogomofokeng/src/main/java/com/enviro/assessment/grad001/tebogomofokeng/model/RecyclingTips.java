package com.enviro.assessment.grad001.tebogomofokeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Recycling tips must NOT be empty")
    @Column(nullable = false, unique = true)
    private String recyclingTip;

    @ManyToOne
    @JoinColumn(name = "wasteCategoryId")
    @JsonBackReference
    private WasteCategory wasteCategory;

    public RecyclingTips() {
    }

    public RecyclingTips(Long id, String recyclingTip) {
        this.id = id;
        this.recyclingTip = recyclingTip;
    }
}
