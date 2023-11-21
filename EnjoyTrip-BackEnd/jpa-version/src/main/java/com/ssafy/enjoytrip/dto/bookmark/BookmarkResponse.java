package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.Bookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkResponse {

    int bookmarkId;
    int attractionId;

    public BookmarkResponse(Bookmark bookmark) {
        bookmarkId = bookmark.getId();
        attractionId = bookmark.getAttractionInfo().getId();
    }
}
