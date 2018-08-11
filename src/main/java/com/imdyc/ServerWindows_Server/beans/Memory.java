package com.imdyc.ServerWindows_Server.beans;

import java.util.Date;

/**
 * Created by 邓远超 on 2018/5/15.
 */
public class Memory {
    private Date time;
    private String used_percent;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsed_percent() {
        return used_percent;
    }

    public void setUsed_percent(String used_percent) {
        this.used_percent = used_percent;
    }
}
