package com.ssafy.enjoytrip.dto.bookmark;

import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBookmarkResponse {

    String memberId;
    List<GetInfoResponse> attractions;

    public GetBookmarkResponse(String memberId, List<GetInfoResponse> attractions) {
        this.memberId = memberId;
        this.attractions = attractions;
    }

}
