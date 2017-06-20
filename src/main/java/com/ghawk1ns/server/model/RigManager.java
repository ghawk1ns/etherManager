package com.ghawk1ns.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by ghawkins on 6/17/17.
 */
public class RigManager {

    @JsonProperty("rigs")
    Map<String, Rig> rigs;

    @JsonProperty("total_hash")
    public double totalHash;

    @JsonProperty("alive_gpus")
    public int aliveGPUs;

    @JsonProperty("total_gpus")
    public int totalGPUs;

    @JsonProperty("alive_rigs")
    public int aliveRigs;

    @JsonProperty("total_rigs")
    public int totalRigs;

    @JsonProperty("current_version")
    public String currentVer;

    @JsonProperty("avg_temp")
    public int avgTemp;

    @JsonProperty("capacity")
    public String capacity;

    // TODO per_info
    //  "per_info": {
    //      "claymore": {
    //          "hash": 149,
    //          "per_alive_gpus": 5,
    //          "per_total_gpus": 5,
    //          "per_alive_rigs": 1,
    //          "per_total_rigs": 1,
    //          "per_hash-gpu": "29.8",
    //          "per_hash-rig": "149.0"
    //      }
    //  {
}
