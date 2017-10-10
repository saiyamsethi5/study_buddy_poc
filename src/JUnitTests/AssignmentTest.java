package JUnitTests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ADT.Assignment;

public class AssignmentTest {

	private Assignment test ;
	
	@Before
	public void initialize(){
		//set default values
		test = new Assignment("testAssignment");
		test.setDueDate(new Date("Wed Apr 29 04:56:30 EDT 2015"));
		test.setGrade(80);
		test.setWeight(.20);
	}
	
	
	@Test
	public void testGetAssignmentName() {
		//test if getAssignmentName() is working
		assertEquals("testAssignment",test.getAssignmentName());
	}
	
	@Test
	public void testSetAssignmentName() {
		//test if setAssignmentName() is working
		test.setAssignmentName("differentName");
		assertEquals("differentName",test.getAssignmentName());
	}
	
	@Test
	public void testGetDueDate(){
		//test if getDueDate() is working
		assertEquals(new Date("Wed Apr 29 04:56:30 EDT 2015"),test.getDueDate());
	}
	
	@Test
	public void testSetDueDate(){
		//test if setDueDate() is working
		test.setDueDate(new Date("Mon Apr 20 06:57:39 EDT 2015"));
		assertEquals(new Date("Mon Apr 20 06:57:39 EDT 2015"),test.getDueDate());
	}
	
	@Test
	public void testGetGrade(){
		//test if getGrade() is working
		assertEquals(80.0,test.getGrade(),0);
	}
	
	@Test
	public void testSetGrade(){
		//test if setGrade() is working
		test.setGrade(90);
		assertEquals(90.0,test.getGrade(),0);
	}
	
	@Test
	public void testGetWeight(){
		//test if getWeight() is working
		assertEquals(.20,test.getWeight(),0);
	}
	
	@Test
	public void testSetWeight(){
		//test if setWeight() is working
		test.setWeight(.15);
		assertEquals(.15,test.getWeight(),0);
	}
	

}
