package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.AttractionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetTypeResponse {

    private int attractionTypeId;
    private String attractionTypeName;

    public GetTypeResponse(AttractionType attractionType) {
        attractionTypeId = attractionType.getId();
        attractionTypeName = attractionType.getName();
    }

}
