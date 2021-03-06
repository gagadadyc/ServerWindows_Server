package com.imdyc.ServerWindows_Server.web;

import com.google.gson.Gson;
import com.imdyc.ServerWindows_Server.beans.Process;
import com.imdyc.ServerWindows_Server.beans.Server;
import com.imdyc.ServerWindows_Server.service.ReadServerInfoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by 邓远超 on 2018/5/25.
 */
@WebServlet("/Process")
public class ProcessPoint extends HttpServlet {
    private Gson gson = new Gson();
    private Server server;
    private ReadServerInfoService readServerInfoService = new ReadServerInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Process接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        //获取json格式的请求
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line ;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        Map<String,String> map = gson.fromJson(stringBuilder.toString(),Map.class);

        //向service获取Server信息
        server = new Server();
        List<Process> processList = readServerInfoService.ReadProcessInfo(map.get("serverName"));
        server.setServerName(map.get("serverName"));
        server.setProcesses(processList);

        String serverInfoJson = gson.toJson(server);
        PrintWriter out = response.getWriter();
        out.print(serverInfoJson);
        System.out.println("Process返回");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Process接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");


        //向service获取Server信息
        server = new Server();
        List<Process> processList = readServerInfoService.ReadProcessInfo("VM_70_21_centos");
        server.setServerName("VM_70_21_centos");
        server.setProcesses(processList);

        String serverInfoJson = gson.toJson(server);
        PrintWriter out = response.getWriter();
        out.print(serverInfoJson);
        System.out.println("Process返回");
    }
}