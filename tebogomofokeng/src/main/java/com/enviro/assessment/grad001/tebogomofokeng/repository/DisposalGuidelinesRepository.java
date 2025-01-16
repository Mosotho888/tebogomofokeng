package com.enviro.assessment.grad001.tebogomofokeng.repository;

import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisposalGuidelinesRepository extends JpaRepository<DisposalGuidelines, Long> {
    Boolean existsByDisposalGuideline(String disposalGuideline);
}
