package com.ssafy.enjoytrip.repository.attraction;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InfoRepository extends JpaRepository<AttractionInfo, Long> {
    List<AttractionInfo> findAll();

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode")
    List<AttractionInfo> findAllBySido(@Param("sidoCode") int sidoCode);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode " +
            "and i.gugun.gugunId.gugunCode = :gugunCode")
    List<AttractionInfo> findAllBySidoAndGugun(@Param("sidoCode") int sidoCode, @Param("gugunCode") int gugunCode);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.attractionType.id = :attractionTypeId")
    List<AttractionInfo> findAllByType(@Param("attractionTypeId") int attractionTypeId);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode " +
            "and i.attractionType.id = :attractionTypeId")
    List<AttractionInfo> findAllBySidoAndType(@Param("sidoCode") int sidoCode, @Param("attractionTypeId") int attractionTypeId);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode " +
            "and i.gugun.gugunId.gugunCode = :gugunCode " +
            "and i.attractionType.id = :attractionTypeId")
    List<AttractionInfo> findAllBySidoAndGugunAndType(
            @Param("sidoCode") int sidoCode,
            @Param("gugunCode") int gugunCode,
            @Param("attractionTypeId") int attractionTypeId);
}
