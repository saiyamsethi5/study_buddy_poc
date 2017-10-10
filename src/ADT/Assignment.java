package ADT;

import java.util.Date;

public class Assignment {

	//global varibales
	private String assignmentName;
	private Date dueDate;
	private double grade;
	private double weight;
	
	///constructor
	public Assignment(String assignmentName){
		this.assignmentName = assignmentName; //assigns the assignment name
	}
	
	
	public void setAssignmentName(String assignmentName){
		this.assignmentName = assignmentName; //sets assignment names
	}

	public String getAssignmentName(){
		return this.assignmentName; //returns the assignment name
	}
	
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate; //sets the due date 
	}
	
	public Date getDueDate(){
		return this.dueDate; //returns the due date
	}
	
	public void setGrade(double grade){
		this.grade = grade; //sets the grade
	}
	
	public double getGrade(){
		return this.grade; //returns the grade
	}
	
	public void setWeight(double weight){
		this.weight = weight; //sets the weight
	}
	
	public double getWeight(){
		return this.weight; //gets the weight
	}
}
