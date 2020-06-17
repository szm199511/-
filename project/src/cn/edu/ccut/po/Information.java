package cn.edu.ccut.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_information")
public class Information {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   @Column
   private String type;
   @Column
   private String filename;
   @Column
   private String filesrc;
   @Column
   private String filewriter;
public String getFilewriter() {
	return filewriter;
}
public void setFilewriter(String filewriter) {
	this.filewriter = filewriter;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getFilesrc() {
	return filesrc;
}
public void setFilesrc(String filesrc) {
	this.filesrc = filesrc;
}
   
}
