package com.ssafy.enjoytrip.repository.plan;

import com.ssafy.enjoytrip.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    @Override
    Optional<Plan> findById(Integer id);
}
