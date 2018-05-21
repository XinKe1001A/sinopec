<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
  
    <title>首页</title>
     <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="static/css/man.css">
  </head>
  
  <body>
    <%@ include file="header.jsp"%>
    
     <div class="box">
    
         <div class="image_box">
	           <img alt="" src="static/css/images/0.jpg">
         </div>
         
         <div class="menu_box">
        	<ul>
	          <li>
	            <a href="./view/data_extraction.jsp"><div class="menublock btn "  ><span class="glyphicon glyphicon-folder-open "></span><div class="menusize"><span><b>个人工作台</b></span></div></div></a>
	          </li>
	          <li>
	            <a href="./view/data_base.jsp" ><div class="menublock btn"   ><span class="glyphicon glyphicon-th"></span><div class="menusize"><span><b>基础数据库</b></span></div></div></a>      
	          </li>
	          <li>
	            <a href="./view/data_management.jsp" ><div class="menublock btn"   ><span class="glyphicon glyphicon-th-large"></span><div class="menusize"><span><b>数据管理</b></span></div></div></a>     
	          </li>
	       </ul>
	       <ul>
	          <li>
	              <a href="" ><div class="menublock btn " ><span class="glyphicon glyphicon-list-alt "></span><div class="menusize"><span><b>报表生成</b></span></div></div></a>
	          </li>
	          <li>
	            <a href="" ><div class="menublock btn"   ><span class="glyphicon glyphicon-file"></span><div class="menusize"><span><b>报表填写</b></span></div></div></a>     
	          </li>
	          <li>
	            <a href="" ><div class="menublock btn"   ><span class="glyphicon glyphicon-tasks"></span><div class="menusize"><span><b>报表进度</b></span></div></div></a>     
	          </li>
	       </ul>
	       <ul>
	          <li>
	              <a href="" ><div class="menublock btn "  ><span class="glyphicon glyphicon-stats "></span><div class="menusize"><span><b>统计分析</b></span></div></div></a>
	          </li>
	          <li>
	            <a href="" ><div class="menublock btn"    ><span class="glyphicon glyphicon-cog"></span><div class="menusize"><span><b>用户设置</b></span></div></div></a>     
	          </li>
	          <li ><!-- style="visibility:hidden;" -->
	            <a href="./view/data_base_ForOilUser.jsp" ><div class="menublock btn" ><span class="glyphicon glyphicon-compressed"></span><div class="menusize"><span><b>加油站页面</b></span></div></div></a>     
	          </li>
	       </ul>
          
         </div>
    <!--  <div style="clear:both;"></div>-->
 </div> 
  </body>
</html>
