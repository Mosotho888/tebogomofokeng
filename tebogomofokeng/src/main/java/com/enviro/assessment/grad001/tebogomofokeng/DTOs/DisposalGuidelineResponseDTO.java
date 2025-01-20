package com.enviro.assessment.grad001.tebogomofokeng.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class DisposalGuidelineResponseDTO {
    private Long id;
    private String disposalGuideline;
    private List<WasteCategoryResponseDTO> wasteCategories;

    public DisposalGuidelineResponseDTO(Long id, String disposalGuideline, List<WasteCategoryResponseDTO> wasteCategories) {
        this.id = id;
        this.disposalGuideline = disposalGuideline;
        this.wasteCategories = wasteCategories;
    }
}
