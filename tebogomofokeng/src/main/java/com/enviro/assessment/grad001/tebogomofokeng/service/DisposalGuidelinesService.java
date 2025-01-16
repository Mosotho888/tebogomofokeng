package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineAlreadyExists;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.DisposalGuidelineNotFoundException;
import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import com.enviro.assessment.grad001.tebogomofokeng.repository.DisposalGuidelinesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<DisposalGuidelines>> getAllDisposalGuidelines(Pageable pageable) {
        Page<DisposalGuidelines> allDisposalGuidelines = disposalGuidelinesRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        return ResponseEntity.ok(allDisposalGuidelines.getContent());
    }

    public ResponseEntity<DisposalGuidelines> getDisposalGuidelinesById(Long disposalGuidelineId) {
        DisposalGuidelines disposalGuideline = getDisposalGuideline(disposalGuidelineId);

        return ResponseEntity.ok(disposalGuideline);
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
