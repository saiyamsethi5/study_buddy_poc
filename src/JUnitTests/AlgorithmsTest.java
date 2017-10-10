package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ADT.Assignment;
import ADT.Course;

import java.util.Date;
import java.util.LinkedList;

import SortAndSearch.Algorithms;

public class AlgorithmsTest {

	Algorithms test = new Algorithms();
	
	@Test
	public void testCompareStartTime(){
		//test if compareStartTime() is working
		
		//create some test data in the right format
		String time1 = "12:00 AM";
		String time2 = "5:00 AM";
		String time3 = "2:00 PM";
		
		//test less than
		assertEquals(-1,test.compareStartTime(time1,time2));
		assertEquals(-1,test.compareStartTime(time1,time3));
		assertEquals(-1,test.compareStartTime(time2,time3));
		
		//test greater than
		assertEquals(1,test.compareStartTime(time2,time1));
		assertEquals(1,test.compareStartTime(time3,time2));
		assertEquals(1,test.compareStartTime(time3,time1));
		
		//test equal
		assertEquals(0,test.compareStartTime(time1,time1));
		
				
	}
	
	@Test
	public void testSortByStartTime() {
		//test if sortByStartTime() is working
		
		//make some test data
		LinkedList<Course> courses = new LinkedList<Course>();
		courses.add(new Course("testCourse1"));
		courses.getLast().setStartTime("3:00 AM");
		
		courses.add(new Course("testCourse2"));
		courses.getLast().setStartTime("2:00 AM");
		
		courses.add(new Course("testCourse3"));
		courses.getLast().setStartTime("1:00 AM");
		
		courses.add(new Course("testCourse4"));
		courses.getLast().setStartTime("4:00 AM");
		
		courses.add(new Course("testCourse5"));
		courses.getLast().setStartTime("2:00 AM");
		
		//sort list
		LinkedList<Course> sortedList;
		sortedList = test.sortByStartTime(courses);
		
		assertEquals("testCourse3",sortedList.get(0).getCourseName());
		
		//these had equal start time
		assertEquals("testCourse5",sortedList.get(1).getCourseName());
		assertEquals("testCourse2",sortedList.get(2).getCourseName());
		
		assertEquals("testCourse1",sortedList.get(3).getCourseName());
		assertEquals("testCourse4",sortedList.get(4).getCourseName());
		
	}
	
	@Test
	public void testSortByDueDate(){
		//test if sortByDueDate() is working
		
		//make some test data
		LinkedList<Assignment> assignment = new LinkedList<Assignment>();
		assignment.add(new Assignment("testAssignment1"));
		assignment.getLast().setDueDate(new Date("Wed Apr 29 03:00:00 EDT 2015"));
		
		assignment.add(new Assignment("testAssignment2"));
		assignment.getLast().setDueDate(new Date("Wed Apr 29 02:00:00 EDT 2015"));
		
		assignment.add(new Assignment("testAssignment3"));
		assignment.getLast().setDueDate(new Date("Wed Apr 29 01:00:00 EDT 2015"));
		
		assignment.add(new Assignment("testAssignment4"));
		assignment.getLast().setDueDate(new Date("Wed Apr 29 04:00:00 EDT 2015"));
		
		//sort list
		LinkedList<Assignment> sortedList;
		sortedList = test.sortByDueDate(assignment);
		
		assertEquals("testAssignment3",sortedList.get(0).getAssignmentName());
		assertEquals("testAssignment2",sortedList.get(1).getAssignmentName());
		assertEquals("testAssignment1",sortedList.get(2).getAssignmentName());
		assertEquals("testAssignment4",sortedList.get(3).getAssignmentName());

	}
	
	@Test
	public void testSearchForCourseName(){
		//test if searchForCourseName() is working
		
		//make some test data
		LinkedList<Course> courses = new LinkedList<Course>();
		courses.add(new Course("testCourse1"));		
		courses.add(new Course("testCourse4"));
		courses.add(new Course("testCourse3"));
		courses.add(new Course("testCourse5"));
		courses.add(new Course("testCourse2"));

		assertEquals(0,test.searchForCourseName(courses, "testCourse1"));
		assertEquals(4,test.searchForCourseName(courses, "testCourse2"));
		assertEquals(2,test.searchForCourseName(courses, "testCourse3"));
		assertEquals(1,test.searchForCourseName(courses, "testCourse4"));
		assertEquals(3,test.searchForCourseName(courses, "testCourse5"));
	}
	
	@Test
	public void testSearchForAssignmentName(){
		//test if searchForAssignmentName() is working
		
		//make some test data
		LinkedList<Assignment> assignments = new LinkedList<Assignment>();
		assignments.add(new Assignment("testAssignment1"));		
		assignments.add(new Assignment("testAssignment4"));
		assignments.add(new Assignment("testAssignment3"));
		assignments.add(new Assignment("testAssignment5"));
		assignments.add(new Assignment("testAssignment2"));

		assertEquals(0,test.searchForAssignmentName(assignments, "testAssignment1"));
		assertEquals(4,test.searchForAssignmentName(assignments, "testAssignment2"));
		assertEquals(2,test.searchForAssignmentName(assignments, "testAssignment3"));
		assertEquals(1,test.searchForAssignmentName(assignments, "testAssignment4"));
		assertEquals(3,test.searchForAssignmentName(assignments, "testAssignment5"));
	}

}
