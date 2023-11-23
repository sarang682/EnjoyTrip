package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Plan extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column
    private String title;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanAttractionInfo> attractionInfo = new ArrayList<>();

    @Builder
    public Plan(String title, Member member) {
        this.title=title;
        this.member=member;
    }
}
