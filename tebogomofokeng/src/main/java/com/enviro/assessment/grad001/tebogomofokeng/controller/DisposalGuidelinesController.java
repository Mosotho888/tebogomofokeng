package com.enviro.assessment.grad001.tebogomofokeng.controller;

import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.service.DisposalGuidelinesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposal-guidelines/")
public class DisposalGuidelinesController {
    private final DisposalGuidelinesService disposalGuidelinesService;

    public DisposalGuidelinesController(DisposalGuidelinesService disposalGuidelinesService) {
        this.disposalGuidelinesService = disposalGuidelinesService;
    }

    @GetMapping
    public ResponseEntity<List<DisposalGuidelines>> getAllDisposalGuidelines(Pageable pageable) {
        return disposalGuidelinesService.getAllDisposalGuidelines(pageable);
    }
    @GetMapping("{id}")
    public ResponseEntity<DisposalGuidelines> getDisposalGuidelineById(@PathVariable Long id) {
        return disposalGuidelinesService.getDisposalGuidelinesById(id);
    }
    @PostMapping
    public ResponseEntity<Void> createDisposalGuideline(@Valid @RequestBody DisposalGuidelines newDisposalGuidelineRequest) {
        return disposalGuidelinesService.createDisposalGuideline(newDisposalGuidelineRequest);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateDisposalGuideline(@Valid @RequestBody DisposalGuidelines updatedDisposalGuideline, @PathVariable Long id) {
        return disposalGuidelinesService.updateDisposalGuidelines(id, updatedDisposalGuideline);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long id) {
        return disposalGuidelinesService.deleteDisposalGuideline(id);
    }
}
