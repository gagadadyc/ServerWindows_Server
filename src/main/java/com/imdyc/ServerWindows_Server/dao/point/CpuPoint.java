package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by 邓远超 on 2018/5/22.
 */
@Measurement(name = "cpu")
public class CpuPoint {
    @Column(name = "time")
    private Instant time;

    @Column(name = "usage_idle")
    private String usage_idle;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getUsage_idle() {
        return usage_idle;
    }

    public void setUsage_idle(String usage_idle) {
        this.usage_idle = usage_idle;
    }
}
