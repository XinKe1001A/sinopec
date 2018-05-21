<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title></title>
      <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="../static/css/man.css">
     
     <!-- 加载bootstra之前必须先加载jquery 需要jQuery解析 -->
     <!--  <script src="static/js/lib/jquery.js"></script>-->
     <script type="text/javascript" src="../static/bootstrap/js/jquery.js"></script>
     <script type="text/javascript" src="../static/bootstrap/js/bootstrap.min.js"></script>
     
   
     <style type="text/css">
       .panel-heading .caret{
       display: inline-block;
       width: 0;
       height: 0;
       margin-left: 2px;
       margin-top:5px;
       vertical-align: middle;
       border-top: 18px solid;
       border-right: 18px solid transparent;
       border-left:18px solid transparent;
     
       }
     </style>     

  </head>
  
  <body>

 
 
  
   <div class="header_nav" style="background-color:#5BC0DE;height:70px;"><!-- #337ab7 -->
   
   <div class="container-fluid" >
   		<div class="container-fluid"> 	
   		   
   		    <div class="row" > 		    
   		        <div class="col-md-4 header_home"><a href="../home.jsp" ><h2><span class="glyphicon glyphicon-home" ></span> &nbsp;&nbsp;<b>中国石化基础数据系统</b></h2></a></div><!-- 七七一所物资拓展系统 -->
	        	
	        	<div class="clearfix visible-xs-block"></div>
   		        <div class="col-md-4" >
   		        
   		        </div>
   		        <div class="col-md-4" style="padding:0;">
   		            <div class="col-md-3">
                    </div>
                     <div class="col-md-9 " style="padding-right:0px;" >
   		            	<div class="col-md-12" style=" padding-left:2px;padding-right:0px;height:70px;margin-top:15px;white-space:nowrap; "> <!-- class="item_right" -->
   		            	
   		            		<div class="col-md-10" style="text-align:right;vertical-align: middle;padding:0;" >
		   		            	<span class="col-md-12" id="get_position" style="color:white;font-size:18px;"></span>
		   		            	
		   		           		<span id="time1"  class="col-md-12" style="color:white;font-size:18px;" ></span>
		   		            </div>
   		            		<div class="col-md-2" style="padding:0;"><a href="../login.jsp" data-toggle="tooltip" title="退出" data-placement="bottom" onclick="loginOut()" style="font-size:30px;color:white;text-decoration:none;">&nbsp;&nbsp;<span class="glyphicon glyphicon-off "></span></a></div>
   		           
   		            	</div>
   		             
   		           </div>

					<ul class="list-group" id="messagelist" style="position:absolute;height:400px;overflow:auto;right:100px;top:50px"></ul>
   		        </div> 
 				
   		    </div>
            
   		</div>
   		<div class="clearfix visible-xs-block"></div> 
   </div>
  </div>

<div class="clearfix visible-xs"></div>

     <script type="text/javascript">



      </script>  

   

  </body>
</html>
