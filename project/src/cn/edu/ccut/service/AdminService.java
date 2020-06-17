package cn.edu.ccut.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.ccut.dao.AdminDAO;
import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
@Service("AdminService")
public class AdminService {
	@Resource(name="AdminDao")
	private AdminDAO adminDao;
	public List<Information> queryAlldoc(){
		return adminDao.qureyAllinformationdoc();
	}
	public List<Information> queryAllppt(){
		return adminDao.qureyAllinformationppt();
	}
	public List<Information> queryAllvedio(){
		return adminDao.qureyAllinformationvedio();
	}
	public List<Information> queryinformationdocBywriter(String writer){
		return adminDao.queryinformationdocBywriter(writer);
	}
	public List<Information> queryinformationpptBywriter(String writer){
		return adminDao.queryinformationpptBywriter(writer);
	}
	public List<Information> queryinformationvedioBywriter(String writer){
		return adminDao.queryinformationvedioBywriter(writer);
	}
	public Information queryInformationByID(int id){
		return adminDao.queryInformationById(id);
	}
	public List<Problem> queryAllproblem(){
		return adminDao.queryAllproblem();
	}
	public List<Problem> queryProblemByWriter(String writer){
		return adminDao.queryProblemByWriter(writer);
	}
	public List<Problem> deleteProblem(int id){
		boolean flag = adminDao.deleteProblem(id);
		if(flag){
			return adminDao.queryAllproblem();
		}else{
			return null;
		}
	}
}
