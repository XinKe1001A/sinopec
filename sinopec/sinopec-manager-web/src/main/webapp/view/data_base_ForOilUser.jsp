<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>基础数据库操作</title>
		<link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
		<style type="text/css">
			.ztree li span.button.add {
				margin-left: 2px;
				margin-right: -1px;
				background-position: -144px 0;
				vertical-align: top;
				*vertical-align: middle
			}
	.window{  
    	width:1300px;  
    	background-color:#d0def0;  
    	position:absolute;  
    	padding:2px;  
    	margin:5px;  
    	display:none;  
    }  
	.content{  
    	height:800px;  
    	background-color:#FFF;  
    	font-size:14px;  
    	overflow:auto;  
    }  
	.win_title{  
   		padding:2px;  
    	color:#0CF;  
    	font-size:14px;  
    }  
	.win_title img{  
    	float:right;  
    }  
</style>
  </head>
  <body>
  	<%@ include file="header.jsp"%>
  	<span style="margin-left:20px;font-weight:bold;font-size:15px;text-align:center;line-height:15px;">加油站页面</span>
  	<div id="div_root" style="border-radius:5px;border:1px solidred;padding:10px;">
    	<div id="div_treeForType" style="float:left;width:25%;height:89%;max-height:89%;min-height:89%;overflow-y:auto;background-color:white;border:2px solid #000">
    		<ul id="treeForOilUser" class="ztree" style="width:200px; overflow:auto;"></ul>
    	</div>
    	<div style="float:left;margin-left:5px;heigth:89%;width:74%;min-width:74%;overflow-x:auto;max-height:89%;min-height:89%;overflow-y:auto;background-color:white;border:2px solid #000">
			<span>基础数据</span>
			<input type="button" value="确认并查询" onclick="choose()">
			<input type="button" value="新增基础数据条数" id="btn" onclick="popWindow()">
			<table border="1" cellspacing="1" cellpadding="1">
				<tbody	id="table_tbody">
				</tbody>
			</table>
	</div>
	</div>
	<div class="window" id="center">  
        <div id="title" class="win_title">新增基础数据条数</div>
        <div>
        	<div class="content">
        		<table>
        			<tbody id="newdata_tbody">
        			</tbody>
        		</table>
        	</div>  
        </div>
        <div>
        	<input style="margin-left:600px;"type="button" value="添加" onclick="addDataDetail()">
        	<input type="button" value="确认" onclick="addData()">
        	<input type="button" value="取消" onclick="closeWindow()">
        </div>
    </div>  
    
  </body>
<script src="../static/ztree/js/jquery.js"></script>
<script src="../static/ztree/js/jquery.ztree.all-3.5.js"></script>
<script src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<link href="../static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="../static/js/app/treeForOilUser.js"></script>
<link rel="stylesheet" href="../static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../static/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</html>
