package cn.edu.ccut.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ccut.po.User;
@Repository("LoginDao")
public class LoginDAO  {
	@Resource(name="ht")
	private HibernateTemplate ht;
	
	

	public int queryLoginByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		List<User> result=ht.find("from User as us where us.username='"+username+"' and us.password='"+password+"'");
		
		return result.size();
	}
	
	
	
	public int queryLoginByTeachernameAndTeacherPasswordinTeacher(String teachername, String teacherpassword) {
		// TODO Auto-generated method stub
		List<User> result=ht.find("from Teacher as teacher where teacher.teachername=? and teacher.teacherpassword=?",teachername,teacherpassword);
		
		return result.size();
	}
	
	
	
	
	public int queryLoginByAdminnameAndAdminPasswordinAdmin(String adminname, String adminpassword) {
		// TODO Auto-generated method stub
		List<User> result=ht.find("from Admin as admin where admin.adminname=? and admin.adminpassword=?",adminname,adminpassword);
		
		return result.size();
	}
	
}
