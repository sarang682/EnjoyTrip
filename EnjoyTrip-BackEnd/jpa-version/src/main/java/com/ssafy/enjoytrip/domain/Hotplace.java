package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotplace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotplace_id")
    private int id;

    @Column
    private String title;

    @Column(length = 2000)
    private String content;

    @Column
    private String image;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_type_id")
    private AttractionType attractionType;

    @Builder
    public Hotplace(String title, String content, String image, Double latitude, Double longitude, Member member, AttractionType attractionType) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.member = member;
        this.attractionType = attractionType;
    }
}
