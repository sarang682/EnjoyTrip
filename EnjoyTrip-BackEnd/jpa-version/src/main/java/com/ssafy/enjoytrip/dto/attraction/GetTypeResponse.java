package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.AttractionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetTypeResponse {

    private int contentTypeId;
    private String contentTypeName;

    public GetTypeResponse(AttractionType attractionType) {
        contentTypeId = attractionType.getId();
        contentTypeName = attractionType.getName();
    }

}
