package com.ghawk1ns.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartBeat {

    @JsonProperty("gpu")
    public int gpu;

    @JsonProperty("hash")
    public String hash;

    @JsonProperty("temp")
    public String temp;
}