package com.imdyc.ServerWindows_Server.beans;

import java.util.Date;

/**
 * Created by 邓远超 on 2018/5/22.
 */
public class CPU {
    private Date time;
    private String usage_idle; //cpu空闲百分比

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsage_idle() {
        return usage_idle;
    }

    public void setUsage_idle(String usage_idle) {
        this.usage_idle = usage_idle;
    }
}
