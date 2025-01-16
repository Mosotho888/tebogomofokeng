package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryAlreadyExist;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryNotFoundException;
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
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        return ResponseEntity.ok(wasteCategory);
    }

    public ResponseEntity<Void> createWasteCategory (WasteCategory newWasteCategoryRequest) {
        Boolean doesWasteCategoryExist = wasteCategoryRepository.existsByWasteCategory(newWasteCategoryRequest.getWasteCategory());

        if (doesWasteCategoryExist) {
            throw new WasteCategoryAlreadyExist();
        }

        WasteCategory wasteCategory = new WasteCategory();

        saveWasteCategory(newWasteCategoryRequest, wasteCategory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> updateWasteCategoryById (WasteCategory updatedWasteCategory,Long wasteCategoryId) {
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        saveWasteCategory(updatedWasteCategory, wasteCategory);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteWasteCategoryById(Long wasteCategoryId) {
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        wasteCategoryRepository.delete(wasteCategory);

        return ResponseEntity.noContent().build();
    }

    private void saveWasteCategory(WasteCategory newWasteCategory, WasteCategory wasteCategory) {
        wasteCategory.setWasteCategory(newWasteCategory.getWasteCategory());

        wasteCategoryRepository.save(wasteCategory);
    }

    public WasteCategory getWasteCategory(Long wasteCategoryId) {
        Optional<WasteCategory> optionalWasteCategory = wasteCategoryRepository.findById(wasteCategoryId);

        if (optionalWasteCategory.isPresent()) {

            return optionalWasteCategory.get();

        }
        throw new WasteCategoryNotFoundException();
    }
}
