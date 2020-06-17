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
    			 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    			                 String savePath ="G:/x";
    			                 File file = new File(savePath);
    			                  //判断上传文件的保存目录是否存在
    			                  if (!file.exists() && !file.isDirectory()) {
    			                      System.out.println(savePath+"目录不存在，需要创建");
    			                      //创建目录
    			                      file.mkdir();
    			                  }
    			                 //消息提示
    			                  String message = "";
    			                  try{
    			                      //使用Apache文件上传组件处理文件上传步骤：
    			                      //1、创建一个DiskFileItemFactory工厂
    			                      DiskFileItemFactory factory = new DiskFileItemFactory();
    			                      //2、创建一个文件上传解析器
    			                      ServletFileUpload upload = new ServletFileUpload(factory);
    			                       //解决上传文件名的中文乱码
    			                      upload.setHeaderEncoding("UTF-8"); 
    			                      //3、判断提交上来的数据是否是上传表单的数据
    			                      if(!ServletFileUpload.isMultipartContent(request)){
    			                          //按照传统方式获取数据
    			                         return null;
    			                      }
    			                      //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
    			                      List<FileItem> list = upload.parseRequest(request);
    			                      for(FileItem item : list){
    			                          //如果fileitem中封装的是普通输入项的数据
    			                          if(item.isFormField()){
    			                              String name = item.getFieldName();
    			                              //解决普通输入项的数据的中文乱码问题
    			                              value = item.getString("UTF-8");
    			                              //value = new String(value.getBytes("iso8859-1"),"UTF-8");
    			                              str[i]=value;
    			                              i++;
    			                           //   System.out.println(name + "=" + value);
    			                          }else{//如果fileitem中封装的是上传文件
    			                              //得到上传的文件名称，
    			                              filename = item.getName();
    			                            // System.out.println(filename);
    			                              if(filename==null || filename.trim().equals("")){
    			                                  continue;
    			                              }
    			                              //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
    			                              //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
    			                              filename = filename.substring(filename.lastIndexOf("\\")+1);
    			                              filename = filename.substring(filename.lastIndexOf("."));
    			                              //获取item中的上传文件的输入流
    			                              InputStream in = item.getInputStream();
    			                              //创建一个文件输出流
    			                              str[0]=str[0]+filename;
    			                              FileOutputStream out = new FileOutputStream(savePath + "\\" + str[0]);
    			                              //创建一个缓冲区
    			                              byte buffer[] = new byte[1024];
    			                              //判断输入流中的数据是否已经读完的标识
    			                              int len = 0;
    			                              //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
    			                             while((len=in.read(buffer))>0){
    			                                  //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
    			                                  out.write(buffer, 0, len);
    			                              }
    			                              //关闭输入流
    			                              in.close();
    			                              //关闭输出流
    			                              out.close();
    			                              //删除处理文件上传时生成的临时文件
    			                              item.delete();
    			                             message = "文件上传成功！";
    			                          }
    			                      }
    			                  }catch (Exception e) {
    			                      message= "文件上传失败！";
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
	             return new ModelAndView ("redirect:../index.jsp");
	            
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
