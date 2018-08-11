package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by 邓远超 on 2018/5/25.
 */
@Measurement(name = "processes")
public class ProcessPoint {
    @Column(name = "time")
    private Instant time;

    @Column(name = "mean_total")
    private Double total; //总线程数量

    @Column(name = "mean_sleeping")
    private Double sleeping; //休眠中进程数量

    @Column(name = "mean_zombies")
    private Double zombies; //僵尸线程数量


    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSleeping() {
        return sleeping;
    }

    public void setSleeping(Double sleeping) {
        this.sleeping = sleeping;
    }

    public Double getZombies() {
        return zombies;
    }

    public void setZombies(Double zombies) {
        this.zombies = zombies;
    }
}
