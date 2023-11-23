package com.ssafy.enjoytrip.dto.plan;

import com.ssafy.enjoytrip.domain.PlanAttractionInfo;
import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanAttractionDto {

    private PlanDto plan;
    private GetInfoResponse attraction;

    public PlanAttractionDto (PlanAttractionInfo entity, boolean isLogined, boolean isBookmarked) {
        this.plan=PlanDto.fromEntity(entity.getPlan());
        this.attraction= new GetInfoResponse(entity.getAttractionInfo(),isLogined,isBookmarked);
    }
}
