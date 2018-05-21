<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>用户登录</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
    <link rel="stylesheet" type="text/css" href="static/css/man.css">
    <script type="text/javascript" src="static/bootstrap/js/jquery.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
    
     <style type="text/css">
    .content{
    position: absolute;
    left: 0;
    bottom: 0;
    top: 0;
    right: 0;
    background-image: url(static/css/images/index.jpg) ;
    background-size: 100% 100%;
    background-position-x: 50%;
    background-position-y: 30%;
    }
    .login_block{
    position: absolute;
    left: 50%;
    margin-left: -585px;
    bottom:60px;
    width: 1170px;
    height: 80px;
    background-color:#337ab7;;
    border-radius: 4px;
    padding-top:17px;
    }
    .header_text{
    margin-top:50px;
    font-size:60px;
    text-align:center;
    width:100%;
    height:80px;
    
    }
    .header_text span{
    transition:all 0.5s;
    display: inline-block;
    }
    .header_text span:hover{
    transform: scale(1.3);
    }
    .login_block input{
     height:45px;
    }
    .tip{
    position:absolute;
    left:50%;
    height:40px:
    width:200px;
    bottom:145px;
    text-align:center;
    font-size:20px;

    margin-left:-100px;
    }
     </style>
  </head>
  
  <body>
  <form class="form-horizontal"  action="login.do?main" method="post" id="j_spring_security_check" >
       

  <div class="content">
      <div class="header_text"><span><b>中国石化基础数据系统</b></span></div> 
      <div class="tip"> 
      <span id="errinfo" style="color:red;font-weight:bold;">${message}</span>
      </div>
      <div class="login_block">
         <div class="row">
           <div class="col-md-4 col-md-offset-1">
                  <input type="text" name="j_username" id="j_username" class="form-control" placeholder="账号"  autocomplete="off" >
           </div> 
           <div class="col-md-4">
                  <input type="password" name="j_password" id="j_password" class="form-control"  placeholder="密码"  autocomplete="off" >   
           </div> 
        
           <div class="col-md-2">
               <input  class="btn btn-primary btn-lg active" type="submit" id="loginbtn"  value="登录" onsubmit="alert('dl')"> 
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
               <input type="button" class="btn btn-primary btn-lg active" id="reset" value="重置"> 
           </div>
      </div>
  
    </div>
  </div>
  </form>
  <script>
  $("#reset").click(function(){
	  $("#j_username").val("");
	  $("#j_password").val("");
  });
  function check(){
	  if($("#j_username").val()==""||$("#j_password").val()=="")
		  {
			  
			  $("#errinfo").html("用户名或密码不能为空！");
			  return false;
		  }else
			  {
			  return true;
			  }
  }
 
  </script>
  </body>
</html>
