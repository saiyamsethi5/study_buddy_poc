package SortAndSearch;

//all imports
import ADT.Assignment;
import ADT.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Algorithms {
	
	public LinkedList<Course> sortByStartTime(LinkedList<Course> courses) //
	{ //method that sorts courses by the start time 
		
		if(courses.size() < 2)
		{
			return courses; //if course is < 2 then return courses 
		} 
		int middle = courses.size()/2; //otherwise middle is the course in the middle 
		
		//make left list
		LinkedList<Course> temp = new LinkedList<Course>(); //creates new temp linked list
		for (int i = 0; i < middle; i++){
			temp.add(courses.get(i)); //adds the courses at i
		}
		LinkedList<Course> left = sortByStartTime(temp); //calls sourt by time function and sorts the left
		
		
		//make right list
		temp = new LinkedList<Course>(); //creates new temp 
		for (int i = middle; i < courses.size(); i++){
			temp.add(courses.get(i)); //adds courses 
		}
		LinkedList<Course> right = sortByStartTime(temp); //sorts the right side
		
		return mergeByStartTime(left,right); //mergees the left and right
	}
	
	public LinkedList<Course> mergeByStartTime(LinkedList<Course> left, LinkedList<Course> right)
	{ //merges the two lists
		LinkedList<Course> result = new LinkedList<Course>(); //creates new linked list 
		int i=0, j=0; //counters
		 
		while(i<left.size() && j< right.size()){ //while the size of lef is smalled
			if(compareStartTime(left.get(i).getStartTime(),right.get(j).getStartTime()) == -1){
				result.add(left.get(i)); //compares start time and adds the result if -1
				i++;
			}
			else{
				result.add(right.get(j)); //otherwise adds the result from the right
				j++;
			}
			
		}
		
		
		//go through all of left
		for(int k = i; k<left.size();k++){
			result.add(left.get(k));
		}
		//go through all of right
		for(int k = j; k<right.size();k++){
			result.add(right.get(k));
		}
		
		return result; //return the result
	}
		
	public int compareStartTime(String startTime1, String startTime2)
	{//compares start time 
		//compares start time
		
		int sTime1,sTime2;//two time variables to compare
		
		if(startTime1.substring(0,startTime1.indexOf(":")).equals("12"))
		{ //if time starts at 12, then add 0 to the start
			startTime1 = "0" + startTime1.substring(startTime1.indexOf(":"));
		}
		
		sTime1 = Integer.parseInt(startTime1.substring(0, startTime1.indexOf(":")) + startTime1.substring(startTime1.indexOf(":") + 1, startTime1.indexOf(":") + 3));
		if (startTime1.substring(startTime1.length()-2).equals("PM"))
		{ //if start time is pm then add 100
			sTime1 += 1200;
		}
		
		if(startTime2.substring(0,startTime2.indexOf(":")).equals("12"))
		{ //if start time is 12 then ad a 0 to the start 
			startTime2 = "0" + startTime2.substring(startTime2.indexOf(":"));
		}
		
		sTime2 = Integer.parseInt(startTime2.substring(0, startTime2.indexOf(":")) + startTime2.substring(startTime2.indexOf(":") + 1, startTime2.indexOf(":") + 3));
		if (startTime2.substring(startTime2.length()-2).equals("PM")){
			//if start time is pm then add 1
			sTime2 += 1200;
		}
		
		
		if(sTime1 == sTime2){
			return 0; //if equal return 0 
		}
		else if (sTime1 > sTime2){
			return 1; //if s1 > s2 then return 1
		}
		else
			return -1; //otherwise return -1
		
	}
	
	public LinkedList<Assignment> sortByDueDate(LinkedList<Assignment> assignments)
	{//sorts assignment by due date
		
		if(assignments.size() < 2){
			return assignments; //returns assignment if size is les than 2 
		}
		int middle = assignments.size()/2; //otherwise gets the middle assignment
		
		//make left list
		LinkedList<Assignment> temp = new LinkedList<Assignment>();
		for (int i = 0; i < middle; i++){
			temp.add(assignments.get(i)); //adds to the temp list
		}
		LinkedList<Assignment> left = sortByDueDate(temp); //sorts the left list
		
		
		//make right list
		temp = new LinkedList<Assignment>();
		for (int i = middle; i < assignments.size(); i++){
			temp.add(assignments.get(i)); //adds to the temp
		}
		LinkedList<Assignment> right = sortByDueDate(temp); //sorts the right 
		
		return mergeByDueDate(left,right); //merges the two lists
	}
	
	public LinkedList<Assignment> mergeByDueDate(LinkedList<Assignment> left, LinkedList<Assignment> right)
	{ //merges by due date 
		LinkedList<Assignment> result = new LinkedList<Assignment>(); //create list to hold result
		int i=0, j=0;
		
		while(i<left.size() && j< right.size())
		{ //while left and right are not 0
			if(left.get(i).getDueDate().compareTo(right.get(j).getDueDate()) < 0)
			{ //if left is less than right
 				result.add(left.get(i)); //add the left tree
				i++;
			}
			else{
				result.add(right.get(j)); //otherwise add right
				j++;
			}
		}
		
		
		//go through all of left
		for(int k = i; k<left.size();k++){
			result.add(left.get(k));
		}
		//go through all of right
		for(int k = j; k<right.size();k++){
			result.add(right.get(k));
		}
		
		return result; //return the result
	}
	
	public int searchForCourseName(LinkedList<Course> courses,String courseName){

		BST<String,Integer> bst = new BST<String,Integer>(); //create new binary search tree
		
		//loop courses
		for(int i = 0; i<courses.size(); i++)
		{ //adds courses to bst
			bst.put(courses.get(i).getCourseName(), i);
		}
		
		if (bst.get(courseName)==null){
			return -1; // if course doesnt exit then return -1
		}
		else
			return bst.get(courseName); //otherwise get the index 
		
	}
	
	public int searchForAssignmentName(LinkedList<Assignment> assignments,String assignmentName)
	{
		BST<String,Integer> bst = new BST<String,Integer>(); //create bst holding assignments 
		
		//loop courses
		for(int i = 0; i<assignments.size(); i++){
			bst.put(assignments.get(i).getAssignmentName(), i); //add the assignments
		}
	
		if (bst.get(assignmentName)==null){
			return -1; //iff assignment == null then return -1 
		}
		else
			return bst.get(assignmentName); //otherwise reutrn index of assignment
	}
}
