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
@RequestMapping("/api/recycling-tips/")
public class RecyclingTipsController {
    private final RecyclingTipsService recyclingTipsService;

    public RecyclingTipsController(RecyclingTipsService recyclingTipsService) {
        this.recyclingTipsService = recyclingTipsService;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTipResponseDTO>> getAllRecyclingTips(Pageable pageable) {
        return recyclingTipsService.getAllRecyclingTips(pageable);
    }
    @GetMapping("{id}")
    public ResponseEntity<RecyclingTipResponseDTO> getRecyclingTipById(@PathVariable Long id) {
        return recyclingTipsService.getRecyclingTipById(id);
    }
    @PostMapping
    public ResponseEntity<Void> createRecyclingTip(@Valid @RequestBody RecyclingTips newRecyclingTipRequest) {
        return recyclingTipsService.createRecyclingTip(newRecyclingTipRequest);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateRecyclingTip(@PathVariable Long id, @Valid @RequestBody RecyclingTips updatedRecyclingTip) {
        return recyclingTipsService.updateRecyclingTips(id, updatedRecyclingTip);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRecyclingTipById (@PathVariable Long id) {
        return recyclingTipsService.deleteRecyclingTipById(id);
    }

    @GetMapping("/{recyclingTipId}/waste-categories")
    public ResponseEntity<List<WasteCategoryResponseDTO>> getWasteCategoriesByRecyclingId(@PathVariable Long recyclingTipId) {
        return recyclingTipsService.getWasteCategoriesByRecyclingId(recyclingTipId);
    }



}
