package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import com.enviro.assessment.grad001.tebogomofokeng.repository.WasteCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {
    private final WasteCategoryRepository wasteCategoryRepository;

    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public ResponseEntity<List<WasteCategory>> getAllWasteCategories(Pageable pageable) {
        Page<WasteCategory> allWasteCategoriesData = wasteCategoryRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        return ResponseEntity.ok(allWasteCategoriesData.getContent());
    }

    public ResponseEntity<WasteCategory> getWasteCategoryById (Long wasteCategoryId) {
        Optional<WasteCategory> optionalWasteCategory = wasteCategoryRepository.findById(wasteCategoryId);

        if (optionalWasteCategory.isPresent()) {
            WasteCategory wasteCategory = optionalWasteCategory.get();

            return ResponseEntity.ok(wasteCategory);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> createWasteCategory (WasteCategory newWasteCategoryRequest) {
        WasteCategory saveWasteCategory = new WasteCategory();

        saveWasteCategory.setId(null);
        saveWasteCategory.setWasteCategory(newWasteCategoryRequest.getWasteCategory());

        wasteCategoryRepository.save(saveWasteCategory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> updateWasteCategoryById (WasteCategory updatedWasteCategory,Long wasteCategoryId) {
        Optional<WasteCategory> optionalWasteCategory = wasteCategoryRepository.findById(wasteCategoryId);

        if (optionalWasteCategory.isPresent()) {
            WasteCategory wasteCategory = optionalWasteCategory.get();
            wasteCategory.setWasteCategory(updatedWasteCategory.getWasteCategory());
            wasteCategoryRepository.save(wasteCategory);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteWasteCategoryById(Long wasteCategoryId) {
        Optional<WasteCategory> optionalWasteCategory = wasteCategoryRepository.findById(wasteCategoryId);

        if (optionalWasteCategory.isPresent()) {
            WasteCategory wasteCategory = optionalWasteCategory.get();
            wasteCategoryRepository.delete(wasteCategory);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
