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
public class Gugun {

    @MapsId("sidoCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code", referencedColumnName = "sido_code")
    private Sido sido;

    @EmbeddedId
    private GugunId gugunId;

    @Column(name = "gugun_name")
    private String name;

    @OneToMany(mappedBy = "gugun")
    private List<AttractionInfo> attractionInfos = new ArrayList<>();

}
