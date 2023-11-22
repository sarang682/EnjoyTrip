package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "plan_attractionInfo")
@Entity
public class PlanAttractionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_attraction_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attraction_id")
    private AttractionInfo attractionInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plan_id")
    private Plan plan;

    @Builder
    public PlanAttractionInfo(Plan plan, AttractionInfo attractionInfo) {
        this.plan=plan;
        this.attractionInfo=attractionInfo;
    }
}
