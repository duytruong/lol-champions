package com.duyrau.lolchampions;

public class Champion {

    private String name;
    private Integer imageId;

    public String getName() {
        return name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public Champion(String name, Integer imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
