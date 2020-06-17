package cn.edu.ccut.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_problem")
public class Problem {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int problemid;
   @Column   
   private String problem;
   @Column   
   private String answer;
   @Column
   private String problemwriter;
   
public int getProblemid() {
	return problemid;
}
public void setProblemid(int problemid) {
	this.problemid = problemid;
}
public String getProblem() {
	return problem;
}
public void setProblem(String problem) {
	this.problem = problem;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getProblemwriter() {
	return problemwriter;
}
public void setProblemwriter(String problemwriter) {
	this.problemwriter = problemwriter;
}
   
}
