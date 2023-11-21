package com.ssafy.enjoytrip.dto.bookmark;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBookmarkResponse {

    String memberId;
    List<BookmarkResponse> bookmarkList;

    public GetBookmarkResponse(String memberId, List<BookmarkResponse> bookmarkList) {
        this.memberId = memberId;
        this.bookmarkList = bookmarkList;
    }

}
