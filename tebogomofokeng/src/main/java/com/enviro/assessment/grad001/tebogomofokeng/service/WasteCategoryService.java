package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryAlreadyExist;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import com.enviro.assessment.grad001.tebogomofokeng.repository.DisposalGuidelinesRepository;
import com.enviro.assessment.grad001.tebogomofokeng.repository.RecyclingTipsRepository;
import com.enviro.assessment.grad001.tebogomofokeng.repository.WasteCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class WasteCategoryService {
    private final WasteCategoryRepository wasteCategoryRepository;
    private final RecyclingTipsRepository recyclingTipsRepository;
    private final DisposalGuidelinesRepository disposalGuidelinesRepository;
    private final DisposalGuidelinesService disposalGuidelinesService;
    private final RecyclingTipsService recyclingTipsService;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository, RecyclingTipsRepository recyclingTipsRepository, DisposalGuidelinesRepository disposalGuidelinesRepository, DisposalGuidelinesService disposalGuidelinesService, RecyclingTipsService recyclingTipsService) {
        this.wasteCategoryRepository = wasteCategoryRepository;
        this.recyclingTipsRepository = recyclingTipsRepository;
        this.disposalGuidelinesRepository = disposalGuidelinesRepository;
        this.disposalGuidelinesService = disposalGuidelinesService;
        this.recyclingTipsService = recyclingTipsService;
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

    public ResponseEntity<Void> addRecyclingTipToWasteCategory(Long recyclingTipId, Long wasteCategoryId) {
        RecyclingTips recyclingTip = recyclingTipsService.getRecyclingTip(recyclingTipId);
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        if (isWasteCategoryAssociatedWithRecyclingTip(recyclingTip, wasteCategory)) {
            throw new WasteCategoryAlreadyExist();
        }

        recyclingTip.getWasteCategories().add(wasteCategory);

        recyclingTipsRepository.save(recyclingTip);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private static boolean isWasteCategoryAssociatedWithRecyclingTip(RecyclingTips recyclingTip, WasteCategory wasteCategory) {
        return recyclingTip.getWasteCategories().contains(wasteCategory);
    }

    public ResponseEntity<Void> addDisposalGuidelineToWasteCategory(Long disposalGuidelineId, Long wasteCategoryId) {
        DisposalGuidelines disposalGuideline = disposalGuidelinesService.getDisposalGuideline(disposalGuidelineId);
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        if (isWasteCategoryAssociatedWithDisposalGuideline(disposalGuideline, wasteCategory)) {
            throw new WasteCategoryAlreadyExist();
        }

        disposalGuideline.getWasteCategories().add(wasteCategory);

        wasteCategoryRepository.save(wasteCategory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private static boolean isWasteCategoryAssociatedWithDisposalGuideline(DisposalGuidelines disposalGuideline, WasteCategory wasteCategory) {
        return disposalGuideline.getWasteCategories().contains(wasteCategory);
    }

    public ResponseEntity<Void> deleteRecyclingTipFromWasteCategory(Long wasteCategoryId, Long recyclingTipId) {
        RecyclingTips recyclingTip = recyclingTipsService.getRecyclingTip(recyclingTipId);
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        recyclingTip.getWasteCategories().remove(wasteCategory);

        recyclingTipsRepository.save(recyclingTip);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteDisposalGuidelineFromWasteCategory(Long wasteCategoryId, Long disposalGuidelineId) {
        DisposalGuidelines disposalGuidelines = disposalGuidelinesService.getDisposalGuideline(disposalGuidelineId);
        WasteCategory wasteCategory = getWasteCategory(wasteCategoryId);

        disposalGuidelines.getWasteCategories().remove(wasteCategory);

        disposalGuidelinesRepository.save(disposalGuidelines);

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
