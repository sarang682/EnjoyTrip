package com.ssafy.enjoytrip.dto.hotplace;

import com.ssafy.enjoytrip.domain.AttractionType;
import com.ssafy.enjoytrip.domain.Hotplace;
import com.ssafy.enjoytrip.domain.Member;
import lombok.Data;

@Data
public class PostHotplaceRequest {

    private String title;
    private String content;
    private Double latitude;
    private Double longitude;
    private int attractionTypeId;

    public Hotplace toEntity(Member member, AttractionType attractionType, String saveFileName) {
        return Hotplace.builder()
                .title(title)
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .image(saveFileName)
                .member(member)
                .attractionType(attractionType)
                .build();
    }
}
