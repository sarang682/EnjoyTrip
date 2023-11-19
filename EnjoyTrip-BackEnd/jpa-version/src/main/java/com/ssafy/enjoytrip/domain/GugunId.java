package com.ssafy.enjoytrip.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class GugunId implements Serializable {

    @Column(name = "sido_code")
    private int sidoCode;

    @Column(name = "gugun_code")
    private int gugunCode;

}
