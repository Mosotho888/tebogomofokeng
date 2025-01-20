package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.DTOs.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.DTOs.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineAlreadyExists;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.WasteCategoryNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.model.WasteCategory;
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

        List<DisposalGuidelineResponseDTO> disposalGuidelineResponse = mapToDisposalGuidelineResponseDTO(allDisposalGuidelines);

        return ResponseEntity.ok(disposalGuidelineResponse);
    }

    private static List<DisposalGuidelineResponseDTO> mapToDisposalGuidelineResponseDTO(Page<DisposalGuidelines> allDisposalGuidelines) {
        return allDisposalGuidelines.getContent().stream().map(
                disposalGuidelinesResponse -> new DisposalGuidelineResponseDTO(
                        disposalGuidelinesResponse.getId(),
                        disposalGuidelinesResponse.getDisposalGuideline(),
                        mapToWasteCategoryResponseDTO(disposalGuidelinesResponse))
        ).toList();
    }

    private static List<WasteCategoryResponseDTO> mapToWasteCategoryResponseDTO(DisposalGuidelines disposalGuidelinesResponse) {
        return disposalGuidelinesResponse.getWasteCategories()
                .stream()
                .map(wasteCategory -> new WasteCategoryResponseDTO(
                        wasteCategory.getId(), wasteCategory.getWasteCategory())).collect(Collectors.toList());
    }

    public ResponseEntity<DisposalGuidelineResponseDTO> getDisposalGuidelinesById(Long disposalGuidelineId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        DisposalGuidelineResponseDTO disposalGuidelineResponse = new DisposalGuidelineResponseDTO(
                disposalGuideline.getId(),
                disposalGuideline.getDisposalGuideline(),
                mapToWasteCategoryResponseDTO(disposalGuideline)
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

        List<WasteCategoryResponseDTO> wasteCategoryResponse = mapToWasteCategoryResponseDTO(disposalGuideline);

        return ResponseEntity.ok(wasteCategoryResponse);
    }

    public ResponseEntity<WasteCategoryResponseDTO> getWasteCategoryByDisposalGuideline(Long disposalGuidelineId, Long wasteCategoryId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        Optional<WasteCategory> optionalWasteCategory = disposalGuideline.getWasteCategories()
                .stream()
                .filter(wasteCategory1 -> wasteCategory1.getId().equals(wasteCategoryId)).findFirst();

        if (optionalWasteCategory.isPresent()) {
            WasteCategory wasteCategory = optionalWasteCategory.get();

            WasteCategoryResponseDTO wasteCategoryResponseDTO = new WasteCategoryResponseDTO(
                    wasteCategory.getId(),
                    wasteCategory.getWasteCategory());

            return ResponseEntity.ok(wasteCategoryResponseDTO);
        }

        throw new WasteCategoryNotFoundException();
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
