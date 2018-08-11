package com.imdyc.ServerWindows_Server.web;

import com.google.gson.Gson;
import com.imdyc.ServerWindows_Server.beans.CPU;
import com.imdyc.ServerWindows_Server.beans.Memory;
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
 * Created by 邓远超 on 2018/5/11.
 * 处理app的数据请求
 *  1.设置content-type为application/json
 *  2.直接作为数据使用输出流进行输出
 */
@WebServlet("/ResInfo")
public class ResInfoServlet extends HttpServlet{

    private Gson gson = new Gson();
    private Server server ;
    private ReadServerInfoService readServerInfoService = new ReadServerInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("ResInfo接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        //获取json格式的请求
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }

        Map<String,String> map = gson.fromJson(stringBuilder.toString(),Map.class);

        //向service获取Server信息
        server = new Server();
        List<CPU> CPUList = readServerInfoService.ReadCPUInfo(map.get("serverName"));
        server.setServerName(map.get("serverName"));
        server.setCPU(CPUList);
        List<Memory> memoryList = readServerInfoService.ReadServerInfo(map.get("serverName"));
        server.setServerName(map.get("serverName"));
        server.setMemory(memoryList);


        String serverInfoJson = gson.toJson(server);
        PrintWriter out = response.getWriter();
        out.print(serverInfoJson);
        System.out.println("ResInfo返回");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ResInfo接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        //向service获取Server信息
        server = new Server();
        List<Memory> memoryList = readServerInfoService.ReadServerInfo("VM_70_21_centos");
        server.setServerName("VM_70_21_centos");
        server.setMemory(memoryList);
        List<CPU> CPUList = readServerInfoService.ReadCPUInfo("VM_70_21_centos");
        server.setServerName("VM_70_21_centos");
        server.setCPU(CPUList);

        String serverInfoJson = gson.toJson(server);
        PrintWriter out = response.getWriter();
        out.print(serverInfoJson);
        System.out.println("ResInfo返回");
    }
}
