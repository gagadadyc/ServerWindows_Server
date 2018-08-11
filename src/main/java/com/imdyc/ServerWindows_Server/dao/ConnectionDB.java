package com.imdyc.ServerWindows_Server.dao;

import com.imdyc.ServerWindows_Server.dao.point.*;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 邓远超 on 2018/5/11.
 */
public class ConnectionDB {

    final InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086");
    final String dbName = "telegraf";

    private QueryResult queryResult;
    private InfluxDBResultMapper resultMapper;

    public List<MemoryPoint> selectMemory(String host) {
        long t = new Date().getTime();
        t = (t - 86400000) * 1000000;//查询一天之内的数据
        String sql = "SELECT time,used_percent FROM mem where time > " + t + " and host = \'" + host + "\'";
        queryResult = influxDB.query(new Query(sql, dbName));
        resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused


        List<MemoryPoint> memoryList = resultMapper.toPOJO(queryResult, MemoryPoint.class);
        List<MemoryPoint> list3 = new ArrayList<>();
        for(int i=0; i<memoryList.size(); i=i+3){
            list3.add(memoryList.get(i));
        }
        return list3;
    }

    public List<CpuPoint> selectCPU(String host){
        long t = new Date().getTime();
        t = (t - 86400000) * 1000000;
        String sql = "SELECT time,usage_idle FROM cpu where time > "+ t +" and cpu = 'cpu-total' and host = \'"+ host +"\'";
        queryResult = influxDB.query(new Query(sql,dbName));
        resultMapper = new InfluxDBResultMapper();

        List<CpuPoint> cpuList = resultMapper.toPOJO(queryResult, CpuPoint.class);
        List<CpuPoint> list3 = new ArrayList<>();
        for(int i=0; i<cpuList.size(); i=i+3){
            list3.add(cpuList.get(i));
        }
        return list3;
    }

    public List<DiskPoint> selectDisk(String host){
        long t = new Date().getTime();
        t = (t - 86400000) * 1000000;
        String sql = "SELECT time,mean(\"write_bytes\") AS \"mean_write_bytes\", mean(\"read_bytes\") AS \"mean_read_bytes\" FROM diskio WHERE time > "+ t +" AND \"host\"= \'"+ host +"\' GROUP BY time(30s)";
        queryResult = influxDB.query(new Query(sql,dbName));
        resultMapper = new InfluxDBResultMapper();

        List<DiskPoint> diskList = resultMapper.toPOJO(queryResult, DiskPoint.class);

        return diskList;
    }

    public List<ProcessPoint> selectProcess(String host){
        String sql = "SELECT mean(\"total\") AS \"mean_total\", mean(\"sleeping\") AS \"mean_sleeping\", mean(\"zombies\") AS \"mean_zombies\" FROM \"telegraf\".\"autogen\".\"processes\" WHERE time > now() - 24h GROUP BY time(10s) FILL(null)";
        queryResult = influxDB.query(new Query(sql,dbName));
        resultMapper = new InfluxDBResultMapper();

        List<ProcessPoint> processPointList = resultMapper.toPOJO(queryResult, ProcessPoint.class);
        //将得到的10秒钟一次的数据降为30秒一次
        List<ProcessPoint> list3 = new ArrayList<>();
        for(int i=0; i<processPointList.size(); i=i+3){
            list3.add(processPointList.get(i));
        }
        return list3;
    }

    public List<ServerNamePoint> selectServer() {
        String sql = "SHOW TAG VALUES FROM system WITH KEY=host;";
        queryResult = influxDB.query(new Query(sql, dbName));
        resultMapper = new InfluxDBResultMapper();
        List<ServerNamePoint> snList = resultMapper.toPOJO(queryResult, ServerNamePoint.class);

        return snList;
    }

    //根据服务器名，查system表的最后一条数据
    public List<SysPoint> selectSystem(String serverName) {

//        List<ServerNamePoint> snList = selectServer();
        List<SysPoint> sysList = new ArrayList<>();

        //根据主机名查询运行时间，占有cpu数，用户数，主机名
        String runtimeSQL = "SELECT LAST(\"uptime_format\"),n_cpus,n_users,host,uptime_format FROM system WHERE host = \'" + serverName + "\';";
        queryResult = influxDB.query(new Query(runtimeSQL, dbName));
        resultMapper = new InfluxDBResultMapper();
        sysList = resultMapper.toPOJO(queryResult, SysPoint.class);//因为只查最后一条数据，所以该list只有一行

        return sysList;
    }

}
