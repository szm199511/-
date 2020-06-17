package cn.edu.ccut.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.ccut.dao.UserDAO;
import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.po.User;
@Service("UserService")
public class UserService {
	@Resource(name="UserDao")
	private UserDAO userDao;
	public int  addUser(User user){
		int number =  userDao.addUser(user);
		return number;
	}
	public List<Information> queryAlldoc(){
		return userDao.qureyAllinformationdoc();
	}
	public List<Information> queryAllppt(){
		return userDao.qureyAllinformationppt();
	}
	public List<Information> queryAllvedio(){
		return userDao.qureyAllinformationvedio();
	}
	public List<Problem> queryAllproblem(){
		return userDao.queryAllproblem();
	}
	public List<Information> queryinformationdocBywriter(String writer){
		return userDao.queryinformationdocBywriter(writer);
	}
	public List<Information> queryinformationpptBywriter(String writer){
		return userDao.queryinformationpptBywriter(writer);
	}
	public List<Information> queryinformationvedioBywriter(String writer){
		return userDao.queryinformationvedioBywriter(writer);
	}
	public Information queryInformationByID(int id){
		return userDao.queryInformationById(id);
	}
	public List<Problem> queryProblemByWriter(String writer){
		return userDao.queryProblemByWriter(writer);
	}
}
