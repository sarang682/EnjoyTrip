package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Bookmark;
import com.ssafy.enjoytrip.domain.Member;
import lombok.Data;

@Data
public class PostBookmarkRequest {

    private int attractionId;

    public Bookmark toEntity(Member member, AttractionInfo info) {
        return Bookmark.builder()
                .member(member)
                .attractionInfo(info)
                .build();
    }
}
