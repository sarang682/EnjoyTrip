package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.Gugun;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetGugunResponse {

    private int sidoCode;
    private int gugunCode;
    private String gugunName;

    public GetGugunResponse(Gugun gugun) {
        sidoCode = gugun.getGugunId().getSidoCode();
        gugunCode = gugun.getGugunId().getGugunCode();
        gugunName = gugun.getName();
    }
}
