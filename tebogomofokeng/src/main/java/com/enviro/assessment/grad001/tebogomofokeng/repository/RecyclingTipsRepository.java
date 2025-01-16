package com.enviro.assessment.grad001.tebogomofokeng.repository;

import com.enviro.assessment.grad001.tebogomofokeng.model.RecyclingTips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingTipsRepository extends JpaRepository<RecyclingTips, Long> {
    Boolean existsByRecyclingTip(String recyclingTip);
}
