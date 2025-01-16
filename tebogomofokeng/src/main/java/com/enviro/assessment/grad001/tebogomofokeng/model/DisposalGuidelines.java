package com.enviro.assessment.grad001.tebogomofokeng.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class DisposalGuidelines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "disposal guidelines must NOT be empty")
    private String disposalGuideline;
    private Long categoryId;

    public DisposalGuidelines() {
    }

    public DisposalGuidelines(Long id, String disposalGuideline) {
        this.id = id;
        this.disposalGuideline = disposalGuideline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisposalGuideline() {
        return disposalGuideline;
    }

    public void setDisposalGuideline(String disposalGuideline) {
        this.disposalGuideline = disposalGuideline;
    }
}
