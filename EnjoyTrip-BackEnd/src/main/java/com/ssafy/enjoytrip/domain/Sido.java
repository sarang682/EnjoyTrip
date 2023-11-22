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
public class Sido {

    @Id
    @Column(name = "sido_code")
    private int code;

    @Column(name = "sido_name")
    private String name;

    @OneToMany(mappedBy = "sido")
    private List<Gugun> guguns = new ArrayList<>();

}
