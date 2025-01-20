package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.DTOs.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.RecyclingTipAlreadyExist;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.RecyclingTipsNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
import com.enviro.assessment.grad001.tebogomofokeng.repository.RecyclingTipsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecyclingTipsService {
    private final RecyclingTipsRepository recyclingTipsRepository;

    public RecyclingTipsService(RecyclingTipsRepository recyclingTipsRepository) {
        this.recyclingTipsRepository = recyclingTipsRepository;
    }

    public ResponseEntity<Void> createRecyclingTip(RecyclingTips newRecyclingTipsRequest) {
        Boolean doesRecyclingTipExist = recyclingTipsRepository.existsByRecyclingTip(newRecyclingTipsRequest.getRecyclingTip());

        if (doesRecyclingTipExist) {
            throw new RecyclingTipAlreadyExist();
        }
        RecyclingTips recyclingTip = new RecyclingTips();

        saveRecyclingTip(newRecyclingTipsRequest, recyclingTip);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<RecyclingTipResponseDTO>> getAllRecyclingTips(Pageable pageable) {
        Page<RecyclingTips> allRecyclingTipsData = recyclingTipsRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        List<RecyclingTipResponseDTO> recyclingTipResponse = mapToRecyclingTipResponseDTO(allRecyclingTipsData);

        return ResponseEntity.ok(recyclingTipResponse);
    }

    private static List<RecyclingTipResponseDTO> mapToRecyclingTipResponseDTO(Page<RecyclingTips> allRecyclingTipsData) {
        return allRecyclingTipsData.getContent().stream().map(
                recyclingTipsResponse -> new RecyclingTipResponseDTO(
                        recyclingTipsResponse.getId(),
                        recyclingTipsResponse.getRecyclingTip(),
                        mapToWasteCategoryDTO(recyclingTipsResponse))
        ).toList();
    }

    private static List<WasteCategoryResponseDTO> mapToWasteCategoryDTO(RecyclingTips recyclingTipsResponse) {
        return recyclingTipsResponse.getWasteCategories()
                .stream()
                .map(wasteCategory -> new WasteCategoryResponseDTO(
                        wasteCategory.getId(), wasteCategory.getWasteCategory())).collect(Collectors.toList());
    }

    public ResponseEntity<RecyclingTipResponseDTO> getRecyclingTipById(Long recyclingTipId) {
        RecyclingTips recyclingTip = getRecyclingTip(recyclingTipId);

        RecyclingTipResponseDTO recyclingTipResponse = new RecyclingTipResponseDTO(
                recyclingTip.getId(),
                recyclingTip.getRecyclingTip(),
                mapToWasteCategoryDTO(recyclingTip)
        );

        return ResponseEntity.ok(recyclingTipResponse);
    }

    public ResponseEntity<Void> updateRecyclingTips(Long recyclingTipId, RecyclingTips updatedRecyclingTip) {
        RecyclingTips recyclingTip = getRecyclingTip(recyclingTipId);

        saveRecyclingTip(updatedRecyclingTip, recyclingTip);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteRecyclingTipById(Long recyclingTipId) {
        RecyclingTips recyclingTip = getRecyclingTip(recyclingTipId);

        recyclingTipsRepository.delete(recyclingTip);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<WasteCategoryResponseDTO>> getWasteCategoriesByRecyclingId(Long recyclingTipId) {
        RecyclingTips recyclingTip = getRecyclingTip(recyclingTipId);

        List<WasteCategoryResponseDTO> wasteCategoryResponse = mapToWasteCategoryDTO(recyclingTip);

        return ResponseEntity.ok(wasteCategoryResponse);
    }

    public ResponseEntity<WasteCategoryResponseDTO> getWasteCategoryByRecyclingId(Long recyclingTipId, Long wasteCategoryId) {
        RecyclingTips recyclingTips = getRecyclingTip(recyclingTipId);

        Optional<WasteCategory> optionalWasteCategory = recyclingTips.getWasteCategories()
                .stream()
                .filter(wasteCategoryData -> wasteCategoryData.getId().equals(wasteCategoryId)).findFirst();

        if (optionalWasteCategory.isPresent()) {
            WasteCategory wasteCategory = optionalWasteCategory.get();

            WasteCategoryResponseDTO wasteCategoryResponseDTO = new WasteCategoryResponseDTO(
                    wasteCategory.getId(),
                    wasteCategory.getWasteCategory());

            return ResponseEntity.ok(wasteCategoryResponseDTO);
        }

        throw new WasteCategoryNotFoundException();
    }

    private void saveRecyclingTip(RecyclingTips newRecyclingTip, RecyclingTips recyclingTip) {
        recyclingTip.setRecyclingTip(newRecyclingTip.getRecyclingTip());

        recyclingTipsRepository.save(recyclingTip);
    }

    public RecyclingTips getRecyclingTip(Long recyclingTipId) {
        Optional<RecyclingTips> optionalRecyclingTip = recyclingTipsRepository.findById(recyclingTipId);

        if (optionalRecyclingTip.isPresent()) {

            return optionalRecyclingTip.get();

        }
        throw new RecyclingTipsNotFoundException();
    }
}
