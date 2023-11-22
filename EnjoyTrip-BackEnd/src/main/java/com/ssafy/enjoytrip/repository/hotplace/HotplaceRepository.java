package com.ssafy.enjoytrip.repository.hotplace;

import com.ssafy.enjoytrip.domain.Hotplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotplaceRepository extends JpaRepository<Hotplace, Long> {
    List<Hotplace> findAll();
    Optional<Hotplace> findById(int id);
    void deleteById(int id);
}
