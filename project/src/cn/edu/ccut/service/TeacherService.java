package cn.edu.ccut.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.ccut.dao.TeacherDAO;
import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.po.Teacher;


@Service("TeacherService")
public class TeacherService {
	@Resource(name="TeacherDao")
	private TeacherDAO teacherDao;
	public int  addTeacher(Teacher teacher){
		int number =  teacherDao.addTeacher(teacher);
		return number;
	}
	public List<Information> addInformation(Information information){
		int number = teacherDao.addInformation(information);
		if(number!=0){
			return teacherDao.qureyAllinformationdoc();
		}else{
			return null;
		}
	}
	public List<Information> queryAllinformationDoc(){
		return teacherDao.qureyAllinformationdoc();
	}
	public List<Information> queryAllinformationPPT(){
		return teacherDao.qureyAllinformationppt();
	}
	public List<Information> queryAllinformationVedio(){
		return teacherDao.qureyAllinformationvedio();
	}
	public List<Information> queryAllInformationBywriter(String writer){
		return teacherDao.queryAllInformationBywriter(writer);
	}
	public List<Information> queryteacherInformationdoc(String writer){
		return teacherDao.qureyAllinformationteacherdoc(writer);
	}
	public List<Information> queryteacherInformationppt(String writer){
		return teacherDao.qureyAllinformationteacherppt(writer);
	}
	public List<Information> queryteacherInformationvedio(String writer){
		return teacherDao.qureyAllinformationteachervedio(writer);
	}
	public Information queryInformationByID(int id){
		return teacherDao.queryInformationById(id);
	}
	public List<Information> deleteinformation(int id,String writer){
		boolean flag = teacherDao.deleteinformation(id);
		if(flag){
			return teacherDao.queryAllInformationBywriter(writer);
		}else{
			return null;
		}
	}
	public List<Problem> queryproblemByWriter(String writer){
		return teacherDao.queryProblemByWriter(writer);
	}
	public int addProblem(Problem problem){
		int num = teacherDao.addProblme(problem);
		return num;
	}
	public List<Problem> deleteProblem(int id, String writer){
		boolean flag = teacherDao.deleteProblem(id);
		if(flag){
			return teacherDao.queryProblemByWriter(writer);
		}else{
			return null;
		}
	}
	public List<Problem> modifyProblem(Problem pro , int id){
		int num = teacherDao.modifyproblem(pro, id);
		if(num!=0){
			return teacherDao.queryProblemByWriter(pro.getProblemwriter());
		}else{
			return null;
		}
	}
	public List<Problem> queryProblemByproblemwriterAndproblem(String problem,String problemwriter){
		return teacherDao.queryProblemByproblemwriterAndproblem(problem, problemwriter);
	}
}
