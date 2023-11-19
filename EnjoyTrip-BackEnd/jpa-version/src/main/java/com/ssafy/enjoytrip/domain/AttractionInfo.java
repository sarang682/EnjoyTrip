package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_info")
public class AttractionInfo {

    @Id
    @Column(name = "attraction_id")
    private int id;

    @Column
    private String title;

    @Column
    private String addr1;

    @Column
    private String addr2;

    @Column
    private String zipcode;

    @Column
    private String tel;

    @Column(name = "first_image")
    private String firstImage;

    @Column(name = "first_image2")
    private String firstImage2;

    @Column
    private int readcount;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String mlevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "sido_code"),
            @JoinColumn(name = "gugun_code")
    })
    private Gugun gugun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_type_id")
    private AttractionType attractionType;

    @OneToOne(mappedBy = "attractionInfo")
    @PrimaryKeyJoinColumn
    private AttractionDescription attractionDescription;

    @OneToMany(mappedBy = "attractionInfo")
    private List<Bookmark> bookmarks = new ArrayList<>();

}
