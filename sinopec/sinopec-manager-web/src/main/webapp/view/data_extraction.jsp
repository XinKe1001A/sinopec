<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>数据抽取</title>
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
  	<span style="margin-left:20px;font-weight:bold;font-size:15px;text-align:center;line-height:15px;">数据抽取</span>
  	<div id="div_root" style="border-radius:5px;border:1px solidred;padding:10px;">
  		<div id="div_treeForOrganization" style="width:31%;float:left;min-height:28%;overflow-y:auto;max-height:28%;">
    		<ul id="treeForOrganization" class="ztree" style="width:90%; overflow:auto;"></ul>
    	</div>
    	<div id="showdiv" style="float:left;width:33%;height:90%;">
    		<span>基础数据抽取-选中的加油站和基础数据</span>
    		<div style="float:left;width:141%;height:30%;overflow:hidden;"><!-- 2625% -->
    			<div id="showOrgdetail" style="float:left;width:71%;height:87%;overflow-y:scroll;border:2px solid #000">
    				<dl id="delete_choosed">
        			</dl>
        		</div>
        		<div id="showDatadetail" style="float:left;width:29%;height:87%;overflow-y:scroll;border:2px solid #000">
    				<dl id="delete_data_choosed">
        			</dl>
        		</div>
        		<input type="button" onclick="dataextraction()" value="基础数据抽取确认">
        	</div>
		</div>
    	<div id="div_treeForType" style="float:left;margin-left:14%;width:15%;min-height:28%;overflow-y:auto;max-height:28%;">
    		<ul id="treeForType" class="ztree" style="width:90%; overflow:auto;"></ul>
    	</div>
    	
    </div>
    
	<div style="position:absolute;left:5px;top:38%;z-inde:111111;width:99%;height:60%;min-width:99%;overflow-x:auto;min-height:60%;overflow-y:auto;max-height:60%;background-color:white;border:2px solid #000">
		<span>要抽取的基础数据</span>
		<input type="button" value="基础数据抽取预览" onclick="popWindow()">
		<table border="1" cellspacing="1" cellpadding="1">
			<tbody	id="table_tbody">
			</tbody>
		</table>
		
	</div>
	<div class="window" id="center">  
        <div id="title" class="win_title">基础数据抽取预览</div>
        <div>
        	<div class="content" >
        		<table id="table_to_excel" border="1" cellspacing="1" cellpadding="1">
        			<tbody id="newdata_tbody">
        			</tbody>
        		</table>
        	</div>  
        </div>
        <div>
        	<input type="button" value="确认并下载" onclick="exportExcel()">
        	<input type="button" value="取消" onclick="closeWindow()">
        </div>
    </div>  
    
  </body>
<script src="../static/ztree/js/jquery.js"></script>
<script src="../static/ztree/js/jquery.ztree.all-3.5.js"></script>
<script src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<link href="../static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="../static/js/app/treeForOrganization.js"></script>
<script src="../static/js/app/treeForTypeToDataExtraction.js"></script>
<link rel="stylesheet" href="../static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../static/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="../static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</html>
