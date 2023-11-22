package com.ssafy.enjoytrip.dto.hotplace;

import com.ssafy.enjoytrip.domain.Hotplace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotplaceDto {

    private int hotplaceId;
    private String title;
    private String image;
    private String memberName;

    public HotplaceDto(Hotplace hotplace) {
        hotplaceId = hotplace.getId();
        title = hotplace.getTitle();
        image = hotplace.getImage();
        memberName = hotplace.getMember().getName();
    }
}
