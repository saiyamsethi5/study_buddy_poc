package ADT;
import java.util.LinkedList;

public class Course {
	
	private String courseName; //courseName
	private LinkedList<Assignment> assignments; //linked list of assignmnets 
	
	private String startTime; //holds start time
	private String endTime; //end time 
	private LinkedList<String> days = new LinkedList(); //linked list holding days 
	private String location; //holds location 
	
	public Course(String courseName){
		this.courseName = courseName; //sets course name 
		assignments = new LinkedList();//initiliazes assignments linked list 
	}
	
	public void setCourseName(String courseName){
		this.courseName = courseName; //sets the course name
	}
	
	public String getCourseName(){
		return this.courseName; //gets the course name
	}
	
	public void setStartTime(String startTime){
		this.startTime = startTime; //sets the start time
	}
	
	public String getStartTime(){
		return this.startTime; //gets the start time
	}
	
	public void setEndTime(String endTime){ 
		this.endTime = endTime; //sets the end time
	}
	
	public String getEndTime(){
		return this.endTime; //gets the end time
	}
	
	public void addDay(String day){
		days.add(day); //adds the day to the linked list 
	}
	
	public LinkedList<String> getDays(){
		return this.days; //returns the day from the linked list
	}
	
	public void setLocation(String location){
		this.location = location; //sets the locaton 
	}
	
	public String getLocation(){
		return this.location; //gets the location
	}
	
	public void addAssignment(String name){
		assignments.add(new Assignment(name)); //adds assignment to linked list
	}
	
	public LinkedList<Assignment> getAssignments(){
		return assignments; //returns the assignment linked list 
	}
	
	public double calculateGrade(){
	//calculates the grade
		//grade = sum(grade*weight) / sum(weight)
		//sets inital variables to 0
		double percentageGrade = 0.0;
		double gpa = 0.0;
		double numerator = 0;
		double denominator = 0;
		Assignment current;
		
		for (int i = 0; i < assignments.size(); i++){
			current = assignments.get(i); //sets current assignmnet
			
			if (current.getGrade() != 0.0  && current.getWeight() != 0.0){ //gets grade
				numerator += current.getGrade() * current.getWeight(); //gets numerator
				denominator += current.getWeight(); //gets denominator 
			}
		}
		
		percentageGrade = numerator/denominator; //calculates grade

		//change to 4.0 gpa
		if (percentageGrade >= 85){
			gpa = 4.0;
		}
		else if (percentageGrade >= 80){
			gpa = 3.7;
		}
		else if (percentageGrade >= 77){
			gpa = 3.3;
		}
		else if (percentageGrade >= 73){
			gpa = 3.0;
		}
		else if (percentageGrade >= 70){
			gpa = 2.7;
		}
		else if (percentageGrade >= 67){
			gpa = 2.3;
		}
		else if (percentageGrade >= 63){
			gpa = 2.0;
		}
		else if (percentageGrade >= 60){
			gpa = 1.7;
		}
		else if (percentageGrade >= 57){
			gpa = 1.3;
		}
		else if (percentageGrade >= 53){
			gpa = 1.0;
		}
		else if (percentageGrade >= 50){
			gpa = 0.7;
		}
		else{
			gpa = 0;
		}
		
		return gpa; //returns the gpa
		
	}
}
