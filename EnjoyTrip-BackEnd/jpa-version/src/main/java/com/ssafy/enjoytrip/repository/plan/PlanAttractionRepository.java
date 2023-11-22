package com.ssafy.enjoytrip.repository.plan;

import com.ssafy.enjoytrip.domain.PlanAttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanAttractionRepository extends JpaRepository<PlanAttractionInfo, Integer> {
    @Override
    Optional<PlanAttractionInfo> findById(Integer id);
}
