package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.DTOs.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineAlreadyExists;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import com.enviro.assessment.grad001.tebogomofokeng.repository.DisposalGuidelinesRepository;
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
public class DisposalGuidelinesService {
    private final DisposalGuidelinesRepository disposalGuidelinesRepository;

    public DisposalGuidelinesService(DisposalGuidelinesRepository disposalGuidelinesRepository) {
        this.disposalGuidelinesRepository = disposalGuidelinesRepository;
    }

    public ResponseEntity<Void> createDisposalGuideline(DisposalGuidelines newDisposalGuidelineRequest) {
        Boolean doesDisposalGuidelineExist = disposalGuidelinesRepository.existsByDisposalGuideline(newDisposalGuidelineRequest.getDisposalGuideline());

        if (doesDisposalGuidelineExist) {
            throw new DisposalGuidelineAlreadyExists();
        }

        DisposalGuidelines disposalGuidelines = new DisposalGuidelines();

        saveDisposalGuideline(newDisposalGuidelineRequest, disposalGuidelines);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    public ResponseEntity<List<DisposalGuidelineResponseDTO>> getAllDisposalGuidelines(Pageable pageable) {
        Page<DisposalGuidelines> allDisposalGuidelines = disposalGuidelinesRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        List<DisposalGuidelineResponseDTO> disposalGuidelineResponse = allDisposalGuidelines.getContent().stream().map(
                disposalGuidelinesResponse -> new DisposalGuidelineResponseDTO(
                        disposalGuidelinesResponse.getId(),
                        disposalGuidelinesResponse.getDisposalGuideline(),
                        disposalGuidelinesResponse.getWasteCategories()
                                .stream()
                                .map(wasteCategory -> new WasteCategoryResponseDTO(
                                        wasteCategory.getId(), wasteCategory.getWasteCategory())).collect(Collectors.toList()))
        ).toList();

        return ResponseEntity.ok(disposalGuidelineResponse);
    }

    public ResponseEntity<DisposalGuidelineResponseDTO> getDisposalGuidelinesById(Long disposalGuidelineId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        DisposalGuidelineResponseDTO disposalGuidelineResponse = new DisposalGuidelineResponseDTO(
                disposalGuideline.getId(),
                disposalGuideline.getDisposalGuideline(),
                disposalGuideline.getWasteCategories()
                        .stream().map(wasteCategory -> new WasteCategoryResponseDTO(
                                wasteCategory.getId(), wasteCategory.getWasteCategory())).collect(Collectors.toList())
        );

        return ResponseEntity.ok(disposalGuidelineResponse);
    }

    public ResponseEntity<Void> updateDisposalGuidelines(Long disposalGuidelineId, DisposalGuidelines updatedDisposalGuideline) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        saveDisposalGuideline(updatedDisposalGuideline, disposalGuideline);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteDisposalGuideline(Long disposalGuidelineId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        disposalGuidelinesRepository.delete(disposalGuideline);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<WasteCategoryResponseDTO>> getWasteCategoriesByDisposalGuidelineId(Long disposalGuidelineId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        List<WasteCategoryResponseDTO> wasteCategoryResponse = disposalGuideline.getWasteCategories()
                .stream().map(wasteCategory -> new WasteCategoryResponseDTO(
                        wasteCategory.getId(), wasteCategory.getWasteCategory())).toList();

        return ResponseEntity.ok(wasteCategoryResponse);
    }

    private void saveDisposalGuideline(DisposalGuidelines newDisposalGuideline, DisposalGuidelines disposalGuidelines) {
        disposalGuidelines.setDisposalGuideline(newDisposalGuideline.getDisposalGuideline());
        disposalGuidelinesRepository.save(disposalGuidelines);
    }

    public DisposalGuidelines getDisposalGuideline(Long disposalGuidelineId) {
        Optional<DisposalGuidelines> optionalDisposalGuideline = disposalGuidelinesRepository.findById(disposalGuidelineId);

        if (optionalDisposalGuideline.isPresent()) {

            return optionalDisposalGuideline.get();

        }
        throw new DisposalGuidelineNotFoundException();
    }
}
