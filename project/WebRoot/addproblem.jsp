<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>

    <title>学习资源平台</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   
</head>
<body>
     
           
          
    <div id="wrapper">
         <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="adjust-nav">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img src="assets/img/logo.png" />

                    </a>
                    
                </div>
              
                
            </div>
        </div>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                 


                    <li class="active-link">
                        <a href="index.jsp" ><i class="fa fa-desktop "></i>主页 <span class="badge">Included</span></a>
                    </li>
                   

                    <li>
                        <a href="teacherController/queryinf.do?filewriter=${login.teachername }"><i class="fa fa-table "></i>管理文档  <span class="badge">Included</span></a>
                    </li>
                    <li>
                        <a href="teacherController/querypro.do?problemwriter=${login.teachername }"><i class="fa fa-edit "></i>题目管理  <span class="badge">Included</span></a>
                    </li>


                    <li>
                        <a href="dd.html"><i class="fa fa-qrcode "></i>My Link One</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o"></i>My Link Two</a>
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-edit "></i>My Link Three </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-table "></i>My Link Four</a>
                    </li>
                     <li>
                        <a href="#"><i class="fa fa-edit "></i>My Link Five </a>
                    </li>
                    
                </ul>
                            </div>

        </nav>
        <!-- /. NAV SIDE  -->

        <div id="page-wrapper" >
            <div id="page-inner">
  <form action="teacherController/addproblem.do"  method="post">
     题目名称：  <input type="text" name="problem">  <br />  
     题目答案： <input type="text" name="answer"><br /> 
     上传者：    &nbsp&nbsp&nbsp&nbsp<input type="text" name="problemwriter"><br /> 

       <input type="submit">
    </form>
             </div>
         </div>              
                 
   
</body>
</html>
