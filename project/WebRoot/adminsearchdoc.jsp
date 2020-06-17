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
                        <a href="adminindex.jsp" ><i class="fa fa-desktop "></i>主页 <span class="badge">Included</span></a>
                    </li>
                   

                    <li >
                       <a href=""><i class="fa fa-table "></i>管理文档  <span class="badge">Included</span></a>
                    </li>
                    <li>
                        <a href=""><i class="fa fa-edit "></i>Blank Page  <span class="badge">Included</span></a>
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
<div align="right">
<form action="" method="post">
  <input type="text" name="writer">
  <input type="submit" value="查询">
</form><br><div>

          <div style="position: absolute; top: 150px; left: 400px;">
	<table >
			<tr>
				<th style="width:100px">
					文档名称
				</th>

				<th style="width:100px">
					上传者
				</th>

				<th style="width:100px">
					是否下载
				</th>

			</tr>
			<c:forEach var="inf" items="${allinf}" varStatus="s">
				<tr>
					<td>
						${inf.filename }
					</td>
					<td>
						${inf.filewriter }
					</td>
					<td>
					<a href="">	下载</a>
					</td>
				</tr>
			</c:forEach>
</table>
</div>
        </div>
          </div>
          


    
   
</body>
</html>
