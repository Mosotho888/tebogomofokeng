package com.enviro.assessment.grad001.tebogomofokeng.DTOs;

import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import lombok.Data;

import java.util.List;

@Data
public class RecyclingTipResponseDTO {
    private Long id;
    private String recyclingTip;
    private List<WasteCategoryResponseDTO> wasteCategories;

    public RecyclingTipResponseDTO(Long id, String recyclingTip, List<WasteCategoryResponseDTO> wasteCategories) {
        this.id = id;
        this.recyclingTip = recyclingTip;
        this.wasteCategories = wasteCategories;
    }
}
