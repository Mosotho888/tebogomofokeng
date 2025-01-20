package com.enviro.assessment.grad001.tebogomofokeng.DTOs;

import lombok.Data;


@Data
public class WasteCategoryResponseDTO {
    private Long id;
    private String wasteCategory;

    public WasteCategoryResponseDTO(Long id, String wasteCategory) {
        this.id = id;
        this.wasteCategory = wasteCategory;
    }
}
