package com.enviro.assessment.grad001.tebogomofokeng.controller;

import com.enviro.assessment.grad001.tebogomofokeng.DTOs.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.service.DisposalGuidelinesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelinesController {
    private final DisposalGuidelinesService disposalGuidelinesService;

    public DisposalGuidelinesController(DisposalGuidelinesService disposalGuidelinesService) {
        this.disposalGuidelinesService = disposalGuidelinesService;
    }

    @GetMapping
    public ResponseEntity<List<DisposalGuidelineResponseDTO>> getAllDisposalGuidelines(Pageable pageable) {
        return disposalGuidelinesService.getAllDisposalGuidelines(pageable);
    }
    @GetMapping("/{disposalGuidelineId}")
    public ResponseEntity<DisposalGuidelineResponseDTO> getDisposalGuidelineById(@PathVariable Long disposalGuidelineId) {
        return disposalGuidelinesService.getDisposalGuidelinesById(disposalGuidelineId);
    }
    @PostMapping
    public ResponseEntity<Void> createDisposalGuideline(@Valid @RequestBody DisposalGuidelines newDisposalGuidelineRequest) {
        return disposalGuidelinesService.createDisposalGuideline(newDisposalGuidelineRequest);
    }
    @PutMapping("/{disposalGuidelineId}")
    public ResponseEntity<Void> updateDisposalGuideline(@Valid @RequestBody DisposalGuidelines updatedDisposalGuideline, @PathVariable Long disposalGuidelineId) {
        return disposalGuidelinesService.updateDisposalGuidelines(disposalGuidelineId, updatedDisposalGuideline);
    }
    @DeleteMapping("/{disposalGuidelineId}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long disposalGuidelineId) {
        return disposalGuidelinesService.deleteDisposalGuideline(disposalGuidelineId);
    }

    @GetMapping("/{disposalGuidelineId}/waste-categories")
    public ResponseEntity<List<WasteCategoryResponseDTO>> getWasteCategoriesByGuidelineId(@PathVariable Long disposalGuidelineId) {
        return disposalGuidelinesService.getWasteCategoriesByDisposalGuidelineId(disposalGuidelineId);
    }

    @GetMapping("/{disposalGuidelineId}/waste-categories/{wasteCategoryId}")
    public ResponseEntity<WasteCategoryResponseDTO> getWasteCategoryByGuidelineId(@PathVariable Long disposalGuidelineId, @PathVariable Long wasteCategoryId) {
        return disposalGuidelinesService.getWasteCategoryByDisposalGuideline(disposalGuidelineId, wasteCategoryId);
    }
}
