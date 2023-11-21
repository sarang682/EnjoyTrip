package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.AttractionType;
import com.ssafy.enjoytrip.domain.Bookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostBookmarkResponse {
    int bookmarkId;
    String memberId;
    int attractionId;

    public PostBookmarkResponse(Bookmark bookmark) {
        bookmarkId = bookmark.getId();
        memberId = bookmark.getMember().getId();
        attractionId = bookmark.getAttractionInfo().getId();
    }
}
