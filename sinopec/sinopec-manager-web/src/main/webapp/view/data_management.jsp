<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>数据管理</title>
        
		<link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
		<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
		
  </head>
  <%@ include file="header.jsp" %>
  <body>
  <div>
  	<div style="float:left;width:30%">
    	<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
    </div>
   	<div id = "Detial" align="center" style="float:left;width:30%">
   	
   	</div>
    <div id = "attrTable" align="center" style="float:left;width:30%">
  		
  	</div> 	
   </div>
    
  
  </body>
  <script src="../static/ztree/js/jquery.js"></script>
  <script src="../static/ztree/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
  <link href="../static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">

  
  
  
  
<link rel="stylesheet" href="../static/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="../static/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
	  <script src="../static/js/app/datamanagetree.js"></script>
</html>
