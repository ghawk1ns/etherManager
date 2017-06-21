package com.ghawk1ns.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghawk1ns.server.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RigStats {

    @JsonIgnore
    public long rigUptime;

    @JsonProperty("uptime")
    public void setRigUptime(String raw) {
        rigUptime = Util.stol(raw);
    }

    @JsonIgnore
    public long minerUptime;

    @JsonProperty("miner_secs")
    public void setMinerUptime(String raw) {
        minerUptime = Util.stol(raw);
    }

    @JsonIgnore
    public int numOverHeated;

    @JsonProperty("overheat")
    public void setNumOverHeated(String raw) {
        numOverHeated = Util.stoi(raw);
    }

    @JsonProperty("throttled")
    public String throttled;

    @JsonProperty("core")
    public String cores;

    @JsonProperty("pool_info")
    public String poolInfo;

    @JsonProperty("updating")
    public String updating; // ex) "0" -> Not sure what this means

    @JsonProperty("status")
    public String status;

    @JsonIgnore
    public double hash;

    @JsonProperty("hash")
    public void setHash(String raw) {
        hash = Util.stod(raw);
    }

    @JsonProperty("overheatedgpu")
    public String overheatedGPU;

    @JsonIgnore
    public int gpus; // number of active gpus

    @JsonProperty("gpus")
    public void setGPUs(String raw) {
        gpus = Util.stoi(raw);
    }

    @JsonProperty("defunct")
    public String defunct;

    @JsonProperty("off")
    public String off;

    @JsonIgnore
    public List<Double> temps;

    @JsonProperty("temp")
    public void setTemps(String raw) {
        String[] parts = raw.split(" ");
        temps = Arrays.stream(parts)
                .map(Util::stod)
                .collect(Collectors.toList());
    }

    @JsonProperty("cpu_temp")
    public int cpuTemp;

    @JsonProperty("cpu_temp")
    public void setCPUTemp(String raw) {
        cpuTemp = Util.stoi(raw);
    }

    @JsonIgnore
    public int autorebooted;

    @JsonProperty("autorebooted")
    public void setAutorebooted(String raw) {
        autorebooted = Util.stoi(raw);
    }

    @JsonProperty("fanpercent")
    public String fanPercent;

    @JsonProperty("miner_hashes")
    public String minerHashes;

    @JsonProperty("mem")
    public String mem;
}