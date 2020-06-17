package cn.edu.ccut.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.ccut.dao.LoginDAO;

@Service("LoginService")
public class LoginService  {
	@Resource(name="LoginDao")
	private LoginDAO loginDao;
	public boolean loginuser(String username, String password) {
		// TODO Auto-generated method stub
		int count=loginDao.queryLoginByUsernameAndPassword(username, password);
		if(count>0){
			return true;
			
		}else{
			return false;
		}
		
	}
	public boolean loginteacher(String teachername, String teacherpassword) {
		// TODO Auto-generated method stub
		int count=loginDao.queryLoginByTeachernameAndTeacherPasswordinTeacher(teachername, teacherpassword);
		if(count>0){
			return true;
			
		}else{
			return false;
		}
		
	}
	public boolean loginadmin(String adminname, String adminpassword) {
		// TODO Auto-generated method stub
		int count=loginDao.queryLoginByAdminnameAndAdminPasswordinAdmin(adminname, adminpassword);
		if(count>0){
			return true;
			
		}else{
			return false;
		}
		
	}

}
