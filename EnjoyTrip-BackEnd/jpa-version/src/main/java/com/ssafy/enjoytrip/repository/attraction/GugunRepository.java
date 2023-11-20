package com.ssafy.enjoytrip.repository.attraction;

import com.ssafy.enjoytrip.domain.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Long> {
    List<Gugun> findAllBySidoCode(int sidoCode);
}