package com.imdyc.ServerWindows_Server.web;

import com.google.gson.Gson;
import com.imdyc.ServerWindows_Server.dao.point.SysPoint;
import com.imdyc.ServerWindows_Server.service.ReadServerInfoService;
import com.imdyc.ServerWindows_Server.utility.ArrayListPojo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by 邓远超 on 2018/5/20.
 * 反馈服务器列表、IP等信息
 */
@WebServlet("/ServerList")
public class ServerListServlet extends HttpServlet {

    private Gson gson = new Gson();
    private ReadServerInfoService readServerInfoService = new ReadServerInfoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ServerList接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        ArrayList<SysPoint> sysPointList = readServerInfoService.ReadSystemInfo();

        ArrayListPojo listPojo = new ArrayListPojo();
        listPojo.setList(sysPointList);
        //将List包装后再进行序列化
        String serverInfoJson = gson.toJson(listPojo);

        PrintWriter out = response.getWriter();
        out.write(serverInfoJson);
        out.flush();
        System.out.println("ServerList返回");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int test;
        System.out.println("ServerList接收");
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        ArrayList<SysPoint> sysPointList = readServerInfoService.ReadSystemInfo();


        ArrayListPojo listPojo = new ArrayListPojo();
        listPojo.setList(sysPointList);
        //将List包装后再进行序列化
        String serverInfoJson = gson.toJson(listPojo);


        PrintWriter out = response.getWriter();
        out.write(serverInfoJson);
        out.flush();
        System.out.println("ServerList返回");
    }
}
