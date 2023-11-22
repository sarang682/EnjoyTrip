package com.ssafy.enjoytrip.dto.attraction;

import com.ssafy.enjoytrip.domain.Sido;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetSidoResponse {

    private int sidoCode;
    private String sidoName;

    public GetSidoResponse(Sido sido) {
        sidoCode = sido.getCode();
        sidoName = sido.getName();
    }
}
