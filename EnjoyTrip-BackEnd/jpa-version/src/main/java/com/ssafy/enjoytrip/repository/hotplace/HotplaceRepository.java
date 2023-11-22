package com.ssafy.enjoytrip.repository.hotplace;

import com.ssafy.enjoytrip.domain.Hotplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotplaceRepository extends JpaRepository<Hotplace, Long> {

    List<Hotplace> findAll();

}
