package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.Bookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostBookmarkResponse {
    int bookmarkId;
    BookmarkResponse bookmarkResponse;

    public PostBookmarkResponse(Bookmark bookmark) {
        bookmarkId = bookmark.getId();
        bookmarkResponse = new BookmarkResponse(bookmark);
    }
}
