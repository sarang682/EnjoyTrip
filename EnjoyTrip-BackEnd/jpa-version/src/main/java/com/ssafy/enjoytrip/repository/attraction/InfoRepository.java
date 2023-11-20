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
            "where i.attractionType.id = :contentTypeId")
    List<AttractionInfo> findAllByType(@Param("contentTypeId") int contentTypeId);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode " +
            "and i.attractionType.id = :contentTypeId")
    List<AttractionInfo> findAllBySidoAndType(@Param("sidoCode") int sidoCode, @Param("contentTypeId") int contentTypeId);

    @Query("select i " +
            "from AttractionInfo i " +
            "where i.gugun.gugunId.sidoCode = :sidoCode " +
            "and i.gugun.gugunId.gugunCode = :gugunCode " +
            "and i.attractionType.id = :contentTypeId")
    List<AttractionInfo> findAllBySidoAndGugunAndType(
            @Param("sidoCode") int sidoCode,
            @Param("gugunCode") int gugunCode,
            @Param("contentTypeId") int contentTypeId);
}
