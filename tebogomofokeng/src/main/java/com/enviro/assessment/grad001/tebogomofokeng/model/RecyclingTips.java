package com.enviro.assessment.grad001.tebogomofokeng.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Recycling tips must NOT be empty")
    private String recyclingTip;
    private Long categoryId;

    public RecyclingTips() {
    }

    public RecyclingTips(Long id, String recyclingTip) {
        this.id = id;
        this.recyclingTip = recyclingTip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecyclingTip() {
        return recyclingTip;
    }

    public void setRecyclingTip(String recyclingTip) {
        this.recyclingTip = recyclingTip;
    }
}
