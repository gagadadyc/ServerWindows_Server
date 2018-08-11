package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by 邓远超 on 2018/5/20.
 * 系统表pojo
 */
@Measurement(name = "system")
public class SysPoint {
    @Column(name = "n_cpus")
    private String n_cpus;

    @Column(name = "n_users")
    private String n_users;

    @Column(name = "uptime_format")
    private String uptime_format;

    @Column(name = "host")
    private String host;

    public String getN_cpus() {
        return n_cpus;
    }

    public void setN_cpus(String n_cpus) {
        this.n_cpus = n_cpus;
    }

    public String getN_users() {
        return n_users;
    }

    public void setN_users(String n_users) {
        this.n_users = n_users;
    }

    public String getUptime_format() {
        return uptime_format;
    }

    public void setUptime_format(String uptime_format) {
        this.uptime_format = uptime_format;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "SysPoint{" +
                "n_cpus='" + n_cpus + '\'' +
                ", n_users='" + n_users + '\'' +
                ", uptime_format='" + uptime_format + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
