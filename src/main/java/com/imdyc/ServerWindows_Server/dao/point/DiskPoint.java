package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by 邓远超 on 2018/5/24.
 */
@Measurement(name = "diskio")
public class DiskPoint {
    @Column(name = "time")
    private Instant time;

    @Column(name = "mean_read_bytes")
    private Double read_bytes;

    @Column(name = "mean_write_bytes")
    private Double write_bytes;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Double getRead_bytes() {
        return read_bytes;
    }

    public void setRead_bytes(Double read_bytes) {
        this.read_bytes = read_bytes;
    }

    public Double getWrite_bytes() {
        return write_bytes;
    }

    public void setWrite_bytes(Double write_bytes) {
        this.write_bytes = write_bytes;
    }

    @Override
    public String toString() {
        return "DiskPoint{" +
                "time=" + time +
                ", read_bytes=" + read_bytes +
                ", write_bytes=" + write_bytes +
                '}';
    }
}
