package com.enviro.assessment.grad001.tebogomofokeng.DTOs;

import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WasteCategoryResponseDTO {
    private Long id;
    private String wasteCategory;

    public WasteCategoryResponseDTO(Long id, String wasteCategory) {
        this.id = id;
        this.wasteCategory = wasteCategory;
    }
}
