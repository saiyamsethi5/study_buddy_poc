package JUnitTests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ADT.Course;

public class CourseTest {

	private Course test;
	
	@Before
	public void initialize(){
		//set default values
		test = new Course("testCourse");
		test.setLocation("testLocation");
		test.setStartTime("testStartTime");
		test.setEndTime("testEndTime");
		test.addDay("MONDAY");
		test.addAssignment("testAssignment");
	}
	
	@Test
	public void testGetCourseName() {
		//test if getCourseName() is working
		assertEquals("testCourse",test.getCourseName());
	}
	
	@Test
	public void testSetCourseName() {
		//test if setCourseName() is working
		test.setCourseName("differentName");
		assertEquals("differentName",test.getCourseName());	
	}
	
	@Test
	public void testGetStartName(){
		//test if getStartTime() is working
		assertEquals("testStartTime",test.getStartTime());
	}
	
	@Test
	public void testSetStartName(){
		//test if setStartTime() is working
		test.setStartTime("differentStartTime");
		assertEquals("differentStartTime",test.getStartTime());
	}
	
	@Test
	public void testGetEndName(){
		//test if getEndTime() is working
		assertEquals("testEndTime",test.getEndTime());
	}
	
	@Test
	public void testSetEndName(){
		//test if setEndTime() is working
		test.setEndTime("differentEndTime");
		assertEquals("differentEndTime",test.getEndTime());
	}
	
	@Test
	public void testGetDays(){
		//test if getDays() is working
		assertEquals("MONDAY",test.getDays().get(0));
	}
	
	@Test
	public void testAddDay(){
		//test if addDay() is working
		test.addDay("TUESDAY");
		assertEquals("TUESDAY",test.getDays().getLast());
	}
	
	@Test
	public void testGetLocation(){
		//test if getLocation() is working
		assertEquals("testLocation",test.getLocation());
	}
	@Test
	public void testSetLocation(){
		//test if setLocation() is working
		test.setLocation("differentLocation");
		assertEquals("differentLocation",test.getLocation());
	}
	
	@Test
	public void testGetAssignments(){
		//test if getAssignments() is working
		assertEquals("testAssignment",test.getAssignments().getFirst().getAssignmentName());
	}
	
	@Test
	public void testAddAssignments(){
		//test if addAssignments() is working
		test.addAssignment("differentAssignment");
		assertEquals(2,test.getAssignments().size());
		assertEquals("differentAssignment",test.getAssignments().getLast().getAssignmentName());
	}
	@Test
	public void testCalculateGrade(){
		//test if calculateGrades() is working
		
		assertEquals(0.0,test.calculateGrade(),0);
		
		//set some information to test
		test.getAssignments().get(0).setGrade(80);
		test.getAssignments().get(0).setWeight(60);
		test.addAssignment("testAssignment2");
		test.getAssignments().getLast().setGrade(50);
		test.getAssignments().getLast().setWeight(40);
		
		assertEquals(2.3,test.calculateGrade(),0);
	}
	

}
