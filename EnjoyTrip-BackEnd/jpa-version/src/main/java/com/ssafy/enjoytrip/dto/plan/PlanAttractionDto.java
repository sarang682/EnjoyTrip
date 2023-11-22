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

    public static PlanAttractionDto fromEntity (PlanAttractionInfo entity) {
        return new PlanAttractionDto(
                PlanDto.fromEntity(entity.getPlan()),
                new GetInfoResponse(entity.getAttractionInfo())
        );
    }
}
