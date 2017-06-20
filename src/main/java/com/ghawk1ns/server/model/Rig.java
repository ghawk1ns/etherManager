package com.ghawk1ns.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rig {

    @JsonProperty("condition")
    //"mining"
    public String condition;

    @JsonProperty("version")
    //"1.2.2"
    public String version;

    @JsonProperty("miner")
    //"claymore"
    public String miner;

    @JsonProperty("gpus")
    //"5"
    public String gpus;

    @JsonProperty("miner_instance")
    //5
    public String minerInstance;

    @JsonProperty("miner_hashes")
    //"29.73 29.87 29.88 29.41 30.10"
    public String minerHashes;

    @JsonProperty("bioses")
    //"113-P20-XTX-C1366M8G-W82 113-P20-XTX-C1366M8G-W82 113-P20-XTX-C1366M8G-W82 113-P20-XTX-C1366M8G-W82 xxx-xxx-xxx"
    public String bioses;

    @JsonProperty("meminfo")
    //"GPU0:01.00.0:Radeon RX 580:113-P20-XTX-C1366M8G-W82:Unknown Hynix\nGPU1:02.00.0:Radeon RX 580:113-P20-XTX-C1366M8G-W82:Unknown Hynix\nGPU2:05.00.0:Radeon RX 580:113-P20-XTX-C1366M8G-W82:Unknown Hynix\nGPU3:06.00.0:Radeon RX 580:113-P20-XTX-C1366M8G-W82:Unknown Hynix\nGPU4:08.00.0:Radeon RX 580:xxx-xxx-xxx:Samsung K4G80325FB"
    public String memInfo;

    @JsonProperty("vramsize")
    //"8 8 8 8 8"
    public String vRamSize;

    @JsonProperty("drive_name")
    //"KINGSTON SUV400S37120G 50026B777200022D"
    public String driveName;

    @JsonProperty("mobo")
    //"Z97 Extreme4"
    public String mobo;

    @JsonProperty("lan_chip")
    //"Intel Corporation Ethernet Connection (2) I218-V"
    public String lanChip;

    @JsonProperty("connected_displays")
    //""
    public String connectedDisplays;

    @JsonProperty("ram")
    //"7"
    public String ram;

    @JsonProperty("rack_loc")
    //false
    public String rackLock;

    @JsonProperty("ip")
    //"192.168.1.149"
    public String ip;

    @JsonProperty("driver")
    //"amdgpu"
    public String driver;

    @JsonProperty("server_time")
    //1497685804
    public long serverTime;

    @JsonProperty("uptime")
    //"5335"
    public String uptime;

    @JsonProperty("miner_secs")
    public long minerSecs;

    @JsonProperty("rx_kbps")
    //"0.10"
    public String rxKbps;

    @JsonProperty("tx_kbps")
    //"0.06"
    public String txKbps;

    @JsonProperty("load")
    //"0.32"
    public String load;

    @JsonProperty("cpu_temp")
    //"42"
    public String cpuTemp;

    @JsonProperty("freespace")
    //4.2
    public String freeSpace;

    @JsonProperty("hash")
    //148.99
    public double hash;

    @JsonProperty("pool")
    public String pool;

    @JsonProperty("temp")
    //"67.00 63.00 74.00 67.00 54.00"
    public String temp;

    @JsonProperty("powertune")
    //"5 5 5 5 4"
    public String powerTune;

    @JsonProperty("fanrpm")
    //"3776 3776 3776 3776 3776"
    public String fanRPM;

    @JsonProperty("core")
    //"1257 1257 1257 1257 1200"
    public String core;

    @JsonProperty("mem")
    //"2150 2150 2150 2150 2200"
    public String mem;
}