package cn.edu.ccut.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.ccut.po.Admin;
import cn.edu.ccut.po.Teacher;
import cn.edu.ccut.po.User;
import cn.edu.ccut.service.LoginService;

@Controller("loginController")
@RequestMapping("/loginController")
public class LoginController {
	@Resource(name = "LoginService")
	private LoginService loginService;

	@RequestMapping("/login.do")
	public ModelAndView login(HttpSession session,HttpServletRequest request,Teacher teacher,Admin admin,User user) {
		String selecttype=request.getParameter("selecttype");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
 		if(selecttype.equals("1")){
 			/*System.out.println(name);
 			System.out.println(password);
 			System.out.println(selecttype);*/
			boolean flag = loginService.loginuser(name,password);
		//	System.out.println(flag);
			if (flag) {
				user.setUsername(name);
				user.setPassword(password);
				session.setAttribute("login", user);
				return new ModelAndView("redirect:../stuindex.jsp");
			} else {
				return new ModelAndView("redirect:../login.jsp");
			}
		}else if(selecttype.equals("2")){
			//System.out.println(selecttype);
			boolean flag = loginService.loginteacher(name,password);
			//System.out.println(teacher.getTeachername());
			if (flag) {
				teacher.setTeachername(name);
				teacher.setTeacherpassword(password);
				session.setAttribute("login", teacher);
				return new ModelAndView("redirect:../index.jsp");
			} else {
				return new ModelAndView("redirect:../login.jsp");
			}
		}else{		
			boolean flag = loginService.loginadmin(name,password);
			if (flag) {
				admin.setAdminname(name);
				admin.setAdminpassword(password);
				session.setAttribute("login", admin);
				return new ModelAndView("redirect:../adminindex.jsp");
			} else {
				return new ModelAndView("redirect:../login.jsp");
			}
		}
	}
}
