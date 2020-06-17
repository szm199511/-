package cn.edu.ccut.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.po.Teacher;


@Repository("TeacherDao")
public class TeacherDAO {
	@Resource(name="ht")
	private HibernateTemplate ht;
	
	public int addTeacher(Teacher teacher){
		int number = (Integer) ht.save(teacher);
		return number;
	}
	public  int addInformation(Information information){
		int number =  (Integer)ht.save(information);
		return number;
	}
	public List<Information> queryAllInformationBywriter(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"'");
	}
	public  List<Information> qureyAllinformationdoc(){
		return ht.find("from Information as information where information.type='"+1+"'");
	}
	public  List<Information> qureyAllinformationppt(){
		return ht.find("from Information as information where information.type='"+2+"'");
	}
	public  List<Information> qureyAllinformationvedio(){
		return ht.find("from Information as information where information.type='"+3+"'");
	}
	public  List<Information> qureyAllinformationteacherdoc(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+1+"'" );
	}
	public  List<Information> qureyAllinformationteachervedio(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+3+"'" );
	}
	public  List<Information> qureyAllinformationteacherppt(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+2+"'" );
	}
	public Information queryInformationById(int id){
		List<Information> list =  ht.find("from Information as information where information.id='"+id+"'");
		Information inf = list.get(0);
		return inf;
	}
	public boolean  deleteinformation(int id){
		Information inf = (Information) ht.get(Information.class, id);
		try{
			ht.delete(inf);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public int addProblme(Problem problem){
		int number =(Integer) ht.save(problem);
		return number;
	}
	public List<Problem> queryProblemByWriter(String writer){
		return ht.find("from Problem as problem where problem.problemwriter ='"+writer+"'");
	}
	public boolean deleteProblem(int id){
		Problem pro = (Problem) ht.get(Problem.class, id);
		try{
			ht.delete(pro);
			return true;
		}catch(Exception e){
			return false;
		}	
	}
	public  int modifyproblem(Problem problem,int id){
		Problem pro = ht.get(Problem.class, id);
		pro.setProblem(problem.getProblem());
		pro.setAnswer(problem.getAnswer());
		pro.setProblemwriter(problem.getProblemwriter());
		try{
        	ht.update(pro);
        	return 1;
        }catch(Exception e){
			return 0;
		}
	}
	public List<Problem> queryProblemByproblemwriterAndproblem(String problem,String problemwriter){
		return ht.find("from Problem as pro where pro.problem='"+problem+"' and pro.problemwriter='"+problemwriter+"'");
	}
}
