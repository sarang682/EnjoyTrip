package com.ssafy.enjoytrip.repository.bookmark;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Bookmark;
import com.ssafy.enjoytrip.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByMember(Member member);
    boolean existsByMemberAndAttractionInfo(Member member, AttractionInfo attractionInfo);
    Optional<Bookmark> findById(int bookmarkId);
    void deleteById(int bookmarkId);
}
