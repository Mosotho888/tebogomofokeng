package com.enviro.assessment.grad001.tebogomofokeng.service;

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
        DisposalGuidelines saveDisposalGuidelines = new DisposalGuidelines();

        saveDisposalGuidelines.setId(null);
        saveDisposalGuidelines.setDisposal_guidelines(newDisposalGuidelineRequest.getDisposal_guidelines());

        disposalGuidelinesRepository.save(saveDisposalGuidelines);

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
        Optional<DisposalGuidelines> optionalDisposalGuidelines = disposalGuidelinesRepository.findById(disposalGuidelineId);

        if (optionalDisposalGuidelines.isPresent()) {
            DisposalGuidelines disposalGuidelines = optionalDisposalGuidelines.get();

            return ResponseEntity.ok(disposalGuidelines);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> updateDisposalGuidelines(Long disposalGuidelineId, DisposalGuidelines updatedDisposalGuideline) {
        Optional<DisposalGuidelines> optionalDisposalGuidelines = disposalGuidelinesRepository.findById(disposalGuidelineId);

        if (optionalDisposalGuidelines.isPresent()) {
            DisposalGuidelines disposalGuidelines = optionalDisposalGuidelines.get();
            disposalGuidelines.setDisposal_guidelines(updatedDisposalGuideline.getDisposal_guidelines());
            disposalGuidelinesRepository.save(disposalGuidelines);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteDisposalGuideline(Long disposalGuidelineId) {
        Optional<DisposalGuidelines> optionalDisposalGuidelines = disposalGuidelinesRepository.findById(disposalGuidelineId);

        if (optionalDisposalGuidelines.isPresent()) {
            DisposalGuidelines disposalGuidelines = optionalDisposalGuidelines.get();
            disposalGuidelinesRepository.delete(disposalGuidelines);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
