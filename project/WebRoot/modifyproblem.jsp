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
                <div class="navbar-header">&nbsp; 
                    
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
                        <a href="index.jsp" ><i class="fa fa-desktop "></i>主页 <span class="badge"></span></a>
                    </li>
                   

                    <li>
                        <a href="teacherController/queryinf.do?filewriter=${login.teachername }"><i class="fa fa-table "></i>管理文档  <span class="badge"></span></a>
                    </li>
                                        <li>
                        <a href=""><i class="fa fa-table "></i>试题库  <span class="badge"></span></a>
                    </li>
                    <li>
                        <a href="teacherController/querypro.do?problemwriter=${login.teachername }"><i class="fa fa-edit "></i>题目管理  <span class="badge"></span></a>
                    </li>


                    <li>
                        <a href=""><i class="fa fa-qrcode "></i>站内信</a>
                    </li>
                    
                    
                </ul>
                            </div>

        </nav>
        <!-- /. NAV SIDE  -->

        <div id="page-wrapper" >
            <div id="page-inner">
                   <form action="teacherController/modifypro.do" method="post">
                   <input type="hidden" name= "problemid" value="${allpro[param.index].problemid }"/>
                       题目名称：  <input type="text" value="${allpro[param.index].problem }" name="prob">  <br />  
     	    题目答案： <input type="text" value="${allpro[param.index].answer }" name="answer"><br /> 
                       上传者：    &nbsp&nbsp&nbsp&nbsp<input type="text"  value="${allpro[param.index].problemwriter }" name="problemwriter" readonly="readonly"><br /> 

       <input type="submit" value="提交">
                   </form>
             </div>
         </div>              
                 
   
</body>
</html>
