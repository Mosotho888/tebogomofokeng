package com.enviro.assessment.grad001.tebogomofokeng.controller;

import com.enviro.assessment.grad001.tebogomofokeng.DTOs.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import com.enviro.assessment.grad001.tebogomofokeng.service.RecyclingTipsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipsController {
    private final RecyclingTipsService recyclingTipsService;

    public RecyclingTipsController(RecyclingTipsService recyclingTipsService) {
        this.recyclingTipsService = recyclingTipsService;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTipResponseDTO>> getAllRecyclingTips(Pageable pageable) {
        return recyclingTipsService.getAllRecyclingTips(pageable);
    }
    @GetMapping("/{recyclingTipId}")
    public ResponseEntity<RecyclingTipResponseDTO> getRecyclingTipById(@PathVariable Long recyclingTipId) {
        return recyclingTipsService.getRecyclingTipById(recyclingTipId);
    }
    @PostMapping
    public ResponseEntity<Void> createRecyclingTip(@Valid @RequestBody RecyclingTips newRecyclingTipRequest) {
        return recyclingTipsService.createRecyclingTip(newRecyclingTipRequest);
    }
    @PutMapping("/{recyclingTipId}")
    public ResponseEntity<Void> updateRecyclingTip(@PathVariable Long recyclingTipId, @Valid @RequestBody RecyclingTips updatedRecyclingTip) {
        return recyclingTipsService.updateRecyclingTips(recyclingTipId, updatedRecyclingTip);
    }
    @DeleteMapping("/{recyclingTipId}")
    public ResponseEntity<Void> deleteRecyclingTipById (@PathVariable Long recyclingTipId) {
        return recyclingTipsService.deleteRecyclingTipById(recyclingTipId);
    }

    @GetMapping("/{recyclingTipId}/waste-categories")
    public ResponseEntity<List<WasteCategoryResponseDTO>> getWasteCategoriesByRecyclingId(@PathVariable Long recyclingTipId) {
        return recyclingTipsService.getWasteCategoriesByRecyclingId(recyclingTipId);
    }

    @GetMapping("/{recyclingTipId}/waste-categories/{wasteCategoryId}")
    public ResponseEntity<WasteCategoryResponseDTO> getWasteCategoryByRecyclingTipId(@PathVariable Long recyclingTipId, @PathVariable Long wasteCategoryId) {
        return recyclingTipsService.getWasteCategoryByRecyclingId(recyclingTipId, wasteCategoryId);
    }



}
