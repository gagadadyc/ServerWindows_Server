package com.imdyc.ServerWindows_Server.beans;

import java.util.List;

/**
 * Created by 邓远超 on 2018/5/11.
 */
public class Server {

    private String serverName;
    private List<Memory> memory;
    private List<CPU> CPU;
    private List<Disk> disks;
    private List<Process> processes;
    private String runtime;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    public List<Memory> getMemory() {
        return memory;
    }

    public void setMemory(List<Memory> memory) {
        this.memory = memory;
    }

    public List<CPU> getCPU() {
        return CPU;
    }

    public void setCPU(List<CPU> CPU) {
        this.CPU = CPU;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void setDisks(List<Disk> disks) {
        this.disks = disks;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverName='" + serverName + '\'' +
                ", memory=" + memory +
                ", CPU=" + CPU +
                ", disks=" + disks +
                ", runtime='" + runtime + '\'' +
                '}';
    }
}
