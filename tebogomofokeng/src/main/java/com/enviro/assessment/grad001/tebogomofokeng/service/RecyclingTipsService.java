package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
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

@Service
public class RecyclingTipsService {
    private final RecyclingTipsRepository recyclingTipsRepository;

    public RecyclingTipsService(RecyclingTipsRepository recyclingTipsRepository) {
        this.recyclingTipsRepository = recyclingTipsRepository;
    }

    public ResponseEntity<Void> createRecyclingTips(RecyclingTips newRecyclingTipsRequest) {
        RecyclingTips saveRecyclingTips = new RecyclingTips();

        saveRecyclingTips.setId(null);
        saveRecyclingTips.setRecyclingTips(newRecyclingTipsRequest.getRecyclingTips());

        recyclingTipsRepository.save(saveRecyclingTips);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<RecyclingTips>> getAllRecyclingTips(Pageable pageable) {
        Page<RecyclingTips> allRecyclingTipsData = recyclingTipsRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        return ResponseEntity.ok(allRecyclingTipsData.getContent());
    }

    public ResponseEntity<RecyclingTips> getRecyclingTipById(Long recyclingTipId) {
        Optional<RecyclingTips> optionalRecyclingTips = recyclingTipsRepository.findById(recyclingTipId);

        if (optionalRecyclingTips.isPresent()) {
            RecyclingTips recyclingTips = optionalRecyclingTips.get();

            return ResponseEntity.ok(recyclingTips);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> updateRecyclingTips(Long recyclingTipId, RecyclingTips updatedRecyclingTip) {
        Optional<RecyclingTips> optionalRecyclingTips = recyclingTipsRepository.findById(recyclingTipId);

        if (optionalRecyclingTips.isPresent()) {
            RecyclingTips recyclingTips = optionalRecyclingTips.get();
            recyclingTips.setRecyclingTips(updatedRecyclingTip.getRecyclingTips());
            recyclingTipsRepository.save(recyclingTips);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteRecyclingTipById(Long recyclingTipId) {
        Optional<RecyclingTips> optionalRecyclingTips = recyclingTipsRepository.findById(recyclingTipId);

        if (optionalRecyclingTips.isPresent()) {
            RecyclingTips recyclingTips = optionalRecyclingTips.get();
            recyclingTipsRepository.delete(recyclingTips);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
