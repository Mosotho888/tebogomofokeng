package com.enviro.assessment.grad001.tebogomofokeng.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class DisposalGuidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "disposal guidelines must NOT be empty")
    private String disposal_guidelines;
    private Long categoryId;

    public DisposalGuidelines() {
    }

    public DisposalGuidelines(Long id, String disposal_guidelines) {
        this.id = id;
        this.disposal_guidelines = disposal_guidelines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisposal_guidelines() {
        return disposal_guidelines;
    }

    public void setDisposal_guidelines(String disposal_guidelines) {
        this.disposal_guidelines = disposal_guidelines;
    }
}
