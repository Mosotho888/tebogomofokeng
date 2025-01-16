package com.enviro.assessment.grad001.tebogomofokeng.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Recycling tips must NOT be empty")
    private String recyclingTips;
    private Long categoryId;

    public RecyclingTips() {
    }

    public RecyclingTips(Long id, String recyclingTips) {
        this.id = id;
        this.recyclingTips = recyclingTips;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecyclingTips() {
        return recyclingTips;
    }

    public void setRecyclingTips(String recyclingTips) {
        this.recyclingTips = recyclingTips;
    }
}
