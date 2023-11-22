package com.ssafy.enjoytrip.dto.hotplace;

import com.ssafy.enjoytrip.domain.AttractionType;
import com.ssafy.enjoytrip.domain.Hotplace;
import com.ssafy.enjoytrip.domain.Member;
import lombok.Data;

@Data
public class PostHotplaceRequest {

    private String title;
    private String content;
    private String image;
    private Double latitude;
    private Double longitude;
    private int attractionTypeId;

    public Hotplace toEntity(Member member, AttractionType attractionType) {
        return Hotplace.builder()
                .title(title)
                .content(content)
                .image(image)
                .latitude(latitude)
                .longitude(longitude)
                .member(member)
                .attractionType(attractionType)
                .build();
    }
}
