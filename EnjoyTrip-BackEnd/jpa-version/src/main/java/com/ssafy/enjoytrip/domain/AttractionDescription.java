package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attraction_description")
public class AttractionDescription {

    @Id
    @Column(name = "attraction_id")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "attraction_id", referencedColumnName = "attraction_id")
    private AttractionInfo attractionInfo;

    @Column
    private String homepage;

    @Column(length = 10000)
    private String overview;

    @Column
    private String telname;

}
