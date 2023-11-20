package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.AttractionDescription;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetDescriptionResponse {

    private int contentId;
    private String homepage;
    private String overview;
    private String telname;

    public GetDescriptionResponse(AttractionDescription description) {
        contentId = description.getId();
        homepage = description.getHomepage();
        overview = description.getOverview();
        telname = description.getTelname();
    }
}