package cn.edu.ccut.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.service.AdminService;
@Controller("adminController")
@RequestMapping("/adminController")
public class AdminController {
	 @Resource(name = "AdminService")
	private AdminService adminService;
	@RequestMapping("/queryalldoc.do")
	   public ModelAndView queryAllDoc(HttpSession session){
		   List<Information> list = adminService.queryAlldoc();
		   if(list!=null){
			   session.setAttribute("alldoc", list);
			   return new ModelAndView("redirect:../admindoc.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryallppt.do")
	   public ModelAndView queryAllPpt(HttpSession session){
		   List<Information> list = adminService.queryAllppt();
		   if(list!=null){
			   session.setAttribute("allppt", list);
			   return new ModelAndView("redirect:../adminppt.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryallvedio.do")
	   public ModelAndView queryAllVedio(HttpSession session){
		   List<Information> list = adminService.queryAllvedio();
		   if(list!=null){
			   session.setAttribute("allvedio", list);
			   return new ModelAndView("redirect:../adminvedio.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryinformationdoc.do")
	   public ModelAndView queryInformation(HttpServletRequest request,HttpSession session){
		   String writer = request.getParameter("writer");
		   List<Information> list = adminService.queryinformationdocBywriter(writer);
		   if(list!=null){
			   session.setAttribute("allinf", list);
			   return new ModelAndView("redirect:../adminsearchdoc.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryinformationppt.do")
	   public ModelAndView queryInformationppt(HttpServletRequest request,HttpSession session){
		   String writer = request.getParameter("writer");
		   List<Information> list = adminService.queryinformationpptBywriter(writer);
		   if(list!=null){
			   session.setAttribute("allinf", list);
			   return new ModelAndView("redirect:../adminsearchppt.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryinformationvedio.do")
	   public ModelAndView queryInformationvedio(HttpServletRequest request,HttpSession session){
		   String writer = request.getParameter("writer");
		   List<Information> list = adminService.queryinformationvedioBywriter(writer);
		   if(list!=null){
			   session.setAttribute("allinf", list);
			   return new ModelAndView("redirect:../adminsearchvedio.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/admindown.do")
	   public ModelAndView filedown(HttpServletRequest request,Information information,HttpServletResponse response) throws Exception{
		   int id = Integer.parseInt(request.getParameter("id"));
		   information = adminService.queryInformationByID(id);
		   String fileName = information.getFilename();
		 //fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
	         //�ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
	         //String fileSaveRootPath="C:/video";
	         //ͨ���ļ����ҳ��ļ�������Ŀ¼
	         String path = "G:/x";
	         //�õ�Ҫ���ص��ļ�
	         File file = new File(path+"\\"+fileName);
	         //����ļ�������
	         if(!file.exists()){
	             request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
	             System.out.println(fileName);
	             return new ModelAndView ("redirect:../adminindex.jsp");
	            
	         }
	         //�����ļ���
	         String realname = fileName.substring(fileName.indexOf("_")+1);
	         //������Ӧͷ��������������ظ��ļ�
	         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
	         //��ȡҪ���ص��ļ������浽�ļ�������
	         FileInputStream in = new FileInputStream(path+"\\"+fileName);
	         //���������
	         OutputStream out = response.getOutputStream();
	         //����������
	         byte buffer[] = new byte[1024];
	         int len = 0;
	         //ѭ�����������е����ݶ�ȡ������������
	         while((len=in.read(buffer))>0){
	             //��������������ݵ��������ʵ���ļ�����
	             out.write(buffer, 0, len);
	         }
	         //�ر��ļ�������
	         in.close();
	         //�ر������
	         out.close();
	         if(information.getType().equals("1")){
	        	 return new ModelAndView ("redirect:../adminword.jsp");
	         }else if(information.getType().equals("2")){
	        	 return new ModelAndView ("redirect:../adminppt.jsp");
	         }else{
	        	 return new ModelAndView ("redirect:../adminvedio.jsp");
	         }  
	   }
	   @RequestMapping("/queryproblem.do")
	   public ModelAndView queryAllproblem(HttpSession session){
		   List<Problem> list = adminService.queryAllproblem();
		   if(list!=null){
			   session.setAttribute("allproblem", list);
			   return new ModelAndView("redirect:../adminproblem.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/queryproblembywriter.do")
	   public ModelAndView queryproblemBywriter(HttpServletRequest request,HttpSession session){
		   String writer = request.getParameter("writer");
		   List<Problem> list = adminService.queryProblemByWriter(writer);
		   if(list!=null){
			   session.setAttribute("allproblem", list);
			   return new ModelAndView("redirect:../adminproblem.jsp");
		   }else{
			   return new ModelAndView("redirect:../adminindex.jsp");
		   }	   
	   }
	   @RequestMapping("/admindelete.do")
	   public ModelAndView deleteproblem(HttpServletRequest request,HttpSession session){
		   int id =  Integer.parseInt(request.getParameter("id"));
		  // String filewriter = request.getParameter("filewriter");
		 //  System.out.println(filewriter);
		   List<Problem> list = adminService.deleteProblem(id);
		   session.setAttribute("allproblem", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../adminproblem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
}
