package com.ssafy.enjoytrip.repository.plan;

import com.ssafy.enjoytrip.domain.Plan;
import com.ssafy.enjoytrip.domain.PlanAttractionInfo;
import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanAttractionRepository extends JpaRepository<PlanAttractionInfo, Integer> {
    @Override
    Optional<PlanAttractionInfo> findById(Integer id);

    List<PlanAttractionInfo> findAllByPlan(Plan plan);

}
