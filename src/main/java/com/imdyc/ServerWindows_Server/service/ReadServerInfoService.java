package com.imdyc.ServerWindows_Server.service;

import com.imdyc.ServerWindows_Server.beans.*;
import com.imdyc.ServerWindows_Server.beans.Process;
import com.imdyc.ServerWindows_Server.dao.ConnectionDB;
import com.imdyc.ServerWindows_Server.dao.point.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 邓远超 on 2018/5/12.
 */
public class ReadServerInfoService {

    ConnectionDB cdb = new ConnectionDB();
    Server server = new Server();

    /**
     * 根据服务器名获取内存数据
     * @param serverName 服务器名，来自客户端的post参数
     * @return
     */
    public List<Memory> ReadServerInfo(String serverName) {

        //将MemoryPoint中的Instant转成Date，放入beans.Memory中
        List<Memory> memlist = new ArrayList<>();

        Iterator iterator = cdb.selectMemory(serverName).iterator();
        while (iterator.hasNext()) {
            MemoryPoint mp = (MemoryPoint) iterator.next();
            long l = mp.getTime().toEpochMilli();
            Memory memory = new Memory();
            memory.setTime(new Date(l));
            memory.setUsed_percent(mp.getUsed_percent());
            memlist.add(memory);
        }
        return memlist;
    }

    public List<CPU> ReadCPUInfo(String serverName) {
        List<CPU> CPUList = new ArrayList<>();
        Iterator iterator = cdb.selectCPU(serverName).iterator();
        while (iterator.hasNext()){
            CpuPoint cpuPoint = (CpuPoint) iterator.next();
            long l = cpuPoint.getTime().toEpochMilli();
            CPU cpu = new CPU();
            cpu.setTime(new Date(l));
            cpu.setUsage_idle(cpuPoint.getUsage_idle());
            CPUList.add(cpu);
        }
        return CPUList;

    }

    /**
     *
     * @param serverName
     * @return 存有读磁盘字节数和写磁盘字节数的List<disk>
     */
    public List<Disk> ReadDiskInfo(String serverName) {
        List<Disk> diskList = new ArrayList<>();
        List<DiskPoint> diskPointList = cdb.selectDisk(serverName);
        System.out.println(cdb.selectDisk(serverName));
        System.out.println(diskPointList.size()+"*******************");

        for(int i=3; i<diskPointList.size(); i++){
            DiskPoint diskPoint = diskPointList.get(i);
            //b转换成kb减去一分钟之前的数据，再除以60，即每秒读取kb数
            double r = ((diskPointList.get(i).getRead_bytes()/1024)-(diskPointList.get(i-2).getRead_bytes()/1024))/60;
            double w = ((diskPointList.get(i).getWrite_bytes()/1024)-(diskPointList.get(i-2).getWrite_bytes()/1024))/60;
            long l = diskPoint.getTime().toEpochMilli();

            Disk disk = new Disk();
            disk.setTime(new Date(l));
            disk.setRead_speed((float)r);
            disk.setWrite_speed((float)w);
            diskList.add(disk);
        }
        return diskList;
    }

    public List<Process> ReadProcessInfo(String serverName) {
        List<Process> processList = new ArrayList<>();
        List<ProcessPoint> processPointList = cdb.selectProcess(serverName);
        for(int i=2; i<processPointList.size(); i++){
            ProcessPoint processPoint = processPointList.get(i);

            Process process = new Process();
            process.setTime(new Date(processPoint.getTime().toEpochMilli())); //Instant转long，再转成date
            process.setSleeping((int) Math.ceil(processPoint.getSleeping()));//double进一取证并转int
            process.setTotal((int) Math.ceil(processPoint.getTotal()));
            process.setZombies((int) Math.ceil(processPoint.getZombies()));
            processList.add(process);
        }
        return processList;
    }


    /**
     * 返回服务器列表，每个单位是一个sysPoint对象
     * @return
     */
    public ArrayList<SysPoint> ReadSystemInfo() {
        List<ServerNamePoint> snList = cdb.selectServer();
        ArrayList<SysPoint> sysPointList = new ArrayList<>();

        //取出snList所有服务器名，将服务器命作为key、SysPoint对象作为Value
        Iterator iterator = snList.iterator();
        while (iterator.hasNext()) {
            ServerNamePoint snp = (ServerNamePoint) iterator.next();
            String serverName = snp.getHost();

            List<SysPoint> sysList;
            SysPoint sp;

            sysList = cdb.selectSystem(serverName);
            if (sysList != null) {
                sp = sysList.get(0);
            } else {
                System.out.println("sysList无数据");
                break;
            }

            sysPointList.add(sp);
        }
        return sysPointList;
    }

}
