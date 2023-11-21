package com.ssafy.enjoytrip.dto.bookmark;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkDto {

    int bookmarkId;
    int attractionId;

    public BookmarkDto(com.ssafy.enjoytrip.domain.Bookmark bookmark) {
        bookmarkId = bookmark.getId();
        attractionId = bookmark.getAttractionInfo().getId();
    }
}
