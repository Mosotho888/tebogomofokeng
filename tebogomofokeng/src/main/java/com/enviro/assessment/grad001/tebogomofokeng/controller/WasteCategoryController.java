package com.enviro.assessment.grad001.tebogomofokeng.controller;
import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import com.enviro.assessment.grad001.tebogomofokeng.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-category/")
public class WasteCategoryController {
    private final WasteCategoryService wasteCategoryService;

    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories(Pageable pageable) {

        return wasteCategoryService.getAllWasteCategories(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        return wasteCategoryService.getWasteCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createWasteCategory(@Valid @RequestBody WasteCategory newWasteCategory) {
        return wasteCategoryService.createWasteCategory(newWasteCategory);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateWasteCategory(@PathVariable Long id, @Valid @RequestBody WasteCategory updatedWasteCategory) {
        return  wasteCategoryService.updateWasteCategoryById(updatedWasteCategory, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWasteCategoryById (@PathVariable Long id) {
        return  wasteCategoryService.deleteWasteCategoryById(id);
    }
}

