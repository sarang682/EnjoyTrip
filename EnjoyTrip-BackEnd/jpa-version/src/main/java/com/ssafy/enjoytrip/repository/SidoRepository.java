package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Long> {
    List<Sido> findAll();
}
