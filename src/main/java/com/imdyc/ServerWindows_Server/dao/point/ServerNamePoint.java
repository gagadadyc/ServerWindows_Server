package com.imdyc.ServerWindows_Server.dao.point;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * Created by 邓远超 on 2018/5/20.
 * 服务器名pojo
 */
@Measurement(name = "system")
public class ServerNamePoint {
    @Column(name = "value")
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
