package cn.edu.ccut.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.service.TeacherService;
@Controller("teacherController")
@RequestMapping("/teacherController")
public class TeacherController {
	@Resource(name = "TeacherService")
	private TeacherService teacherService;
	   @RequestMapping("/upload.do")
       public ModelAndView Upload(HttpSession session,HttpServletRequest request, Information information,HttpServletResponse response){  	   
    		    String filename = "";
    			String value="";
    			String[] str=new String[4];
    			int i=0;
    			//String videotext=request.getParameter("text").toString();
    			 //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
    			                 String savePath ="G:/x";
    			                 File file = new File(savePath);
    			                  //�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
    			                  if (!file.exists() && !file.isDirectory()) {
    			                      System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
    			                      //����Ŀ¼
    			                      file.mkdir();
    			                  }
    			                 //��Ϣ��ʾ
    			                  String message = "";
    			                  try{
    			                      //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
    			                      //1������һ��DiskFileItemFactory����
    			                      DiskFileItemFactory factory = new DiskFileItemFactory();
    			                      //2������һ���ļ��ϴ�������
    			                      ServletFileUpload upload = new ServletFileUpload(factory);
    			                       //����ϴ��ļ�������������
    			                      upload.setHeaderEncoding("UTF-8"); 
    			                      //3���ж��ύ�����������Ƿ����ϴ���������
    			                      if(!ServletFileUpload.isMultipartContent(request)){
    			                          //���մ�ͳ��ʽ��ȡ����
    			                         return null;
    			                      }
    			                      //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
    			                      List<FileItem> list = upload.parseRequest(request);
    			                      for(FileItem item : list){
    			                          //���fileitem�з�װ������ͨ�����������
    			                          if(item.isFormField()){
    			                              String name = item.getFieldName();
    			                              //�����ͨ����������ݵ�������������
    			                              value = item.getString("UTF-8");
    			                              //value = new String(value.getBytes("iso8859-1"),"UTF-8");
    			                              str[i]=value;
    			                              i++;
    			                           //   System.out.println(name + "=" + value);
    			                          }else{//���fileitem�з�װ�����ϴ��ļ�
    			                              //�õ��ϴ����ļ����ƣ�
    			                              filename = item.getName();
    			                            // System.out.println(filename);
    			                              if(filename==null || filename.trim().equals("")){
    			                                  continue;
    			                              }
    			                              //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
    			                              //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
    			                              filename = filename.substring(filename.lastIndexOf("\\")+1);
    			                              filename = filename.substring(filename.lastIndexOf("."));
    			                              //��ȡitem�е��ϴ��ļ���������
    			                              InputStream in = item.getInputStream();
    			                              //����һ���ļ������
    			                              str[0]=str[0]+filename;
    			                              FileOutputStream out = new FileOutputStream(savePath + "\\" + str[0]);
    			                              //����һ��������
    			                              byte buffer[] = new byte[1024];
    			                              //�ж��������е������Ƿ��Ѿ�����ı�ʶ
    			                              int len = 0;
    			                              //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
    			                             while((len=in.read(buffer))>0){
    			                                  //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
    			                                  out.write(buffer, 0, len);
    			                              }
    			                              //�ر�������
    			                              in.close();
    			                              //�ر������
    			                              out.close();
    			                              //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
    			                              item.delete();
    			                             message = "�ļ��ϴ��ɹ���";
    			                          }
    			                      }
    			                  }catch (Exception e) {
    			                      message= "�ļ��ϴ�ʧ�ܣ�";
    			                      e.printStackTrace();
    			                      
    			                  }
    		   information.setFilename(str[0]);
    		   information.setFilesrc(savePath);
    		   information.setType(str[1]);
    		   information.setFilewriter(str[2]);
    		   if(str[1].equals("1")){
    			   List<Information> list = teacherService.addInformation(information);
    			   List<Information> list1 =teacherService.queryAllinformationDoc();
    			   session.setAttribute("allinf",list1);
    			   if(list!=null){
    				   return new ModelAndView ("redirect:../word.jsp");
    			   } else{
    				   return new ModelAndView ("redirect:../index.jsp");
    			   }
    		   }else if(str[1].equals("2")){
    			   List<Information> list = teacherService.addInformation(information);
    			   List<Information> list1 =teacherService.queryAllinformationPPT();
    			   session.setAttribute("allinf",list1);
    			   if(list!=null){
    				   return new ModelAndView ("redirect:../ppt.jsp");
    			   } else{
    				   return new ModelAndView ("redirect:../index.jsp");
    			   }
    		   } else{
    			   List<Information> list = teacherService.addInformation(information);
    			   List<Information> list1 =teacherService.queryAllinformationVedio();
    			   session.setAttribute("allinf",list1);
    			   if(list!=null){
    				   return new ModelAndView ("redirect:../vedio.jsp");
    			   } else{
    				   return new ModelAndView ("redirect:../index.jsp");
    			   }
    		   } 	   
       } 
	   @RequestMapping("/queryinf.do")
	   public ModelAndView queryAllInformationByfilewriter(HttpSession session,HttpServletRequest request){
		   String filewriter = request.getParameter("filewriter");
		   List<Information> list = teacherService.queryAllInformationBywriter(filewriter);
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../manageinformation.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/queryinfdoc.do")
	   public ModelAndView queryAllInformationDoc(HttpSession session){
		   List<Information> list =  teacherService.queryAllinformationDoc();
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView("redirect:../word.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");	   
		   }
	   }
	   @RequestMapping("/queryinfvedio.do")
	   public ModelAndView queryAllInformationVedio(HttpSession session){
		   List<Information> list =  teacherService.queryAllinformationVedio();
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView("redirect:../vedio.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");	   
		   }
	   }
	   @RequestMapping("/queryinfppt.do")
	   public ModelAndView queryAllInformationPPT(HttpSession session){
		   List<Information> list =  teacherService.queryAllinformationPPT();
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView("redirect:../ppt.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");	   
		   }
	   }
	   @RequestMapping("/queryteacherdoc.do")
	   public ModelAndView queryteacherInformationdoc(HttpSession session,HttpServletRequest request){
		   String filewriter = request.getParameter("filewriter");
		   List<Information> list = teacherService.queryteacherInformationdoc(filewriter);
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../searchdoc.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/queryteacherppt.do")
	   public ModelAndView queryteacherInformationppt(HttpSession session,HttpServletRequest request){
		   String filewriter = request.getParameter("filewriter");
		   List<Information> list = teacherService.queryteacherInformationppt(filewriter);
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../searchppt.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/queryteachervedio.do")
	   public ModelAndView queryteacherInformationvedio(HttpSession session,HttpServletRequest request){
		   String filewriter = request.getParameter("filewriter");
		   List<Information> list = teacherService.queryteacherInformationvedio(filewriter);
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../searchvedio.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/down.do")
	   public ModelAndView filedown(HttpServletRequest request,Information information,HttpServletResponse response) throws Exception{
		   int id = Integer.parseInt(request.getParameter("id"));
		   information = teacherService.queryInformationByID(id);
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
	             return new ModelAndView ("redirect:../index.jsp");
	            
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
	        	 return new ModelAndView ("redirect:../word.jsp");
	         }else if(information.getType().equals("2")){
	        	 return new ModelAndView ("redirect:../ppt.jsp");
	         }else{
	        	 return new ModelAndView ("redirect:../vedio.jsp");
	         }  
	   }
	   @RequestMapping("/delete.do")
	   public ModelAndView deletefile(HttpServletRequest request,HttpSession session){
		   int id =  Integer.parseInt(request.getParameter("id"));
		   String filewriter = request.getParameter("filewriter");
		 //  System.out.println(filewriter);
		   List<Information> list = teacherService.deleteinformation(id, filewriter);
		   session.setAttribute("allinf", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../manageinformation.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/querypro.do")
	   public ModelAndView queryproblem(HttpSession session,HttpServletRequest request){
		   String writer = request.getParameter("problemwriter");
		   List<Problem> list = teacherService.queryproblemByWriter(writer);
		   session.setAttribute("allpro", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../problem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/addproblem.do")
	    public ModelAndView Addproblem(HttpServletRequest request,HttpSession session,Problem problem){
		   String pb = request.getParameter("problem");
		   String answer = request.getParameter("answer");
		   String problemwriter = request.getParameter("problemwriter");
		   problem.setProblem(pb);
		   problem.setAnswer(answer);
		   problem.setProblemwriter(problemwriter);
		   int num = teacherService.addProblem(problem);
		   if(num!=0){
			   List<Problem> list = teacherService.queryproblemByWriter(problemwriter);
			   session.setAttribute("allpro", list);
			   return new ModelAndView ("redirect:../problem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/deletepro.do")
	   public ModelAndView deleteproblem(HttpServletRequest request,HttpSession session){
		   int id =  Integer.parseInt(request.getParameter("id"));
		   String problemwriter = request.getParameter("problemwriter");
		   List<Problem> list = teacherService.deleteProblem(id, problemwriter);
		   session.setAttribute("allpro", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../problem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
 	   }
	   @RequestMapping("/modifypro.do")
	   public ModelAndView modifyproblem(HttpServletRequest request,HttpSession session,Problem problem){
		   int id = Integer.parseInt(request.getParameter("problemid"));
		   String pro = request.getParameter("prob");
		   String answer = request.getParameter("answer");
		   String problemwriter = request.getParameter("problemwriter");
		   problem.setProblem(pro);
		   problem.setAnswer(answer);
		   problem.setProblemwriter(problemwriter);
		   List<Problem> list = teacherService.modifyProblem(problem, id);
		   session.setAttribute("allpro", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../problem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
	   @RequestMapping("/searchpro.do")
	    public ModelAndView modifyproblem(HttpServletRequest request,HttpSession session){
		   String problem = request.getParameter("problem");
		   String problemwriter = request.getParameter("problemwriter");
		   List<Problem> list = teacherService.queryProblemByproblemwriterAndproblem(problem, problemwriter);
		   session.setAttribute("allpro", list);
		   if(list!=null){
			   return new ModelAndView ("redirect:../problem.jsp");
		   }else{
			   return new ModelAndView ("redirect:../index.jsp");
		   }
	   }
}
