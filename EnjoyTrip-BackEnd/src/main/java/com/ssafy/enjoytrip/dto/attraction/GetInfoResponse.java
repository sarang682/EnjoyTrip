package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.AttractionInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetInfoResponse {

    private int attractionId;
    private int attractionTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readcount;
    private int sidoCode;
    private int gugunCode;
    private Double latitude;
    private Double longitude;
    private String mlevel;
    private boolean isLogined;
    private boolean isBookmarked;

    public GetInfoResponse(AttractionInfo info, boolean isLogined, boolean isBookmarked) {
        attractionId = info.getId();
        attractionTypeId = info.getAttractionType().getId();
        title = info.getTitle();
        addr1 = info.getAddr1();
        addr2 = info.getAddr2();
        zipcode = info.getZipcode();
        tel = info.getTel();
        firstImage = info.getFirstImage();
        firstImage2 = info.getFirstImage2();
        readcount = info.getReadcount();
        sidoCode = info.getGugun().getGugunId().getSidoCode();
        gugunCode = info.getGugun().getGugunId().getGugunCode();
        latitude = info.getLatitude();
        longitude = info.getLongitude();
        mlevel = info.getMlevel();
        this.isLogined = isLogined;
        this.isBookmarked = isBookmarked;
    }
}
