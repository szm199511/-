<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html >
<head>

    <title>学习资源平台</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />

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
                    
                        <img src="assets/img/logo.png" />
                    
                </div>
              

            </div>
        </div>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                 


                     <li> 
                        <a href="index.jsp" ><i class="fa fa-desktop "></i>主页 <span class="badge">Included</span></a>
                    </li>
                   

                    <li >
                       <a href="userController/queryproblem.do"><i class="fa fa-table "></i>试题库  <span class="badge">Included</span></a>
                    </li>
                    <li>
                        <a href=""><i class="fa fa-edit "></i>题目管理  <span class="badge">Included</span></a>
                    </li>


 <li>
                        <a href="#"><i class="fa fa-qrcode "></i>My Link One</a>
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

          <div style="position: absolute; top: 150px; left: 400px;">
	<table >
			<tr>
				<th style="width:100px">
					错误题目
				</th>

				<th style="width:100px">
					正确答案
				</th>

				<th style="width:100px">
					
				</th>
				
			</tr>
			<c:forEach var="problem" items="${allproblem }" varStatus="s">
				<tr>
					<td>
						${problem.problem } 
					</td>
					<td>
						${problem.answer }
					</td>

				</tr>
			</c:forEach>
	
		你的分数：${result} 
</table>
</div>
        </div>
          </div>
          


    
   
</body>
</html>
