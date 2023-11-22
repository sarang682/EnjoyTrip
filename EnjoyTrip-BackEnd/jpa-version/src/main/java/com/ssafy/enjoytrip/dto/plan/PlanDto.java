package com.ssafy.enjoytrip.dto.plan;

import com.ssafy.enjoytrip.domain.Plan;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlanDto {

    private int id;
    private String title;
    private MemberDto member;
    private String createdAt;
    private String updatedAt;

    public static PlanDto fromEntity (Plan plan) {
        return new PlanDto(
                plan.getId(),
                plan.getTitle(),
                MemberDto.fromEntity(plan.getMember()),
                plan.getCreatedAt(),
                plan.getUpdatedAt()
        );
    }
}
