package cn.edu.ccut.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.ccut.po.Information;
import cn.edu.ccut.po.Problem;
import cn.edu.ccut.po.User;
@Repository("UserDao")
public class UserDAO {
	@Resource(name="ht")
	private HibernateTemplate ht;
	public int addUser(User user ){
		int number = (Integer) ht.save(user);
		return number;
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
	public List<Problem> queryAllproblem(){
		return ht.find("from Problem");
	}
	public List<Information> queryinformationdocBywriter(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+1+"'");
	}
	public List<Information> queryinformationpptBywriter(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+2+"'");
	}
	public List<Information> queryinformationvedioBywriter(String writer){
		return ht.find("from Information as information where information.filewriter='"+writer+"' and information.type='"+3+"'");
	}
	public Information queryInformationById(int id){
		List<Information> list =  ht.find("from Information as information where information.id='"+id+"'");
		Information inf = list.get(0);
		return inf;
	}
	public List<Problem> queryProblemByWriter(String writer){
		return ht.find("from Problem as problem where problem.problemwriter='"+writer+"'");
	}
}
