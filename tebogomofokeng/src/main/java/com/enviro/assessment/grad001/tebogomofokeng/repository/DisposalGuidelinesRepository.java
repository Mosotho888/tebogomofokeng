package com.enviro.assessment.grad001.tebogomofokeng.repository;

import com.enviro.assessment.grad001.tebogomofokeng.model.DisposalGuidelines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisposalGuidelinesRepository extends JpaRepository<DisposalGuidelines, Long> {
    //DisposalGuidelines findByDisposalGuideline(String disposalGuideline);
    Boolean existsByDisposalGuideline(String disposalGuideline);
}
