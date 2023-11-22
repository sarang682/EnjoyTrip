package com.ssafy.enjoytrip.repository.attraction;

import com.ssafy.enjoytrip.domain.AttractionDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<AttractionDescription, Long> {
    Optional<AttractionDescription> findById(int id);
}