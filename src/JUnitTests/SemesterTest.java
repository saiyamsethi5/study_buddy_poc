package JUnitTests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ADT.Semester;

public class SemesterTest {

	Semester test;
	
	@Before
	public void initialize(){
		//set default values
		test = new Semester("testSemester");
		test.setStartDate(new Date("Wed Apr 01 03:05:43 EDT 2015"));
		test.setEndDate(new Date("Wed May 20 17:38:18 EDT 2015"));
		test.addCourse("testCourse");
	}
	
	@Test
	public void testGetSemesterName() {
		//test if getSemesterName is working
		assertEquals("testSemester",test.getSemesterName());
	}
	
	@Test
	public void testSetSemesterName() {
		//test if setSemesterName() is working
		test.setSemesterName("differentSemesterName");
		assertEquals("differentSemesterName",test.getSemesterName());
	}
	
	@Test
	public void testGetStartDate() {
		//test if getStartDate() is working
		assertEquals(new Date("Wed Apr 01 03:05:43 EDT 2015"),test.getStartDate());
	}
	
	@Test
	public void testSetStartDate() {
		//test if setStartDate() is working
		test.setStartDate(new Date("Wed May 06 17:38:18 EDT 2015"));
		assertEquals(new Date("Wed May 06 17:38:18 EDT 2015"), test.getStartDate());
	}
	
	@Test
	public void testGetEndDate() {
		//test if getEndDate() is working
		assertEquals(new Date("Wed May 20 17:38:18 EDT 2015"), test.getEndDate());
	}
	
	@Test
	public void testSetEndDate() {
		//test if setEndDate() is working
		test.setEndDate(new Date("Wed May 06 17:38:18 EDT 2015"));
		assertEquals(new Date("Wed May 06 17:38:18 EDT 2015"),test.getEndDate());
	}
	
	@Test
	public void testGetCourse() {
		//test if getCourse() is working
		assertEquals("testCourse",test.getCourse().getFirst().getCourseName());
	}
	
	@Test
	public void testAddCourse() {
		//test if addCourse() is working
		test.addCourse("testCourse2");
		assertEquals(2,test.getCourse().size());
		assertEquals("testCourse2",test.getCourse().getLast().getCourseName());
	}
	
	@Test
	public void testCalculateGPA() {
		//test if calculateGPA() is working
		assertEquals(0.0,test.calculateGPA(),0);
		
		//set some information to help testing
		test.getCourse().get(0).addAssignment("testAssignment1");
		test.getCourse().get(0).getAssignments().getFirst().setGrade(60);
		test.getCourse().get(0).getAssignments().getFirst().setWeight(50);
		
		test.addCourse("testCourse2");
		test.getCourse().get(1).addAssignment("testAssignment12");
		test.getCourse().get(1).getAssignments().getLast().setGrade(80);
		test.getCourse().get(1).getAssignments().getLast().setWeight(50);
		
		assertEquals(2.7,test.calculateGPA(),0);
		
	}
	
	
	
	
	
	

}
