package ADT;
import java.util.Date;
import java.util.LinkedList;

public class Semester {

	private String semesterName; //holds semester name 
	private LinkedList<Course> courses; //linked list of courses 
	private Date startDate;//holds start date
	private Date endDate; //holds end date
	
	
	public Semester(String semesterName)
	{
		this.semesterName = semesterName; //sets semester name 
		courses = new LinkedList<Course>(); //creates courses linked list
	}
	
	public void setSemesterName(String semesterName){
		this.semesterName = semesterName; //sets the semester name 
	}
	
	public String getSemesterName(){
		return this.semesterName; //gets the semester name
	}
	
	public void setStartDate(Date startDate){
		this.startDate = startDate; //sets the start date
	}
	
	public Date getStartDate(){
		return this.startDate; //gets the start date 
	}
	
	public void setEndDate(Date endDate){
		this.endDate = endDate; //sets the end ate
	}
	
	public Date getEndDate(){
		return this.endDate; //gets the end date
	}	

	public void addCourse(String name){
		courses.add(new Course(name)); //adds courses to linked list
	}
	
	public LinkedList<Course> getCourse(){
		return this.courses; //returns courses
	}
	 
	public double calculateGPA(){
		//calculates the gpa
		int count = 0;
		double totalGrades = 0;
		
		for (int i = 0; i < courses.size(); i++){
			totalGrades += courses.get(i).calculateGrade(); //gets total grades 
			count++; //adds count 
		}
		if (count == 0){
			return 0; //if count is 0 return 0
		}
		return totalGrades/count; //otherwise return total grades / count
	}
}
