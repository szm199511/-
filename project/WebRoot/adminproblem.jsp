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
                        <a href="adminindex.jsp" ><i class="fa fa-desktop "></i>主页 <span class="badge"></span></a>
                    </li>
                   

                    <li >
                       <a href="adminController/queryproblem.do"><i class="fa fa-table "></i>试题库  <span class="badge"></span></a>
                    </li>
                    <li>
                        <a href=""><i class="fa fa-edit "></i>文档管理  <span class="badge"></span></a>
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
<div align="right">
<form action="adminController/queryproblembywriter.do" method="post">
  <input type="text" name="writer">
  <input type="submit" value="查询">
</form><br><div>
          <div style="position: absolute; top: 150px; left: 400px;">
	<table >
			<tr>
				<th style="width:100px">
					题目名称
				</th>

				<th style="width:100px">
					题目答案
				</th>

				<th style="width:100px">
					上传者
				</th>
				<th style="width:100px">
					是否删除
				</th>
			</tr>
			<c:forEach var="problem" items="${allproblem }" varStatus="s">
				<tr>
					<td>
						${problem.problem } 
					</td>
					<td>
						${problem.answer} 
					</td>
					<td>
					   ${problem.problemwriter} 
					</td>
					<td>
					  <a href="adminController/admindelete.do?id=${problem.problemid }">删除</a>
					</td>
				</tr>
			</c:forEach>
</table>
</div>
        </div>
          </div>
          


    
   
</body>
</html>
