package com.ssafy.enjoytrip.repository.attraction;

import com.ssafy.enjoytrip.domain.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<AttractionType, Long> {
    List<AttractionType> findAll();
}