<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test</title>
        
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
  
  <body onload="load()">
  	<%@ include file="../header.jsp"%>
  	</br>

  
  </body>

  <script src="../static/ztree/js/jquery.js"></script>
  <script src="../static/ztree/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
  <link href="../static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
  <script src="../static/js/app/treeDemo.js"></script>
  
  
  
  
<link rel="stylesheet" href="../static/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="../static/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="../static/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script>
function load(){
//alert("!");
var myArray=new Array();
myArray[0]=1;
$.ajax({
          url : '/sinopec-manager-web/getValuesByOrgsAndClass',
          type : "post",
          data:{
          oids:myArray,
          dcid:1
          },
          success : function(data) {
        	  
        	  console.log(data)
          },
          error : function(msg) {
          			alert("失败!","提示信息");
          }
});

}
</script>
</html>
