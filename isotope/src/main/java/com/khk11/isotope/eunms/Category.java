package com.khk11.isotope.eunms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    //이넘은 상수제약 걸때 많이 사용합니다.
    PAINT("paint","페인트"),
    PHOTO("photo","포토"),
    SKETCH("sketch","스케치");

    private final String desc;
    private final String kor;
   /* Category(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }*/
}
