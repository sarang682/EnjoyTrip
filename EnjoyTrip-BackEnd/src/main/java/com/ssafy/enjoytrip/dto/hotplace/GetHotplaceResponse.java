package com.ssafy.enjoytrip.dto.hotplace;

import com.ssafy.enjoytrip.domain.Hotplace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetHotplaceResponse {

    int hotplaceId;
    String title;
    String content;
    String image;
    Double latitude;
    Double longitude;
    String memberId;
    String memberName;
    String attractionTypeName;
    String createdAt;

    public GetHotplaceResponse(Hotplace hotplace) {
        hotplaceId = hotplace.getId();
        title = hotplace.getTitle();
        content = hotplace.getContent();
        image = hotplace.getImage();
        latitude = hotplace.getLatitude();
        longitude = hotplace.getLongitude();
        memberId = hotplace.getMember().getId();
        memberName = hotplace.getMember().getName();
        attractionTypeName = hotplace.getAttractionType().getName();
        createdAt = hotplace.getCreatedAt();
    }

}
