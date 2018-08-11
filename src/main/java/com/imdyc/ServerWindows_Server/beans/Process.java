package com.imdyc.ServerWindows_Server.beans;

import java.util.Date;

/**
 * Created by 邓远超 on 2018/5/25.
 */
public class Process {
    private Date time;
    private int total; //总线程数量
    private int sleeping; //休眠中进程数量
    private int zombies; //僵尸线程数量

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSleeping() {
        return sleeping;
    }

    public void setSleeping(int sleeping) {
        this.sleeping = sleeping;
    }

    public int getZombies() {
        return zombies;
    }

    public void setZombies(int zombies) {
        this.zombies = zombies;
    }

    @Override
    public String toString() {
        return "Process{" +
                "time=" + time +
                ", total=" + total +
                ", sleeping=" + sleeping +
                ", zombies=" + zombies +
                '}';
    }
}
