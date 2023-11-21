package com.ssafy.enjoytrip.repository.bookmark;

import com.ssafy.enjoytrip.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
