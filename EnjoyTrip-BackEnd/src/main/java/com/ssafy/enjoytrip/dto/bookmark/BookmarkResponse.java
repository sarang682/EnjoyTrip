package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.Bookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkResponse {
    int bookmarkId;
    String memberId;
    int attractionId;
    String action;

    public BookmarkResponse(Bookmark bookmark, String action) {
        bookmarkId = bookmark.getId();
        memberId = bookmark.getMember().getId();
        attractionId = bookmark.getAttractionInfo().getId();
        this.action = action;
    }

}
