package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.Bookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkDto {

    int bookmarkId;
    int attractionId;
    String title;


    public BookmarkDto(Bookmark bookmark) {
        bookmarkId = bookmark.getId();
        attractionId = bookmark.getAttractionInfo().getId();
    }

}
