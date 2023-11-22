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
@Table(name = "attraction_type")
public class AttractionType {

    @Id
    @Column(name = "attraction_type_id")
    private int id;

    @Column(name = "attraction_type_name")
    private String name;

    @OneToMany(mappedBy = "attractionType")
    private List<AttractionInfo> attractionInfos = new ArrayList<>();

    @OneToMany(mappedBy = "attractionType")
    private List<Hotplace> hotplaces = new ArrayList<>();

}
