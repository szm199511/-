package cn.edu.ccut.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.po.Teacher;
import cn.edu.ccut.po.User;
import cn.edu.ccut.service.TeacherService;
import cn.edu.ccut.service.UserService;

@Controller("userController")
@RequestMapping("/userController")
public class UserController {
   @Resource(name = "UserService")
   private UserService userService;
   @Resource(name = "TeacherService")
   private TeacherService teacherService;
   @RequestMapping("/adduser.do")
   public ModelAndView addUserAndTeacher(HttpServletRequest request,
				HttpServletResponse response,HttpSession session,User user,Teacher teacher){
	   String username = request.getParameter("username");
	   String password = request.getParameter("password");
	   String selecttype = request.getParameter("selecttype");
	   if(selecttype.equals("1")){
		   user.setUsername(username);
		   user.setPassword(password);
		   int number = userService.addUser(user);
		   if(number!=0){
			   return new ModelAndView("redirect:../login.jsp");
		   }else{
			   return new ModelAndView("redirect:../login.jsp");
		   }
	   }else{
		   teacher.setTeachername(username);
		   teacher.setTeacherpassword(password);
		   int number =  teacherService.addTeacher(teacher);
		   if(number!=0){
			   return new ModelAndView("redirect:../login.jsp");
		   }else{
			   return new ModelAndView("redirect:../login.jsp"); 
		   }
	   }
   }
   @RequestMapping("/queryalldoc.do")
   public ModelAndView queryAllDoc(HttpSession session){
	   List<Information> list = userService.queryAlldoc();
	   if(list!=null){
		   session.setAttribute("alldoc", list);
		   return new ModelAndView("redirect:../studoc.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryallppt.do")
   public ModelAndView queryAllPpt(HttpSession session){
	   List<Information> list = userService.queryAllppt();
	   if(list!=null){
		   session.setAttribute("allppt", list);
		   return new ModelAndView("redirect:../stuppt.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryallvedio.do")
   public ModelAndView queryAllVedio(HttpSession session){
	   List<Information> list = userService.queryAllvedio();
	   if(list!=null){
		   session.setAttribute("allvedio", list);
		   return new ModelAndView("redirect:../stuvedio.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryinformationdoc.do")
   public ModelAndView queryInformation(HttpServletRequest request,HttpSession session){
	   String writer = request.getParameter("writer");
	   List<Information> list = userService.queryinformationdocBywriter(writer);
	   if(list!=null){
		   session.setAttribute("allinf", list);
		   return new ModelAndView("redirect:../stusearchdoc.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryinformationppt.do")
   public ModelAndView queryInformationppt(HttpServletRequest request,HttpSession session){
	   String writer = request.getParameter("writer");
	   List<Information> list = userService.queryinformationpptBywriter(writer);
	   if(list!=null){
		   session.setAttribute("allinf", list);
		   return new ModelAndView("redirect:../stusearchppt.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryinformationvedio.do")
   public ModelAndView queryInformationvedio(HttpServletRequest request,HttpSession session){
	   String writer = request.getParameter("writer");
	   List<Information> list = userService.queryinformationvedioBywriter(writer);
	   if(list!=null){
		   session.setAttribute("allinf", list);
		   return new ModelAndView("redirect:../stusearchvedio.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/studown.do")
   public ModelAndView filedown(HttpServletRequest request,Information information,HttpServletResponse response) throws Exception{
	   int id = Integer.parseInt(request.getParameter("id"));
	   information = userService.queryInformationByID(id);
	   String fileName = information.getFilename();
	 //fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
         //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
         //String fileSaveRootPath="C:/video";
         //通过文件名找出文件的所在目录
         String path = "G:/x";
         //得到要下载的文件
         File file = new File(path+"\\"+fileName);
         //如果文件不存在
         if(!file.exists()){
             request.setAttribute("message", "您要下载的资源已被删除！！");
             System.out.println(fileName);
             return new ModelAndView ("redirect:../stuindex.jsp");
            
         }
         //处理文件名
         String realname = fileName.substring(fileName.indexOf("_")+1);
         //设置响应头，控制浏览器下载该文件
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream in = new FileInputStream(path+"\\"+fileName);
         //创建输出流
         OutputStream out = response.getOutputStream();
         //创建缓冲区
         byte buffer[] = new byte[1024];
         int len = 0;
         //循环将输入流中的内容读取到缓冲区当中
         while((len=in.read(buffer))>0){
             //输出缓冲区的内容到浏览器，实现文件下载
             out.write(buffer, 0, len);
         }
         //关闭文件输入流
         in.close();
         //关闭输出流
         out.close();
         if(information.getType().equals("1")){
        	 return new ModelAndView ("redirect:../stuword.jsp");
         }else if(information.getType().equals("2")){
        	 return new ModelAndView ("redirect:../stuppt.jsp");
         }else{
        	 return new ModelAndView ("redirect:../stuvedio.jsp");
         }  
   }
   @RequestMapping("/queryproblem.do")
   public ModelAndView queryAllproblem(HttpSession session){
	   List<Problem> list = userService.queryAllproblem();
	   if(list!=null){
		   session.setAttribute("allproblem", list);
		   return new ModelAndView("redirect:../stuproblem.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/queryproblembywriter.do")
   public ModelAndView queryproblemBywriter(HttpServletRequest request,HttpSession session){
	   String writer = request.getParameter("writer");
	   List<Problem> list = userService.queryProblemByWriter(writer);
	   if(list!=null){
		   session.setAttribute("allproblem", list);
		   return new ModelAndView("redirect:../stuproblem.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/onlinejudge.do")
   public ModelAndView OnlineJudge(HttpServletRequest request,HttpSession session){
	   List<Problem> list = userService.queryAllproblem();
	   int num = list.size();
	   Random random =  new Random();
	   Set<Integer> allSet = new TreeSet<Integer>();
		while(true)
		{
			allSet.add(new Random().nextInt(num));
			if(allSet.size()==10)
			{
				break;
			}
		}
		Integer[] temp = allSet.toArray(new Integer[] {});
		int[] intArray = new int[temp.length];

		for (int i = 0; i < temp.length; i++) {

			intArray[i] = temp[i].intValue();

		}
	   List<Problem> list1 = new ArrayList();
	   for(int i=0;i<10;i++){
		   list1.add(list.get(intArray[i]));
	   }
	   session.setAttribute("allpro", list1);
	   if(list1!=null){
		   return new ModelAndView("redirect:../stuonlinejudge.jsp");
	   }else{
		   return new ModelAndView("redirect:../stuindex.jsp");
	   }	   
   }
   @RequestMapping("/onlinejudgeresult.do")
   public ModelAndView OnlineJudgeResult(HttpServletRequest request,HttpSession session){
	   String a[] = new String [10];
	   String b[] = new String [10];
	   String c[] = new String [10];
	   int d []   =  new int [10];
	   String e[] = new String [10];
	   a = request.getParameterValues("answer");
	   b = request.getParameterValues("answer1");
	   c = request.getParameterValues("id");
	 /*  for(int i = 0;i<10;i++){
		   d[i] =Integer.parseInt(c[i]); 
	   }*/
	   int result=0;

	   List<Problem> list = new ArrayList();
	  // List<Problem> list1 = new ArrayList();
	 //  list= userService.queryAllproblem();
	   for(int i = 0;i<10;i++){		 
		  /* System.out.print(b[i]);
		   System.out.println(a[i]);*/
		  if(a[i].equals(b[i])){
			  result+=10;
		  }else{
			  Problem problem = new Problem();
			  problem.setProblem(c[i]);
			  problem.setAnswer(b[i]);
			  list.add(problem);
		  } 	  
	   }
	  /* for(int i =0;i<list.size();i++){
		   System.out.println(list.get(i).getProblem());
	   }*/
	   session.setAttribute("result", result);
	   session.setAttribute("allproblem", list);
	   
		   return new ModelAndView("redirect:../stuonlinejudgeresult.jsp");
	
   }
}
