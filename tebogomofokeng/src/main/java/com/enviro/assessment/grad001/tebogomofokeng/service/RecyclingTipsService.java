package com.enviro.assessment.grad001.tebogomofokeng.service;

import com.enviro.assessment.grad001.tebogomofokeng.exceptions.RecyclingTipAlreadyExist;
import com.enviro.assessment.grad001.tebogomofokeng.exceptions.RecyclingTipsNotFoundException;
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

    public ResponseEntity<Void> createRecyclingTip(RecyclingTips newRecyclingTipsRequest) {
        Boolean doesRecyclingTipExist = recyclingTipsRepository.existsByRecyclingTip(newRecyclingTipsRequest.getRecyclingTip());

        if (doesRecyclingTipExist) {
            throw new RecyclingTipAlreadyExist();
        }
        RecyclingTips recyclingTip = new RecyclingTips();

        saveRecyclingTip(newRecyclingTipsRequest, recyclingTip);

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
        RecyclingTips recyclingTip = getRecyclingTip(recyclingTipId);

        return ResponseEntity.ok(recyclingTip);
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
