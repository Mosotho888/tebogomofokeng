package com.enviro.assessment.grad001.tebogomofokeng.repository;

import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    Boolean existsByWasteCategory(String wasteCategory);
}
