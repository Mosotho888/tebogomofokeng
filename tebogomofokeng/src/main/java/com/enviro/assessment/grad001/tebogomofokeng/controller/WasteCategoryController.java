package com.enviro.assessment.grad001.tebogomofokeng.controller;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import com.enviro.assessment.grad001.tebogomofokeng.service.RecyclingTipsService;
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
    private final RecyclingTipsService recyclingTipsService;

    public WasteCategoryController(WasteCategoryService wasteCategoryService, RecyclingTipsService recyclingTipsService) {
        this.wasteCategoryService = wasteCategoryService;
        this.recyclingTipsService = recyclingTipsService;
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

    @PostMapping("{id}/recycling-tip")
    public ResponseEntity<Void> addRecyclingTipToWasteCategory(@RequestBody RecyclingTips newRecyclingTipRequest, @PathVariable Long id) {
        return wasteCategoryService.addRecyclingTipToWasteCategory(newRecyclingTipRequest.getId(), id);
    }

    @DeleteMapping("{wasteCategoryId}/recycling-tip/{recyclingTipId}")
    public ResponseEntity<Void> deleteRecyclingTipFromWasteCategory(@PathVariable Long wasteCategoryId, @PathVariable Long recyclingTipId) {
        return wasteCategoryService.deleteRecyclingTipFromWasteCategory(wasteCategoryId,recyclingTipId);
    }

    @PostMapping("{id}/disposal-guideline")
    public ResponseEntity<Void> addDisposalGuidelineToWasteCategory(@RequestBody DisposalGuidelines newDisposalGuidelineRequest, @PathVariable Long id) {
        return wasteCategoryService.addDisposalGuidelineToWasteCategory(newDisposalGuidelineRequest.getId(), id);
    }

    @DeleteMapping("{wasteCategoryId}/disposal-guideline/{disposalGuidelineId}")
    public ResponseEntity<Void> deleteDisposalGuidelineFromWasteCategory(@PathVariable Long wasteCategoryId, @PathVariable Long disposalGuidelineId) {
        return wasteCategoryService.deleteDisposalGuidelineFromWasteCategory(wasteCategoryId,disposalGuidelineId);
    }
}

