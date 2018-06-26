<%@page import="com.jc.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>会议列表</title>
<link rel="stylesheet" type="text/css" href="Assets/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="Assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="Assets/css/thems.css">
<script type="text/javascript" src="Assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	//自适应屏幕宽度
	window.onresize=function(){ location=location }; 
	
	var main_h = $(window).height();
	$('.hy_list').css('height',main_h-45+'px');
	
	var search_w = $(window).width()-40;
	$('.search').css('width',search_w+'px');
	//$('.list_hy').css('width',search_w+'px');
});
</script>
<!--框架高度设置-->
</head>
<%
	List<User> list = (List<User>)request.getAttribute("list");
 %>
<body onLoad="Resize();">
<div id="right_ctn">
	<div class="right_m">
		<!--会议列表-->
        <div class="hy_list">
        	<div class="box_t">
            	<span class="name">会议列表</span>
                <!--当前位置-->
                <div class="position">
                	<a href=""><img src="Assets/images/icon5.png" alt=""/></a>
                    <a href="">首页</a>
                    <span><img src="Assets/images/icon3.png" alt=""/></span>
                    <a href="">会议管理</a>
                    <span><img src="Assets/images/icon3.png" alt=""/></span>
                    <a href="">会议列表</a>
                </div>
                <!--当前位置-->
            </div>
            <!--查询-->
            <div class="search">
            	<span>按会议名称查询：</span>
                <div class="s_text"><input name="" type="text"></div>
                <a href="" class="btn">查询</a>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <form action="" method="post">
            <table cellpadding="0" cellspacing="0" class="list_hy">
              <tr>
                <th class="xz" scope="col">选择</th>
                <th scope="col">ID</th>
                <th scope="col">用户名</th>
                <th scope="col">密码</th>
                <th scope="col">性别</th>
                <th scope="col">操作</th>
              </tr>
              <% 
              	for(User user : list){
              	%>
              	<tr>
	                <td class="xz"><input name="" type="checkbox" value=""></td>
	                <td><%=user.getId() %></td>
	                <td><%=user.getUsername() %></td>
	                <td><%=user.getPassword() %></td>
	                <td><%=user.getSex() %></td>
	                <td>
	                	<a href="#">删除</a>
	                	<a href="#">修改</a>
	                </td>
              	</tr>
             
              	<%
              	}
              %>
              
            </table>
            <!--列表-->
            <!--右边底部-->
            <div class="r_foot">
            	<div class="r_foot_m">
            	<span>
                	<input name="" type="checkbox" value="">
                    <em>全部选中</em>
                </span>
                <a href="" class="btn">删除</a>
                <a href="" class="btn">刷新</a>
                
                <!--分页-->
                <div class="page">
                	<a href="" class="prev"><img src="Assets/images/icon7.png" alt=""/></a>
                    <a class="now">1</a>
                    <a href="">2</a>
                    <a href="">3</a>
                    <a href="">4</a>
                    <a href="">5</a>
                    <a href="">6</a>
                    <a href="" class="next"><img src="Assets/images/icon8.png" alt=""/></a>
                </div>
                <!--分页-->
                </div>
            </div>
            </form>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>
