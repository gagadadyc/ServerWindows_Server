package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by 邓远超 on 2018/5/11.
 */
@Measurement(name = "mem")
public class MemoryPoint {
    @Column(name = "time")
    private Instant time;

    @Column(name = "used_percent")
    private String used_percent;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getUsed_percent() {
        return used_percent;
    }

    public void setUsed_percent(String used_percent) {
        this.used_percent = used_percent;
    }
}
