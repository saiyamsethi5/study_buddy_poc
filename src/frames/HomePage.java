package frames;

//imports all classes used in program
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.CardLayout;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.freixas.jcalendar.*;

import SortAndSearch.Algorithms;
import ADT.Assignment;
import ADT.Course;
import ADT.Semester;

public class HomePage implements ActionListener //implements action listener for jcomponents
{

	private JFrame frame; //creates the main frame
	//creates all the JPanels
	private JPanel panelHome, panelAssignments, panelGrades, panelSearchHome, panelSearchAssignments, panelHelp, panelAddCourse, 
					panelAddAssignment, panelManage, panelRemoveCourse, panelAddSemester, panelRemoveSemester,
					panelManageAssignment, panelEditAssignment;
	//creates all the JLabels
	private JLabel lblUpcoming, lblAssignments, lblGrades, lblSearch, lblDescription, lblSearch1, lblDescription1, lblHelp, 
					lblAddCourse, lblCourseName, lblLocation, lblTime, lblStart, lblEnd, lblDay, lblPercent, lblAddAssignment,
					lblCourse, lblAssignment, lblDueDate, lblWeight, lblManage, lblCurrentDay, lblSemester, lblRemoveCourse, 
					lblCourseName1, lblRemovingACourse, lblThisStepIs, lblAddSemester, lblSemesterName, lblEnd1, lblStart1,
					lblRemoveSemester, lblSemesterToRemove, lblRemovingASemester, lblThisStepIs1, lblSemester_1,
					lblChooseActiveSemester, lblEditAssignment, lblManage2, lblAssignmentName, lblAssignmentName2,
					lblDueDate2, lblWeight2, lblGrade, labelPercent, lblPercent2,
					lblCourseName2;
	//Creates all the JTextFields
	private JTextField txtSearch, txtSearch1, txtCourseName, txtLocation, txtWeight, txtAssignmentField, 
						textFieldSemester, textFieldAssignmentName, txtFieldWeight, txtFieldGrade;
	//Creates all the JTextAreas
	private JTextArea txtInfo,textAreaDisplay, textAreaDisplayAssignments, textAreaDisplayGrades,textAreaSearchAssignment,textAreaSearchHome;
	
	//Creates all the JButtons based on specific panels
	private JButton btnHome, btnAssignments, btnGrades, btnSearch, btnHelp, btnManage; //buttons for home panel
	private JButton btnHome1, btnAssignments1, btnGrades1, btnSearch1, btnManage2; //buttons for assignments panel
	private JButton btnHome2, btnAssignments2, btnGrades2; //buttons for grades panel
	private JButton btnAddSemester, btnRemoveSemester, btnAddCourse, btnRemoveCourse, btnAddAssignment; //buttons for manage panel
	private JButton btnBack, btnEnter, btnBack1, btnEnter1, btnBack2, btnBack3, btnBack4, btnBack5, 
					btnBack6, btnBack7, btnBack8, btnBack9, btnBack10; //enter and back buttons 
	private JButton btnSaveAssignment, btnDeleteAssignment, btnEditAssignment;
	private JButton btnFinish, btnFinish1, btnFinish2, btnFinish3, btnFinish4, btnSelect, btnSelect2,btnSelect3; //finish buttons 
	//Creates all the JComboBox
	private JComboBox comboBoxHoursStart, comboBoxMinutesStart, comboBoxDayStart, comboBoxHoursEnd, comboBoxMinutesEnd, comboBoxDayEnd, 
						comboBoxCourses, comboBoxSemester, comboBoxCourse, comboBoxSemesters, comboBoxSemesterCourseRemove,
						comboBoxActiveSemester, comboBoxAssignments,comboBoxCourses2;
	//Creates all the radio buttons
	private JRadioButton rdbtnMonday, rdbtnTuesday, rdbtnWednesday, rdbtnThursday, rdbtnFriday, rdbtnSaturday, rdbtnSunday;	
	//creates the JCalendars
	private JCalendar calendarPane, calendarPane1, calendarPane2, calendarPane3;
	
	
	private LinkedList<Semester> semesters; //a linked list that holds the semesters
	private Semester activeSemester = null; //Semester variable to hold the active semester
	private String tempAssignmentName; //holds a temporary assignment name 
	
	public HomePage() //Constructor
	{
		//Calls all the initialize functions that initialize each panel
		initializeFrame();
		intializeHomePanel();
		clock();
		initializeAssignmentsPanel();
		initializeGradesPanel();
		initializeSearchHome();
		initializeSearchAssignments();
		initializeHelp();
		initializeAddAssignments();
		initializeManagePanel();
		initializeManageAssignmentPanel();
		initializeAddCourse();
		initializeRemoveCourse();
		initializeAddSemester();
		initializeRemoveSemester();
		initializeEditAssignment();
		initializeActionListeners();
		initializeADT();
		
		try
		{ //sets the active semester that was previously set on close, and sets it on start 
			File dir = new File ("Active Semester" + File.separator + "currentSemester.txt"); //creates the directory for the file
			BufferedReader fr = new BufferedReader(new FileReader(dir)); //uses buffered reader to read the file
			activeSemester = new Semester (fr.readLine()); //sets the active semester as on the line of the file
			
			//refreshes the cmbo boxes to be able to load the active semester
			comboBoxActiveSemester.removeAllItems();  //removes all items from the combo box
			for(int i = 0; i < semesters.size(); i++)
			{
				comboBoxActiveSemester.addItem(semesters.get(i).getSemesterName()); //adds semester names to the box
			}
			
			if(this.activeSemester!=null)
			{ //if active semester is not null, it gets the active semester and sets it to the combo box
				comboBoxActiveSemester.setSelectedItem(activeSemester.getSemesterName());
			}
			
			for(int i = 0; i < semesters.size(); i++)
			{//loops through semester to set active semester
				if(semesters.get(i).getSemesterName().equals(comboBoxActiveSemester.getSelectedItem().toString())){
					activeSemester = semesters.get(i);
				}
			}
		}
		catch (Exception e)
		{
			//do noting if caught any exception
		}
		
		showSchedule(); //cals the show schedule function to display the upcoming classes
		initializeWindowListeners(); //calls windows listeners to perform operate on close fnctions
		
	}
	
	private void initializeADT()
	{	
		File dir; //creates new dir
		String[] entries; //creates array entry
		BufferedReader fr; //creates buffered reader 
		semesters  = new LinkedList<Semester>();  //creates linked list semesters 
		String[] temp; //holds temp
		
		//gets all semesters
		dir = new File("System Files");
		entries = dir.list(); //lists all files in system files

		//adds semester
		for(String s: entries){
			semesters.add(new Semester (s));
		}

		//go through each semester
		for(int i = 0; i < semesters.size(); i++){
			
			//set start and end dates for semester
			try 
			{
				//sets the dir to the file location
				dir = new File("System Files" + File.separator + semesters.get(i).getSemesterName() + File.separator + semesters.get(i).getSemesterName() + ".txt");
				fr = new BufferedReader(new FileReader(dir));

				semesters.get(i).setStartDate(new Date(fr.readLine())); //sets the start date
				semesters.get(i).setEndDate(new Date(fr.readLine())); //sets the end date
				
				fr.close(); //closes the file
			}
			catch (Exception e) 
			{
				//catches any exceptions
			}

			//get all courses
			dir = new File("System Files" + File.separator + semesters.get(i).getSemesterName());
			entries = dir.list(); //stores all files in entries
			try {
				//adds courses and all information
				for(String s: entries){ //stores all entries in s and loops through eac at a time
					if (!s.equals(semesters.get(i).getSemesterName().concat(".txt"))){ //concats .txt
						
						semesters.get(i).addCourse(s.substring(0, s.length()-4)); //formats the course 
						fr = new BufferedReader(new FileReader(dir+ File.separator + s));
						semesters.get(i).getCourse().getLast().setLocation(fr.readLine()); //reads location 
						semesters.get(i).getCourse().getLast().setStartTime(fr.readLine()); //reads start time
						semesters.get(i).getCourse().getLast().setEndTime(fr.readLine()); //reads end time
						
						for(String d: fr.readLine().split(",")){ //stores split in d
							if(d.equals("")){continue;}
							semesters.get(i).getCourse().getLast().addDay(d);
						}
						
						//add assignments
						int assignmentCounter = Integer.parseInt (fr.readLine());
						for(int j = 0; j < assignmentCounter; j++){
							temp = fr.readLine().split(","); //reads split into temp and adds to adt respectivly
							semesters.get(i).getCourse().getLast().addAssignment(temp[0]);
							semesters.get(i).getCourse().getLast().getAssignments().getLast().setDueDate(new Date(temp[1]));
							semesters.get(i).getCourse().getLast().getAssignments().getLast().setGrade(Double.parseDouble(temp[2]));
							semesters.get(i).getCourse().getLast().getAssignments().getLast().setWeight(Double.parseDouble(temp[3]));
						} 
						fr.close(); //closes file
					}
				}
			} 
			catch (FileNotFoundException e) {
				//runs when file not ofund
			} 
			catch (IOException e){
				//catches io exception
			} 
			catch (NullPointerException e){
				//catches nill pointer
			}
		}
	}

	private void saveInformation(){
		//saves all information in System Files
		PrintWriter writer; //creates printwriter to write file
		Semester currentSemester; //creates current semester 
		Course currentCourse; //creates current course
		Assignment currentAssignment; //create current assignment 
		File dir; //creates file to hold directoy
	
		
		//TODO BUG NOT DELETING FILES -> REMOVING SEMESTERS AND COURSES WILL NOT SAVE
		//delete information first
		dir = new File("System Files"); //creates location of directory 
		File toDelete; //creates new variable where file to be deleted will be help
	
		for (String semesterFiles: dir.list())
		{//loops through all files and stores in semesterFiles
			dir = new File ("System Files" + File.separator + semesterFiles); //creates directory
			//delete everything in the dir
			for (String fileName: dir.list())
			{//holds all items in fileName
				toDelete = new File (dir.getPath(),fileName);
				toDelete.delete(); //deletes the file
			} 
			dir.delete(); //delets the dir
		}
		
		 //make main file
		dir = new File("System Files");
		dir.mkdir(); //makes the directory
		
		//loop though semesters
		for(int i = 0; i < semesters.size(); i++)
		{
			//refresh parent dir
			dir = new File("System Files");
			currentSemester = semesters.get(i); //gets the current semester
			
			//make semester folder
			dir = new File(dir,currentSemester.getSemesterName());
			dir.mkdir(); //makes the directory
			
			try
			{
				//print semester info
				writer = new PrintWriter(dir + File.separator + currentSemester.getSemesterName() + ".txt");
				writer.println(currentSemester.getStartDate()); //prints start date
				writer.println(currentSemester.getEndDate()); //prints end date
				writer.close(); //closes the writer
				
				//loop through this semesters courses
				for (int j = 0; j < currentSemester.getCourse().size(); j++)
				{
					currentCourse = currentSemester.getCourse().get(j); //gets the current course
	
					//make text files for each course
					writer = new PrintWriter(dir + File.separator + currentCourse.getCourseName() + ".txt");
					
					//print course info
					writer.println(currentCourse.getLocation()); //prints location 
					writer.println(currentCourse.getStartTime()); //prints start time 
					writer.println(currentCourse.getEndTime()); //prints end time 
					for(int k = 0; k < currentCourse.getDays().size(); k++)
					{
						writer.print(","+ currentCourse.getDays().get(k)); //prints a comma for the days
					}
					writer.println(); //prints a new line
					writer.println(currentCourse.getAssignments().size()); //prints number of assignments
					//loop through assignments
					for(int k = 0; k < currentCourse.getAssignments().size();k++)
					{
						currentAssignment = currentCourse.getAssignments().get(k); //gets assignments
						
						//print assignment info
						writer.print(currentAssignment.getAssignmentName()); //prints assignment name
						writer.print(","+currentAssignment.getDueDate()); //prints due date
						writer.print(","+currentAssignment.getGrade()); //prints grade
						writer.println(","+currentAssignment.getWeight()); //prints the weight
					}
					writer.close(); // closes the writer
				}	
			}
			catch(Exception e){
				//catches an exception
			}
		}
	}
	
	private void initializeFrame()
	{
		//create sthe frame and sets all of its operations
		frame = new JFrame();
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 456, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));	
	}
	
	private void clock()
	{ //clock function to create live clock for home page
		Thread clock = new Thread() //creates a new thread to refres the clock
		{
			public void run()
			{//run function that runs the thread
				try 
				{
					for(;;) //empty for loop
					{
						//creates new date and format
						DateFormat dateFormat = new SimpleDateFormat("EEE MMMM dd yyyy   HH:mm:ss");
						Date date = new Date();
						
						lblCurrentDay.setText(dateFormat.format(date)); //sets the label to active clock
					
						sleep(1000); //resets timer
					}
				} catch (InterruptedException e) 
				{
					//catches exception if interuption
				}
			}
		};
		
		clock.start(); //starts the clock once again
	}
	
	private void intializeHomePanel()
	{//creates all components on the home panel
		panelHome = new JPanel(); //creates jpanel and components
		panelHome.setBackground(Color.BLACK);
		frame.getContentPane().add(panelHome, "name_885469656387360");
		panelHome.setLayout(null);
		
		btnHome = new JButton("HOME"); //creates home button and sets components
		btnHome.setBackground(Color.YELLOW);
		btnHome.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnHome.setBounds(0, 425, 152, 40);
		panelHome.add(btnHome);
		
		btnAssignments = new JButton("ASSIGNMENTS"); //creates assignment button and sets components
		btnAssignments.setBackground(Color.YELLOW);
		btnAssignments.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAssignments.setBounds(147, 425, 146, 40);
		panelHome.add(btnAssignments);
		
		btnGrades = new JButton("GRADES"); //creates grade button and sets components
		btnGrades.setBackground(Color.YELLOW);
		btnGrades.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnGrades.setBounds(288, 425, 152, 40);
		panelHome.add(btnGrades);
		
		btnSearch = new JButton("SEARCH"); //creates search button and sets components
		btnSearch.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 0, 102));
		btnSearch.setBounds(0, 11, 152, 23);
		btnSearch.addActionListener(this);
		panelHome.add(btnSearch);
		
		btnHelp = new JButton("HELP"); //creates help button and sets components
		btnHelp.setForeground(new Color(255, 255, 255));
		btnHelp.setBackground(new Color(0, 0, 102));
		btnHelp.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnHelp.setBounds(147, 11, 146, 23);
		btnHelp.addActionListener(this);
		panelHome.add(btnHelp);
		
		btnManage = new JButton("MANAGE"); //creates manage button and sets components
		btnManage.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnManage.setForeground(new Color(255, 255, 255));
		btnManage.setBackground(new Color(0, 0, 102));
		btnManage.setBounds(288, 11, 152, 23);
		btnManage.addActionListener(this);
		panelHome.add(btnManage);
		
		lblUpcoming = new JLabel("UPCOMING TODAY"); //creates label and sets components
		lblUpcoming.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblUpcoming.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpcoming.setForeground(Color.WHITE);
		lblUpcoming.setBounds(10, 36, 421, 50);
		panelHome.add(lblUpcoming);
		
		lblCurrentDay = new JLabel ("Clock"); //creates label for clock and and sets components
		lblCurrentDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentDay.setForeground(new Color(255, 255, 255));
		lblCurrentDay.setBackground(new Color(0, 0, 0));
		lblCurrentDay.setBounds(10, 76, 420, 14);
		panelHome.add(lblCurrentDay);
		
		textAreaDisplay = new JTextArea(); //creates new text area to update courses on
		textAreaDisplay.setEditable(false);
		textAreaDisplay.setForeground(Color.WHITE);
		textAreaDisplay.setBackground(Color.BLACK);
		int fontPoints = 15;
		textAreaDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontPoints ));
		textAreaDisplay.setBounds(10, 97, 421, 317);
		panelHome.add(textAreaDisplay);
		
		
		JScrollPane scrollPaneDisplay = new JScrollPane(textAreaDisplay); //adds scrolling ability to the text area
		scrollPaneDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDisplay.setBounds(10, 97, 421, 317);
		panelHome.add(scrollPaneDisplay);
		
	}
	
	private void initializeAssignmentsPanel ()
	{//initializes the assignments tab and all its componenets
		panelAssignments = new JPanel(); //creates new jpanel and sets components
		panelAssignments.setBackground(Color.BLACK);
		frame.getContentPane().add(panelAssignments, "name_887414573009179");
		panelAssignments.setLayout(null);
		
		btnHome1 = new JButton("HOME"); //creates new home button and sets components
		btnHome1.setBackground(Color.YELLOW);
		btnHome1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnHome1.setBounds(0, 425, 152, 40);
		panelAssignments.add(btnHome1);
		
		btnAssignments1 = new JButton("ASSIGNMENTS"); //creates new asssignemtn button and sets components
		btnAssignments1.setBackground(Color.YELLOW);
		btnAssignments1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAssignments1.setBounds(147, 425, 146, 40);
		panelAssignments.add(btnAssignments1);
		
		btnGrades1 = new JButton("GRADES"); //creates new grades button and sets components
		btnGrades1.setBackground(Color.YELLOW);
		btnGrades1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnGrades1.setBounds(288, 425, 152, 40);
		panelAssignments.add(btnGrades1);
		
		btnSearch1 = new JButton("SEARCH"); //creates new search button and sets components
		btnSearch1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnSearch1.setForeground(new Color(255, 255, 255));
		btnSearch1.setBackground(new Color(0, 0, 102));
		btnSearch1.setBounds(0, 11, 217, 23);
		btnSearch1.addActionListener(this);
		panelAssignments.add(btnSearch1);
		
		btnManage2 = new JButton("MANAGE"); //creates new manage button and sets components
		btnManage2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnManage2.setForeground(new Color(255, 255, 255));
		btnManage2.setBackground(new Color(0, 0, 102));
		btnManage2.setBounds(215, 11, 225, 23);
		btnManage2.addActionListener(this);
		panelAssignments.add(btnManage2);
		
		lblAssignments = new JLabel("ASSIGNMENTS"); //creates new label and sets components
		lblAssignments.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignments.setForeground(Color.WHITE);
		lblAssignments.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblAssignments.setBounds(10, 36, 421, 50);
		panelAssignments.add(lblAssignments);
		
		textAreaDisplayAssignments = new JTextArea(); //creates new jtext area to display all info
		textAreaDisplayAssignments.setEditable(false);
		textAreaDisplayAssignments.setForeground(Color.WHITE);
		textAreaDisplayAssignments.setBackground(Color.BLACK);
		int fontPoints = 15;
		textAreaDisplayAssignments.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontPoints ));
		textAreaDisplayAssignments.setBounds(10, 97, 421, 317);
		panelAssignments.add(textAreaDisplayAssignments);
		
		JScrollPane scrollPaneDisplay = new JScrollPane(textAreaDisplayAssignments); //adds scrolling to the jtext area
		scrollPaneDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDisplay.setBounds(10, 97, 421, 317);
		panelAssignments.add(scrollPaneDisplay);	
	}
	
	private void initializeGradesPanel ()
	{//initializes the grades tab
		panelGrades = new JPanel(); //creates new grades panel and sets components
		panelGrades.setBackground(Color.BLACK);
		frame.getContentPane().add(panelGrades, "name_887882990244141");
		panelGrades.setLayout(null);
		
		btnHome2 = new JButton("HOME"); //creates home button and sets components
		btnHome2.setBackground(Color.YELLOW);
		btnHome2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnHome2.setBounds(0, 425, 152, 40);
		panelGrades.add(btnHome2);
		
		btnAssignments2 = new JButton("ASSIGNMENTS"); //creates assignment button and sets components
		btnAssignments2.setBackground(Color.YELLOW);
		btnAssignments2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAssignments2.setBounds(147, 425, 146, 40);
		panelGrades.add(btnAssignments2);
		
		btnGrades2 = new JButton("GRADES"); //creates grades and sets components
		btnGrades2.setBackground(Color.YELLOW);
		btnGrades2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnGrades2.setBounds(288, 425, 152, 40);
		panelGrades.add(btnGrades2);
		
		lblGrades = new JLabel("GRADES"); //creats grades label and sets components
		lblGrades.setVerticalAlignment(SwingConstants.CENTER);
		lblGrades.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrades.setForeground(Color.WHITE);
		lblGrades.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblGrades.setBounds(10, 15, 421, 50);
		panelGrades.add(lblGrades);
		
		textAreaDisplayGrades = new JTextArea(); //creates new jtext area to display info
		textAreaDisplayGrades.setEditable(false);
		textAreaDisplayGrades.setForeground(Color.WHITE);
		textAreaDisplayGrades.setBackground(Color.BLACK);
		int fontPoints = 15;
		textAreaDisplayGrades.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontPoints ));
		textAreaDisplayGrades.setBounds(10, 97, 421, 317);
		panelGrades.add(textAreaDisplayGrades);
		
		JScrollPane scrollPaneDisplay = new JScrollPane(textAreaDisplayGrades); //adds scrolling ability to the jtextarea
		scrollPaneDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDisplay.setBounds(10, 97, 421, 317);
		panelGrades.add(scrollPaneDisplay);
	}
	
	private void initializeSearchAssignments ()
	{//initializes the search assignments page
		panelSearchAssignments = new JPanel(); //creates new panel and sets components
		panelSearchAssignments.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(panelSearchAssignments, "name_904775278225363");
		panelSearchAssignments.setLayout(null);
		
		lblSearch1 = new JLabel("SEARCH"); //creates new label and sets components
		lblSearch1.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblSearch1.setForeground(new Color(255, 255, 255));
		lblSearch1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch1.setBounds(10, 15, 421, 50);
		panelSearchAssignments.add(lblSearch1);
		
		lblDescription1 = new JLabel("SEARCH FOR AN ASSIGNMENT"); //creates new label and sets components
		lblDescription1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription1.setForeground(new Color(255, 255, 255));
		lblDescription1.setBounds(0, 51, 440, 14);
		panelSearchAssignments.add(lblDescription1);
		
		txtSearch1 = new JTextField(); //creates new text field and sets components
		txtSearch1.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch1.setText("ENTER ASSIGNMENT NAME HERE");
		txtSearch1.setBounds(10, 75, 330, 31);
		panelSearchAssignments.add(txtSearch1);
		txtSearch1.setColumns(10);
		
		btnBack1 = new JButton("BACK"); //creates new jbutton and sets components
		btnBack1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack1.setForeground(new Color(255, 255, 255));
		btnBack1.setBackground(new Color(0, 0, 102));
		btnBack1.setBounds(10, 37, 89, 23);
		panelSearchAssignments.add(btnBack1);
		
		btnEnter1 = new JButton("ENTER"); //creates enter button and sets components
		btnEnter1.setBackground(Color.YELLOW);
		btnEnter1.setForeground(Color.BLACK);
		btnEnter1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnEnter1.setBounds(350, 76, 80, 30);
		panelSearchAssignments.add(btnEnter1);
		
		textAreaSearchAssignment = new JTextArea(); //creates text area to display info
		textAreaSearchAssignment.setForeground(Color.WHITE);
		textAreaSearchAssignment.setBackground(Color.BLACK);
		int fontPoints = 12;
		textAreaSearchAssignment.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontPoints ));
		textAreaSearchAssignment.setBounds(10, 117, 421, 348);
		panelSearchAssignments.add(textAreaSearchAssignment);
		
		JScrollPane scrollPaneDisplay = new JScrollPane(textAreaSearchAssignment); //creates scrolling ability on text area
		scrollPaneDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDisplay.setBounds(10, 117, 421, 348);
		panelSearchAssignments.add(scrollPaneDisplay);
	}
	
	public void initializeSearchHome ()
	{//initializes search on home page 
		panelSearchHome = new JPanel(); //creates panel and sets components
		panelSearchHome.setBackground(Color.BLACK);
		frame.getContentPane().add(panelSearchHome, "name_901202209743363");
		panelSearchHome.setLayout(null);
		
		lblSearch = new JLabel("SEARCH"); //creates new label and sets components
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblSearch.setBounds(10, 15, 421, 50);
		panelSearchHome.add(lblSearch);
		
		lblDescription = new JLabel("SEARCH FOR A COURSE"); //creates a new label and sets components
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setBounds(0, 52, 440, 14);
		panelSearchHome.add(lblDescription);
		
		txtSearch = new JTextField(); //creates a new text field and sets components
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setText("ENTER COURSE NAME HERE");
		txtSearch.setBounds(10, 75, 330, 31);
		panelSearchHome.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnBack = new JButton("BACK"); //creates a button to go back and sets components
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack.setBounds(10, 37, 89, 23);
		panelSearchHome.add(btnBack);
		
		btnEnter = new JButton("ENTER"); //creates an enter button and sets components
		btnEnter.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 11));
		btnEnter.setBackground(Color.YELLOW);
		btnEnter.setBounds(350, 76, 80, 30);
		panelSearchHome.add(btnEnter);
		
		textAreaSearchHome = new JTextArea(); //creates a text area to display info
		textAreaSearchHome.setForeground(Color.WHITE);
		textAreaSearchHome.setBackground(Color.BLACK);
		int fontPoints = 12;
		textAreaSearchHome.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontPoints ));
		textAreaSearchHome.setBounds(10, 117, 421, 348);
		panelSearchHome.add(textAreaSearchHome);
		
		JScrollPane scrollPaneDisplay = new JScrollPane(textAreaSearchHome); //adds scrolling on text area
		scrollPaneDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDisplay.setBounds(10, 117, 421, 348);
		panelSearchHome.add(scrollPaneDisplay);
	}
	
	private void initializeHelp ()
	{
		panelHelp = new JPanel(); //creates new panel and sets components
		panelHelp.setBackground(Color.BLACK);
		frame.getContentPane().add(panelHelp, "name_905897440369314");
		panelHelp.setLayout(null);
		
		lblHelp = new JLabel("HELP"); //creates new label and sets componentss
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblHelp.setForeground(Color.WHITE);
		lblHelp.setBounds(10, 11, 420, 32);
		panelHelp.add(lblHelp);
		
		txtInfo = new JTextArea(); //creates new text area to display info
		txtInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtInfo.setText("Study Buddy is an application that manages all of\r\nyour stress and problems\r\n\r\nTo start using Study Buddy, you must first begin\r\nby adding a semester. Once completed, Add all\r\nyour courses within the semester and let \r\nStudy Buddy worry about the rest.\r\n\r\nYou will receive daily notifications on the home\r\npage regarding your upcoming classes. \r\n\r\nIn the assignment tab, you can add assignments \r\nfor each course and assign grades and due dates \r\nfor each individual assignment.\r\n\r\nIn the grades tab, take a look at how you are\r\ndoing in the semester by looking at your course\r\ngrades for each individual course. You can also\r\nview your GPA in the 4.0 scale.");
		txtInfo.setForeground(new Color(255, 255, 255));
		txtInfo.setBackground(Color.BLACK);
		txtInfo.setBounds(10, 65, 420, 400);
		panelHelp.add(txtInfo);
		
		btnBack2 = new JButton("BACK"); //creates back button to go back and sets components
		btnBack2.setBackground(new Color(0, 0, 102));
		btnBack2.setForeground(Color.WHITE);
		btnBack2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack2.setBounds(10, 15, 89, 23);
		panelHelp.add(btnBack2);
	}
	
	private void initializeAddCourse ()
	{		//creates the add course panel
		panelAddCourse = new JPanel(); //creates panel and sets components
		panelAddCourse.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(panelAddCourse, "name_909262111329772");
		panelAddCourse.setLayout(null);
		
		lblAddCourse = new JLabel("ADD A COURSE"); //creates a new label and sets components
		lblAddCourse.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblAddCourse.setForeground(new Color(255, 255, 255));
		lblAddCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCourse.setBounds(0, 11, 440, 32);
		panelAddCourse.add(lblAddCourse);
		
		btnBack3 = new JButton("BACK"); //creates a new button and sets components
		btnBack3.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack3.setForeground(new Color(255, 255, 255));
		btnBack3.setBackground(new Color(0, 0, 102));
		btnBack3.setBounds(10, 16, 89, 23);
		panelAddCourse.add(btnBack3);
		
		lblCourseName = new JLabel("COURSE NAME:"); //creates a new label and sets components
		lblCourseName.setForeground(new Color(255, 255, 255));
		lblCourseName.setBounds(10, 124, 89, 23);
		panelAddCourse.add(lblCourseName);
		
		txtCourseName = new JTextField(8); //creates a new text field to hold course name
		txtCourseName.setBounds(10, 145, 420, 20);
		panelAddCourse.add(txtCourseName);
		txtCourseName.setColumns(10);
		
		lblLocation = new JLabel("LOCATION:"); //creats new label and sets components
		lblLocation.setForeground(new Color(255, 255, 255));
		lblLocation.setBounds(10, 174, 74, 23);
		panelAddCourse.add(lblLocation);
		
		txtLocation = new JTextField(); //creates new txt box to hold location
		txtLocation.setBounds(10, 195, 420, 20);
		panelAddCourse.add(txtLocation);
		txtLocation.setColumns(10);
		
		lblTime = new JLabel("TIME:"); //creates new label and sets components
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setBounds(10, 226, 46, 14);
		panelAddCourse.add(lblTime);
		
		lblStart = new JLabel("START:"); //creates start label and sets components
		lblStart.setForeground(new Color(255, 255, 255));
		lblStart.setBounds(10, 245, 46, 14);
		panelAddCourse.add(lblStart);
		
		comboBoxHoursStart = new JComboBox(); //creates new combo box to hold hours
		comboBoxHoursStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxHoursStart.setMaximumRowCount(12);
		comboBoxHoursStart.setBounds(55, 242, 44, 20);
		panelAddCourse.add(comboBoxHoursStart);
		
		comboBoxMinutesStart = new JComboBox(); //creates new combo box to hold minutes
		comboBoxMinutesStart.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMinutesStart.setMaximumRowCount(12);
		comboBoxMinutesStart.setBounds(109, 242, 52, 20);
		panelAddCourse.add(comboBoxMinutesStart);
		
		comboBoxDayStart = new JComboBox(); //creates new combo box to hold am or pm
		comboBoxDayStart.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		comboBoxDayStart.setMaximumRowCount(2);
		comboBoxDayStart.setBounds(171, 242, 46, 20);
		panelAddCourse.add(comboBoxDayStart);
		
		lblEnd = new JLabel("END:"); //creates new label
		lblEnd.setForeground(new Color(255, 255, 255));
		lblEnd.setBounds(10, 277, 38, 14);
		panelAddCourse.add(lblEnd);
		
		comboBoxHoursEnd = new JComboBox(); //box to hold end hours
		comboBoxHoursEnd.setMaximumRowCount(12);
		comboBoxHoursEnd.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxHoursEnd.setBounds(55, 274, 44, 20);
		panelAddCourse.add(comboBoxHoursEnd);
		
		comboBoxMinutesEnd = new JComboBox(); //box to hold end minutes
		comboBoxMinutesEnd.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMinutesEnd.setMaximumRowCount(12);
		comboBoxMinutesEnd.setBounds(109, 274, 52, 20);
		panelAddCourse.add(comboBoxMinutesEnd);
		
		comboBoxDayEnd = new JComboBox(); //box to hold day
		comboBoxDayEnd.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		comboBoxDayEnd.setMaximumRowCount(2);
		comboBoxDayEnd.setBounds(171, 274, 46, 20);
		panelAddCourse.add(comboBoxDayEnd);
		
		lblDay = new JLabel("DAY:"); //creates new label and sets components
		lblDay.setForeground(new Color(255, 255, 255));
		lblDay.setBounds(10, 315, 46, 14);
		panelAddCourse.add(lblDay);
		
		rdbtnMonday = new JRadioButton("MONDAY"); //creates check box for monday
		rdbtnMonday.setBounds(10, 336, 109, 23);
		panelAddCourse.add(rdbtnMonday);
		
		rdbtnTuesday = new JRadioButton("TUESDAY"); //creates check box for tuesday
		rdbtnTuesday.setBounds(10, 362, 109, 23);
		panelAddCourse.add(rdbtnTuesday);
		
		rdbtnWednesday = new JRadioButton("WEDNESDAY"); //creates check box for wendesday
		rdbtnWednesday.setBounds(10, 388, 109, 23);
		panelAddCourse.add(rdbtnWednesday);
		
		rdbtnThursday = new JRadioButton("THURSDAY"); //creates check box for thursday
		rdbtnThursday.setBounds(10, 414, 109, 23);
		panelAddCourse.add(rdbtnThursday);
		
		rdbtnFriday = new JRadioButton("FRIDAY"); //creates check box for friday
		rdbtnFriday.setBounds(151, 336, 109, 23);
		panelAddCourse.add(rdbtnFriday);
		
		rdbtnSaturday = new JRadioButton("SATURDAY"); //creates check box for saturday
		rdbtnSaturday.setBounds(151, 362, 109, 23);
		panelAddCourse.add(rdbtnSaturday);
		
		rdbtnSunday = new JRadioButton("SUNDAY"); //creates check box for sunday
		rdbtnSunday.setBounds(151, 388, 109, 23);
		panelAddCourse.add(rdbtnSunday);
		
		btnFinish = new JButton("FINISH"); //creates a finish button and sets components
		btnFinish.setBackground(Color.YELLOW);
		btnFinish.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnFinish.setBounds(330, 433, 100, 32);
		panelAddCourse.add(btnFinish);
		
		lblSemester = new JLabel("SEMESTER:"); //creates a new label semester and sets components
		lblSemester.setForeground(Color.WHITE);
		lblSemester.setBounds(10, 75, 130, 23);
		panelAddCourse.add(lblSemester);
		
		comboBoxSemester = new JComboBox(); //creates a new combo box to hold semester names
		comboBoxSemester.setBounds(10, 99, 420, 20);
		panelAddCourse.add(comboBoxSemester);
	}
	
	private void initializeAddAssignments ()
	{//initialize the add assignments panel
		panelAddAssignment = new JPanel(); //creates ne wjpanel and sets components
		panelAddAssignment.setBackground(Color.BLACK);
		frame.getContentPane().add(panelAddAssignment, "name_911762583958302");
		panelAddAssignment.setLayout(null);
		
		lblAddAssignment = new JLabel("ADD AN ASSIGNMENT"); //creates new label and sets components
		lblAddAssignment.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblAddAssignment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAssignment.setForeground(Color.WHITE);
		lblAddAssignment.setBackground(Color.BLACK);
		lblAddAssignment.setBounds(0, 11, 440, 37);
		panelAddAssignment.add(lblAddAssignment);
		
		btnBack4 = new JButton("BACK"); //creates button to go back to prev panel
		btnBack4.setForeground(new Color(255, 255, 255));
		btnBack4.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack4.setBackground(new Color(0, 0, 153));
		btnBack4.setBounds(10, 18, 72, 23);
		panelAddAssignment.add(btnBack4);
		
		lblCourse = new JLabel("COURSE:"); //creates new label and sets components
		lblCourse.setForeground(new Color(255, 255, 255));
		lblCourse.setBounds(10, 59, 82, 14);
		panelAddAssignment.add(lblCourse);
		
		comboBoxCourses = new JComboBox(); //creates new combo box to hold courses
		comboBoxCourses.setBounds(10, 72, 420, 20);
		panelAddAssignment.add(comboBoxCourses);
		
		lblAssignment = new JLabel("ASSIGNMENT NAME:"); //creates new label and sets components
		lblAssignment.setForeground(new Color(255, 255, 255));
		lblAssignment.setBounds(10, 103, 142, 14);
		panelAddAssignment.add(lblAssignment);
		
		txtAssignmentField = new JTextField(); //creates text field to get assignment name
		txtAssignmentField.setBounds(10, 117, 420, 20);
		panelAddAssignment.add(txtAssignmentField);
		txtAssignmentField.setColumns(10);
		
		lblDueDate = new JLabel("DUE DATE:");//creates new label and sets components
		lblDueDate.setForeground(new Color(255, 255, 255));
		lblDueDate.setBounds(10, 148, 99, 14);
		panelAddAssignment.add(lblDueDate);
		
		calendarPane = new JCalendar(); //creates a new calender for due date
		calendarPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		calendarPane.setForeground(Color.WHITE);
		calendarPane.setBackground(Color.BLACK);
		calendarPane.setBounds(10, 166, 420, 153);
		panelAddAssignment.add(calendarPane);
		
		lblWeight = new JLabel("WEIGHT:"); //creates new label and sets components
		lblWeight.setForeground(Color.WHITE);
		lblWeight.setBackground(Color.BLACK);
		lblWeight.setBounds(10, 330, 72, 14);
		panelAddAssignment.add(lblWeight);
		
		txtWeight = new JTextField(); //creates new text box to enter weight
		txtWeight.setBounds(10, 346, 86, 20);
		panelAddAssignment.add(txtWeight);
		txtWeight.setColumns(10);
		
		lblPercent = new JLabel("%"); //creates new label and sets components
		lblPercent.setForeground(Color.WHITE);
		lblPercent.setBounds(106, 349, 46, 14);
		panelAddAssignment.add(lblPercent);
		
		btnFinish1 = new JButton("FINISH"); //creates finish button to store info
		btnFinish1.setBackground(Color.YELLOW);
		btnFinish1.setBounds(341, 442, 89, 23);
		panelAddAssignment.add(btnFinish1);
	}
	
	private void initializeManagePanel ()
	{
		panelManage = new JPanel(); //create panel and sets components
		panelManage.setBackground(Color.BLACK);
		frame.getContentPane().add(panelManage, "name_997001733476132");
		panelManage.setLayout(null);
		
		btnAddSemester = new JButton("ADD SEMESTER"); //creates button to add semester
		btnAddSemester.setForeground(Color.BLACK);
		btnAddSemester.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAddSemester.setBackground(Color.YELLOW);
		btnAddSemester.setBounds(10, 161, 420, 35);
		panelManage.add(btnAddSemester);
		
		lblManage = new JLabel("MANAGE"); //creates new label and sets components
		lblManage.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblManage.setForeground(Color.WHITE);
		lblManage.setHorizontalAlignment(SwingConstants.CENTER);
		lblManage.setBackground(Color.BLACK);
		lblManage.setBounds(0, 13, 440, 51);
		panelManage.add(lblManage);
		
		btnRemoveSemester = new JButton("REMOVE SEMESTER"); //creates button to remove semester
		btnRemoveSemester.setBackground(Color.YELLOW);
		btnRemoveSemester.setForeground(Color.BLACK);
		btnRemoveSemester.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnRemoveSemester.setBounds(10, 207, 420, 35);
		panelManage.add(btnRemoveSemester);
		
		btnAddCourse = new JButton("ADD COURSE"); //creates button to add course
		btnAddCourse.setBackground(Color.YELLOW);
		btnAddCourse.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAddCourse.setBounds(10, 279, 420, 35);
		panelManage.add(btnAddCourse);
		
		btnRemoveCourse = new JButton("REMOVE COURSE"); //creates button to remove course
		btnRemoveCourse.setBackground(Color.YELLOW);
		btnRemoveCourse.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnRemoveCourse.setBounds(10, 323, 420, 35);
		panelManage.add(btnRemoveCourse);
		
		btnBack5 = new JButton("BACK"); //creates back button to go back to prev panel
		btnBack5.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack5.setBackground(new Color(0, 0, 102));
		btnBack5.setForeground(Color.WHITE);
		btnBack5.setBounds(23, 25, 89, 30);
		panelManage.add(btnBack5);
		
		lblChooseActiveSemester = new JLabel("CHOOSE ACTIVE SEMESTER"); //creates new label and and sets components
		lblChooseActiveSemester.setForeground(Color.WHITE);
		lblChooseActiveSemester.setBounds(10, 391, 313, 14);
		panelManage.add(lblChooseActiveSemester);
				
		comboBoxActiveSemester = new JComboBox(); //cretes new combo box to hold active semester
		comboBoxActiveSemester.setBounds(10, 411, 420, 20);
		panelManage.add(comboBoxActiveSemester);
	}
	
	private void initializeManageAssignmentPanel ()
	{
		panelManageAssignment = new JPanel(); //creates panel and sets components
		panelManageAssignment.setBackground(Color.BLACK);
		frame.getContentPane().add(panelManageAssignment, "name_334125947236073");
		panelManageAssignment.setLayout(null);
		
		lblManage2 = new JLabel("MANAGE"); //creates new label and sets components
		lblManage2.setHorizontalAlignment(SwingConstants.CENTER);
		lblManage2.setForeground(Color.WHITE);
		lblManage2.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblManage2.setBounds(10, 11, 420, 32);
		panelManageAssignment.add(lblManage2);
		
		btnBack9 = new JButton("BACK"); //creats button to go back to prev panel
		btnBack9.setBackground(new Color(0, 0, 102));
		btnBack9.setForeground(Color.WHITE);
		btnBack9.setBounds(10, 17, 89, 23);
		panelManageAssignment.add(btnBack9);
		
		btnAddAssignment = new JButton("ADD AN ASSIGNMENT"); //creates button to add an assignment
		btnAddAssignment.setBackground(Color.YELLOW);
		btnAddAssignment.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnAddAssignment.setBounds(10, 134, 420, 41);
		panelManageAssignment.add(btnAddAssignment);
		
		btnEditAssignment = new JButton("EDIT ASSIGNMENTS"); //creates button to edit an assignment
		btnEditAssignment.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnEditAssignment.setBackground(Color.YELLOW);
		btnEditAssignment.setForeground(Color.BLACK);
		btnEditAssignment.setBounds(10, 186, 420, 41);
		panelManageAssignment.add(btnEditAssignment);
	}
	private void initializeRemoveCourse ()
	{//creats panel to remove course
		panelRemoveCourse = new JPanel(); //creates panel and sets components
		panelRemoveCourse.setBackground(Color.BLACK);
		frame.getContentPane().add(panelRemoveCourse, "name_1000895401067423");
		panelRemoveCourse.setLayout(null);
		
		lblRemoveCourse = new JLabel("REMOVE COURSE"); //creates label and sets components
		lblRemoveCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveCourse.setForeground(Color.WHITE);
		lblRemoveCourse.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblRemoveCourse.setBounds(10, 11, 420, 32);
		panelRemoveCourse.add(lblRemoveCourse);
		
		btnBack6 = new JButton("BACK"); //creates button to go to prev panel
		btnBack6.setForeground(Color.WHITE);
		btnBack6.setBackground(new Color(0, 0, 102));
		btnBack6.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack6.setBounds(10, 16, 89, 23);
		panelRemoveCourse.add(btnBack6);
		
		lblCourseName1 = new JLabel("COURSE TO REMOVE"); //creates new label and sets components
		lblCourseName1.setForeground(new Color(255, 255, 255));
		lblCourseName1.setBounds(10, 112, 148, 14);
		panelRemoveCourse.add(lblCourseName1);
		
		comboBoxSemesterCourseRemove = new JComboBox(); //creates new combo box to hold semester name
		comboBoxSemesterCourseRemove.setBounds(10, 70, 308, 20);
		panelRemoveCourse.add(comboBoxSemesterCourseRemove);
		
		comboBoxCourse = new JComboBox(); //creates combo box to hold course name
		comboBoxCourse.setBounds(10, 130, 420, 20);
		panelRemoveCourse.add(comboBoxCourse);
		
		//creates new label and and sets components
		lblRemovingACourse = new JLabel("REMOVING A COURSE WILL REMOVE ALL THINGS ASSOCIATED WITH THAT COURSE");
		lblRemovingACourse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRemovingACourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemovingACourse.setForeground(new Color(255, 255, 255));
		lblRemovingACourse.setBounds(10, 341, 420, 14);
		panelRemoveCourse.add(lblRemovingACourse);
		
		lblThisStepIs = new JLabel("THIS STEP IS NOT REVERSIBLE!!!"); //creates new label and sets components
		lblThisStepIs.setForeground(new Color(255, 255, 255));
		lblThisStepIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisStepIs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThisStepIs.setBounds(10, 357, 420, 14);
		panelRemoveCourse.add(lblThisStepIs);
		
		btnFinish2 = new JButton("FINISH"); //creates finish button to perform operations
		btnFinish2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnFinish2.setBackground(Color.YELLOW);
		btnFinish2.setBounds(341, 442, 89, 23);
		panelRemoveCourse.add(btnFinish2);
		
		lblSemester_1 = new JLabel("SEMESTER:"); //creates label and sets components
		lblSemester_1.setForeground(Color.WHITE);
		lblSemester_1.setBounds(10, 54, 148, 14);
		panelRemoveCourse.add(lblSemester_1);
		
		btnSelect = new JButton ("SELECT"); //creates a button to select from combo box
		btnSelect.setBackground(Color.YELLOW);
		btnSelect.setFont(new Font ("Franklin Gothic Demi", Font.PLAIN, 12));
		btnSelect.setBounds(328,69,102,23);
		panelRemoveCourse.add(btnSelect);
	}
	
	private void initializeAddSemester()
	{
		panelAddSemester = new JPanel(); //creates new panel and sets components
		panelAddSemester.setBackground(Color.BLACK);
		frame.getContentPane().add(panelAddSemester, "name_1001913723213783");
		panelAddSemester.setLayout(null);
		
		lblAddSemester = new JLabel("ADD SEMESTER"); //creates new label and sets components
		lblAddSemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSemester.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblAddSemester.setForeground(Color.WHITE);
		lblAddSemester.setBounds(10, 11, 420, 37);
		panelAddSemester.add(lblAddSemester);
		
		btnBack7 = new JButton("BACK"); //creats a button to go to prev panel
		btnBack7.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack7.setForeground(new Color(255, 255, 255));
		btnBack7.setBackground(new Color(0, 0, 102));
		btnBack7.setBounds(10, 16, 89, 23);
		panelAddSemester.add(btnBack7);
		
		lblSemesterName = new JLabel("SEMESTER NAME:"); //creates a new label and sets components
		lblSemesterName.setForeground(new Color(255, 255, 255));
		lblSemesterName.setBounds(10, 59, 218, 14);
		panelAddSemester.add(lblSemesterName);
		
		textFieldSemester = new JTextField(); //creates a text field to get semester name
		textFieldSemester.setBounds(10, 76, 420, 20);
		panelAddSemester.add(textFieldSemester);
		textFieldSemester.setColumns(10);
		
		lblStart1 = new JLabel("START:"); //creates a label and and sets components
		lblStart1.setForeground(new Color(255, 255, 255));
		lblStart1.setBounds(10, 107, 86, 14);
		panelAddSemester.add(lblStart1);
		
		calendarPane1 = new JCalendar(); //creates a new calender 
		calendarPane1.setBounds(10, 126, 420, 141);
		panelAddSemester.add(calendarPane1);
		
		lblEnd1 = new JLabel("END:"); //creates a new label and sets components
		lblEnd1.setForeground(new Color(255, 255, 255));
		lblEnd1.setBounds(10, 278, 144, 14);
		panelAddSemester.add(lblEnd1);
		
		calendarPane2 = new JCalendar(); //creates a new calendar
		calendarPane2.setBounds(10, 296, 420, 141);
		panelAddSemester.add(calendarPane2);
		
		btnFinish3 = new JButton("FINISH"); //creates a new button to finish and perform operations
		btnFinish3.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnFinish3.setBackground(Color.YELLOW);
		btnFinish3.setForeground(Color.BLACK);
		btnFinish3.setBounds(341, 442, 89, 23);
		panelAddSemester.add(btnFinish3);
	}
	
	private void initializeRemoveSemester ()
	{//creates the panel that allows us to remove semesters
		panelRemoveSemester = new JPanel(); //creates new panel and sets components
		panelRemoveSemester.setBackground(Color.BLACK);
		frame.getContentPane().add(panelRemoveSemester, "name_1002854577885769");
		panelRemoveSemester.setLayout(null);
		
		lblRemoveSemester = new JLabel("REMOVE SEMESTER"); //creates new label and sets components
		lblRemoveSemester.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblRemoveSemester.setForeground(Color.WHITE);
		lblRemoveSemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveSemester.setBounds(10, 11, 420, 32);
		panelRemoveSemester.add(lblRemoveSemester);
		
		btnBack8 = new JButton("BACK:"); //creates back button to go to prev panel
		btnBack8.setForeground(new Color(255, 255, 255));
		btnBack8.setBackground(new Color(0, 0, 102));
		btnBack8.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack8.setBounds(10, 15, 78, 23);
		panelRemoveSemester.add(btnBack8);
		
		lblSemesterToRemove = new JLabel("SEMESTER TO REMOVE:"); //creates new label and sets components
		lblSemesterToRemove.setForeground(new Color(255, 255, 255));
		lblSemesterToRemove.setBounds(10, 64, 269, 14);
		panelRemoveSemester.add(lblSemesterToRemove);
		
		comboBoxSemesters = new JComboBox(); //creates new combo box to hold semesters
		comboBoxSemesters.setBounds(10, 80, 420, 20);
		panelRemoveSemester.add(comboBoxSemesters);
		
		//creates new label and sets components
		lblRemovingASemester = new JLabel("REMOVING A SEMESTER WILL REMOVE EVERYTHING WITHIN THAT SEMESTER");
		lblRemovingASemester.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRemovingASemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemovingASemester.setForeground(new Color(255, 255, 255));
		lblRemovingASemester.setBounds(10, 341, 420, 14);
		panelRemoveSemester.add(lblRemovingASemester);
		
		lblThisStepIs1 = new JLabel("THIS STEP IS NOT REVERSIBLE!!!"); //creates new label and sets components
		lblThisStepIs1.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisStepIs1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThisStepIs1.setForeground(new Color(255, 255, 255));
		lblThisStepIs1.setBounds(10, 356, 420, 14);
		panelRemoveSemester.add(lblThisStepIs1);
		
		btnFinish4 = new JButton("FINISH"); //creates finish button to finish operations
		btnFinish4.setBackground(Color.YELLOW);
		btnFinish4.setForeground(new Color(0, 0, 0));
		btnFinish4.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnFinish4.setBounds(341, 442, 89, 23);
		panelRemoveSemester.add(btnFinish4);
	}
	
	private void initializeEditAssignment ()
	{//creates panel to edit assignments
		panelEditAssignment = new JPanel(); //creates panel and sets components
		panelEditAssignment.setBackground(Color.BLACK);
		frame.getContentPane().add(panelEditAssignment, "name_334982281257223");
		panelEditAssignment.setLayout(null);
		
		lblEditAssignment = new JLabel("EDIT ASSIGNMENT"); //creates new label and sets components
		lblEditAssignment.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAssignment.setForeground(Color.WHITE);
		lblEditAssignment.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblEditAssignment.setBounds(10, 26, 420, 32);
		panelEditAssignment.add(lblEditAssignment);
		
		btnBack10 = new JButton("BACK"); //creates new button to go to prev panel
		btnBack10.setForeground(Color.WHITE);
		btnBack10.setBackground(new Color(0, 0, 102));
		btnBack10.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnBack10.setBounds(10, 30, 89, 23);
		panelEditAssignment.add(btnBack10);
		
		lblAssignmentName = new JLabel("ASSIGNMENT NAME:"); //creates new label and sets components
		lblAssignmentName.setForeground(Color.WHITE);
		lblAssignmentName.setBounds(10, 98, 232, 14);
		panelEditAssignment.add(lblAssignmentName);
		
		comboBoxAssignments = new JComboBox(); //creates new combo box to hold assignment names
		comboBoxAssignments.setBounds(10, 115, 321, 20);
		panelEditAssignment.add(comboBoxAssignments);
		 
		btnSelect2 = new JButton("SELECT"); //creates new select box to select combo box item
		btnSelect2.setBackground(Color.YELLOW);
		btnSelect2.setBounds(341, 115, 89, 23);
		panelEditAssignment.add(btnSelect2);
		
		lblAssignmentName2 = new JLabel("ASSIGNMENT NAME:"); //creates new label and sets components
		lblAssignmentName2.setForeground(Color.WHITE);
		lblAssignmentName2.setBounds(10, 142, 280, 14);
		panelEditAssignment.add(lblAssignmentName2);
		
		textFieldAssignmentName = new JTextField(); //creates new text filed to get assignment name
		textFieldAssignmentName.setBounds(10, 159, 420, 20);
		panelEditAssignment.add(textFieldAssignmentName);
		textFieldAssignmentName.setColumns(10);
		
		lblDueDate2 = new JLabel("DUE DATE:"); //creates label and sets components
		lblDueDate2.setForeground(Color.WHITE);
		lblDueDate2.setBounds(10, 190, 200, 14);
		panelEditAssignment.add(lblDueDate2);
		
		calendarPane3 = new JCalendar(); //creates new calendar and sets components
		calendarPane3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		calendarPane3.setForeground(Color.WHITE);
		calendarPane3.setBackground(Color.BLACK);
		calendarPane3.setBounds(10, 210, 420, 153);
		panelEditAssignment.add(calendarPane3);
		
		lblWeight2 = new JLabel("WEIGHT:"); //creates new label and sets components
		lblWeight2.setForeground(Color.WHITE);
		lblWeight2.setBounds(10, 374, 99, 14);
		panelEditAssignment.add(lblWeight2);
		
		lblGrade = new JLabel("GRADE:"); //creates new label and sets components
		lblGrade.setForeground(Color.WHITE);
		lblGrade.setBounds(177, 374, 141, 14);
		panelEditAssignment.add(lblGrade);
		
		txtFieldWeight = new JTextField(); //creates text field to hold weight of assignment
		txtFieldWeight.setBounds(10, 390, 66, 20); 
		panelEditAssignment.add(txtFieldWeight);
		txtFieldWeight.setColumns(10);
		
		labelPercent = new JLabel("%"); //creates new label and sets components
		labelPercent.setForeground(Color.WHITE);
		labelPercent.setBounds(82, 393, 28, 14);
		panelEditAssignment.add(labelPercent);
		
		txtFieldGrade = new JTextField(); //creates text field to hold grade
		txtFieldGrade.setBounds(177, 390, 86, 20);
		panelEditAssignment.add(txtFieldGrade);
		txtFieldGrade.setColumns(10);
		
		lblPercent2 = new JLabel("%"); //creates new label and sets components
		lblPercent2.setForeground(Color.WHITE);
		lblPercent2.setBounds(272, 393, 46, 14);
		panelEditAssignment.add(lblPercent2);
		
		btnSaveAssignment = new JButton("SAVE ASSIGNMENT"); //button to save the assignment
		btnSaveAssignment.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnSaveAssignment.setBackground(Color.YELLOW);
		btnSaveAssignment.setBounds(272, 431, 158, 23);
		panelEditAssignment.add(btnSaveAssignment);
		
		btnDeleteAssignment = new JButton("DELETE ASSIGNMENT"); //button to delete the assignment
		btnDeleteAssignment.setBackground(Color.RED);
		btnDeleteAssignment.setForeground(Color.BLACK);
		btnDeleteAssignment.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 12));
		btnDeleteAssignment.setBounds(10, 431, 158, 23);
		panelEditAssignment.add(btnDeleteAssignment);

		lblCourseName2 = new JLabel("COURSE NAME:"); //creates new label and sets components
		lblCourseName2.setForeground(Color.WHITE);
		lblCourseName2.setBounds(10, 40, 200, 50);
		panelEditAssignment.add(lblCourseName2);
		  
		comboBoxCourses2 = new JComboBox(); //creates to combo box to hold courses
		comboBoxCourses2.setBounds(10, 72, 321, 20);
		panelEditAssignment.add(comboBoxCourses2);
		  
		btnSelect3 = new JButton("SELECT"); //creates new select button to read combo box item
		btnSelect3.setBackground(Color.YELLOW);
		btnSelect3.setBounds(341, 69, 89, 23);
		panelEditAssignment.add(btnSelect3);
	}
	
	private void initializeActionListeners ()
	{	
		MyDateListener listener = new MyDateListener(); //initializes a date listener to read date from calendar
		
		//adds action listeners to all jcomponents
		comboBoxAssignments.addActionListener(this);
		comboBoxCourses2.addActionListener(this);
		btnBack10.addActionListener(this);
		btnSelect2.addActionListener(this);
		btnSelect3.addActionListener(this);
		btnDeleteAssignment.addActionListener(this);
		btnSaveAssignment.addActionListener(this);
		
		calendarPane.addDateListener(listener);
		calendarPane1.addDateListener(listener);
		calendarPane2.addDateListener(listener);
		calendarPane3.addDateListener(listener);
		
		btnHome.addActionListener(this);
		btnHome1.addActionListener(this);
		btnHome2.addActionListener(this);
		
		btnAssignments.addActionListener(this);
		btnAssignments1.addActionListener(this);
		btnAssignments2.addActionListener(this);
		
		btnGrades.addActionListener(this);
		btnGrades1.addActionListener(this);
		btnGrades2.addActionListener(this);
		
		btnSearch.addActionListener(this);
		btnHelp.addActionListener(this);
		btnManage.addActionListener(this);
		
		btnSearch1.addActionListener(this);
		btnManage2.addActionListener(this);
		
		btnBack.addActionListener(this);
		btnEnter.addActionListener(this);
		
		btnBack1.addActionListener(this);
		btnEnter1.addActionListener(this);
		
		btnBack2.addActionListener(this);
		
		btnBack3.addActionListener(this);
		btnFinish.addActionListener(this);
		
		comboBoxHoursStart.addActionListener(this); 
		comboBoxMinutesStart.addActionListener(this); 
		comboBoxDayStart.addActionListener(this); 
		comboBoxHoursEnd.addActionListener(this); 
		comboBoxMinutesEnd.addActionListener(this); 
		comboBoxDayEnd.addActionListener(this);
		
		comboBoxSemesterCourseRemove.addActionListener(this);
		
		rdbtnMonday.addActionListener(this); 
		rdbtnTuesday.addActionListener(this); 
		rdbtnWednesday.addActionListener(this); 
		rdbtnThursday.addActionListener(this); 
		rdbtnFriday.addActionListener(this); 
		rdbtnSaturday.addActionListener(this); 
		rdbtnSunday.addActionListener(this);
		
		txtSearch.addActionListener(this); 
		txtSearch1.addActionListener(this); 
		txtCourseName.addActionListener(this); 
		txtLocation.addActionListener(this);
		
		btnFinish1.addActionListener(this);
		btnBack4.addActionListener(this);
		
		comboBoxCourses.addActionListener(this);
		txtWeight.addActionListener(this);
		txtAssignmentField.addActionListener(this);
		
		btnAddSemester.addActionListener(this);
		btnRemoveSemester.addActionListener(this);
		btnAddCourse.addActionListener(this);
		btnRemoveCourse.addActionListener(this);
		btnBack5.addActionListener(this);
		
		comboBoxCourse.addActionListener(this);
		btnBack6.addActionListener(this);
		btnFinish2.addActionListener(this);
		
		btnFinish3.addActionListener(this);
		btnBack7.addActionListener(this);
		
		btnFinish4.addActionListener(this);
		btnBack8.addActionListener(this);
		
		btnSelect.addActionListener(this);
		comboBoxActiveSemester.addActionListener(this);
		
		btnAddAssignment.addActionListener(this);
		btnBack9.addActionListener(this);
		
		btnEditAssignment.addActionListener(this);
	}
	
	public void initializeWindowListeners()
	{ //initializes on close    
		this.frame.addWindowListener(new WindowAdapter() //adds a window listener
        {
            @Override
            public void windowClosing(WindowEvent e)
            {	//runs these functions on close
                saveInformation(); //saves information to file
                storeActiveSemester(); //stores active semester
                e.getWindow().dispose(); //disposes window
            }
        });
		
	}
	
	public void actionPerformed(ActionEvent e)
	{//performs all the button operations
		if (e.getSource() == btnHome || e.getSource() == btnHome1 || e.getSource() == btnHome2 )
		{
			//sets home panel to true
			panelHome.setVisible(true);
			panelAssignments.setVisible(false);
			panelGrades.setVisible(false);
			showSchedule(); //shows the schedule when the home button is hit
		}
		if (e.getSource() == btnAssignments || e.getSource() == btnAssignments1 || e.getSource() == btnAssignments2 )
		{
			//sets assignment panel to true
			panelAssignments.setVisible(true);
			panelHome.setVisible(false);
			panelGrades.setVisible(false);
			showAssignments(); //shows assignments when the assignment button is hit
		}
		if (e.getSource() == btnGrades || e.getSource() == btnGrades1 || e.getSource() == btnGrades2)
		{ //sets grades panel to true
			panelGrades.setVisible(true);
			panelHome.setVisible(false);
			panelAssignments.setVisible(false);
			showGrades(); //shows all the grades when the grade button is hit
		}
		if (e.getSource () == btnSearch)
		{ //goes to search panel
			//presets the text to empty
			txtSearch.setText("");
			textAreaSearchHome.setText("");
			
			panelSearchHome.setVisible(true);
			panelHome.setVisible(false);
		}
		if (e.getSource () == btnBack || e.getSource() == btnBack2 || e.getSource() ==  btnBack5)
		{// runs when back button is hit to go  back home
			panelHome.setVisible(true);
			panelSearchHome.setVisible(false);
			panelHelp.setVisible(false);
			panelManage.setVisible(false);
			
			//updates the combo box for active semester 
			try{
				for(int i = 0; i < semesters.size(); i++){
					if(semesters.get(i).getSemesterName().equals(comboBoxActiveSemester.getSelectedItem().toString())){
						activeSemester = semesters.get(i);
					}
				}
			}
			catch(NullPointerException f)
			{
				//catches a null pointer
			}
			
			showSchedule(); //shows the scheule on the main page
		}
		if (e.getSource () == btnBack3 || e.getSource () == btnBack6 || e.getSource () == btnBack7 || e.getSource () == btnBack8)
		{ //goes back to manage panel
			panelManage.setVisible(true);
			panelAddCourse.setVisible(false);
			panelRemoveCourse.setVisible(false);
			panelAddSemester.setVisible(false);
			panelRemoveSemester.setVisible(false);
			
			//updates active semester combo box
			comboBoxActiveSemester.removeAllItems();
			for(int i = 0; i < semesters.size(); i++){
				comboBoxActiveSemester.addItem(semesters.get(i).getSemesterName());
			}
			
			//if theres no active semester, then set it as the semester name from global variable
			if(this.activeSemester!=null){
				comboBoxActiveSemester.setSelectedItem(activeSemester.getSemesterName());
			}			
		}
		if (e.getSource () == btnSearch1)
		{
			//goes to search assignment panel 
			txtSearch1.setText(""); //empties out fields
			textAreaSearchAssignment.setText("");
			
			panelSearchAssignments.setVisible(true);
			panelAssignments.setVisible(false);
		}
		if (e.getSource () == btnBack1)
		{//foes to assignments panel
			panelAssignments.setVisible(true);
			panelSearchAssignments.setVisible(false);
			panelAddAssignment.setVisible(false);
			showAssignments(); //shows the assignments that are due
		}
		if (e.getSource () == btnBack4)
		{ //goes to the manage assignment panel
			panelAddAssignment.setVisible(false);
			panelManageAssignment.setVisible(true);
		}
		if (e.getSource () == btnHelp)
		{ //goes to the help panel
			panelHelp.setVisible(true);
			panelHome.setVisible(false);
		}
		if (e.getSource () == btnManage)
		{//goes to the manage panel
			panelManage.setVisible(true);
			panelHome.setVisible(false);
			
			comboBoxActiveSemester.removeAllItems();
			for(int i = 0; i < semesters.size(); i++){ //refreses the active semester box
				comboBoxActiveSemester.addItem(semesters.get(i).getSemesterName());
			}
			
			if(this.activeSemester!=null){ //sets default active semester
				comboBoxActiveSemester.setSelectedItem(activeSemester.getSemesterName());
			}
		}
		
		if (e.getSource () == btnManage2)
		{ //goes to the manage assignment page
			panelManageAssignment.setVisible(true);
			panelAssignments.setVisible(false);	
		}
		if (e.getSource () == btnBack9)
		{ //goes to the assignments page and refreshes the upcoming assignments
			panelManageAssignment.setVisible(false);
			panelAssignments.setVisible(true);
			showAssignments();
		}
		if (e.getSource () == btnAddCourse)
		{ //goes to the add course panel
			panelAddCourse.setVisible(true);
			panelManage.setVisible(false);
			txtCourseName.setText("");
			txtLocation.setText("");
			
			comboBoxSemester.removeAllItems(); //updates the semesters in the combo box
			for(int i = 0; i < semesters.size(); i++){
				comboBoxSemester.addItem(semesters.get(i).getSemesterName());
			}
		}
		if (e.getSource () == btnRemoveCourse)
		{//goes to the remove course panel
			panelRemoveCourse.setVisible(true);
			panelManage.setVisible(false);
			
			comboBoxSemesterCourseRemove.removeAllItems(); //pdates the semesters in the combo box
			for(int i = 0; i < semesters.size(); i++){
				comboBoxSemesterCourseRemove.addItem(semesters.get(i).getSemesterName());
			}
		}	
		if (e.getSource () == btnSelect){
			//refresh comboBoxCourse on select button click
			
			String semesterName = comboBoxSemesterCourseRemove.getSelectedItem().toString(); //gets the selected item
			
			comboBoxCourse.removeAllItems(); //removs all items from the courses combo box 
			
			for(int i=0; i < semesters.size(); i++){
				if(semesters.get(i).getSemesterName().equals(semesterName))
				{
					//adds the courses onto the combo box for all courses in that semester
					for(int j=0;j < semesters.get(i).getCourse().size(); j++)
					{
						comboBoxCourse.addItem(semesters.get(i).getCourse().get(j).getCourseName());
					}
				}
			}
		}			
		if (e.getSource () == btnAddSemester)
		{//goes to the add semester panel
			panelAddSemester.setVisible(true);
			panelManage.setVisible(false);
			textFieldSemester.setText("");
		}
		if (e.getSource () == btnRemoveSemester)
		{//goes to the remove semester panel
			panelRemoveSemester.setVisible(true);
			panelManage.setVisible(false);
			comboBoxSemesters.removeAllItems();
			//adds semesters to the combo boxes
			for(int i = 0; i < semesters.size(); i++){
				comboBoxSemesters.addItem(semesters.get(i).getSemesterName());
			}
		}
		if (e.getSource () == btnFinish3)
		{//operates the components on the addSemester panel
			String semesterName = textFieldSemester.getText(); //holds text for semester name
			Date startTime = calendarPane1.getDate(); //holds text for star ttime 
			Date endTime = calendarPane2.getDate(); //holds text for end time
					
			if (startTime.toString().equalsIgnoreCase(endTime.toString()))
			{ //if start time = end time
				JOptionPane.showMessageDialog (panelAddSemester, "Please check start and end time.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (calendarPane1.getDate().getYear() > calendarPane2.getDate().getYear() )
			{//if start year > end year eg. start = 2017, end = 2015
				JOptionPane.showMessageDialog (panelAddSemester, "Please check start and end time.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (calendarPane1.getDate().getYear() == calendarPane2.getDate().getYear() && calendarPane1.getDate().getMonth() > calendarPane2.getDate().getMonth() )
			{//if start year == end year eg. start = 2015, end = 2015, and start month > end month eg. start = aug, end = jan
				JOptionPane.showMessageDialog (panelAddSemester, "Please check start and end time.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (calendarPane1.getDate().getYear() == calendarPane2.getDate().getYear() && calendarPane1.getDate().getMonth() == calendarPane2.getDate().getMonth() && calendarPane1.getDate().getDate() > calendarPane2.getDate().getDate())
			{//if start year == end year eg. start = 2015, end = 2015, and start month > end month eg. start = aug, end = jan and start day > end day e.g start = 8 and end = 7
				JOptionPane.showMessageDialog (panelAddSemester, "Please check start and end time.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (calendarPane1.getDate().getYear() == calendarPane2.getDate().getYear() && calendarPane1.getDate().getMonth() == calendarPane2.getDate().getMonth() && calendarPane1.getDate().getDate() == calendarPane2.getDate().getDate())
			{//if start year == end year eg. start = 2015, end = 2015, and start month > end month eg. start = aug, end = jan and start day = end day e.g start = 8 and end = 8
				JOptionPane.showMessageDialog (panelAddSemester, "Please check start and end time.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//add to adt
			semesters.add(new Semester (semesterName));
			semesters.getLast().setStartDate(startTime);
			semesters.getLast().setEndDate(endTime);
			
			JOptionPane.showMessageDialog (panelAddSemester, semesterName + "\nHas Been Added Succesfully."); //display message
			panelAddSemester.setVisible(false); //go back to the manage panel
			panelManage.setVisible(true);
			
			//update the combo boxes for active semester
			comboBoxActiveSemester.removeAllItems();
			for(int i = 0; i < semesters.size(); i++){
				comboBoxActiveSemester.addItem(semesters.get(i).getSemesterName());
			}
			
			if(this.activeSemester!=null){
				comboBoxActiveSemester.setSelectedItem(activeSemester.getSemesterName());
			}				
		}
		if (e.getSource () == btnFinish4)
		{//operates the components on the removeSemester panel
			try
			{
				String semesterName = comboBoxSemesters.getSelectedItem().toString(); //gets semester name to remove
							
				//delete semester from adt
				for (int i = 0; i < semesters.size(); i++){
					if(semesters.get(i).getSemesterName().equals(semesterName)){
						semesters.remove(semesters.get(i));
					}
				}
				
				activeSemester = null; //sets active semester as null
				
				//displays success message and returns to prev panel
				JOptionPane.showMessageDialog (panelRemoveSemester, semesterName + "\nHas Been Succesfully Removed.");
				panelRemoveSemester.setVisible(false);
				panelManage.setVisible(true);
				
			}
			catch (NullPointerException f)
			{//catches null pointer if any fields are null
				JOptionPane.showMessageDialog (panelRemoveSemester, "Please Select a Semester to Remove", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			comboBoxActiveSemester.removeAllItems(); //removes all items from combo box  and repdates them
			for(int i = 0; i < semesters.size(); i++){
				comboBoxActiveSemester.addItem(semesters.get(i).getSemesterName());
			}
			
			try
			{
				for(int i = 0; i < semesters.size(); i++) //sets active semester as one in the combo box
				{
					if(semesters.get(i).getSemesterName().equals(comboBoxActiveSemester.getSelectedItem().toString())){
						activeSemester = semesters.get(i);
					}
				}
			}
			catch(NullPointerException f)
			{//catches the nill pointer
				JOptionPane.showMessageDialog (null, "Please Select a Semester to Remove", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			if(this.activeSemester!=null)
			{//if null then set defualt active semester
				comboBoxActiveSemester.setSelectedItem(activeSemester.getSemesterName());
			}		
		}
		if (e.getSource () == btnFinish)
		{//operates the components on the addCourse panel
			try
			{
				String semesterName = comboBoxSemester.getSelectedItem().toString(); //gets semester name
				String courseName = txtCourseName.getText(); //gets course name
				String location = txtLocation.getText(); //gets location
				String startTime = comboBoxHoursStart.getSelectedItem().toString() + ":" + comboBoxMinutesStart.getSelectedItem().toString() + " " + comboBoxDayStart.getSelectedItem().toString();
				String endTime = comboBoxHoursEnd.getSelectedItem().toString() + ":" + comboBoxMinutesEnd.getSelectedItem().toString() + " " + comboBoxDayEnd.getSelectedItem().toString();
				String days = ""; //gets days
				
				if (courseName.length()>18){
					courseName = courseName.substring(0, 15) + "..."; //edits course name if length too big
				}
				if (location.length()>18){
					location = location.substring(0, 15) + "..."; //edits location name if length to big
				}
				
				//adds all the radio buttons to days variable if they are checked
				if (rdbtnMonday.isSelected())
					days = days + "," + "MONDAY";
				if (rdbtnTuesday.isSelected())
					days = days + "," + "TUESDAY";
				if (rdbtnWednesday.isSelected())
					days = days + "," + "WEDNESDAY";
				if (rdbtnThursday.isSelected())
					days = days + "," + "THURSDAY";
				if (rdbtnFriday.isSelected())
					days = days + "," + "FRIDAY";
				if (rdbtnSaturday.isSelected())
					days = days + "," + "SATURDAY";
				if (rdbtnSunday.isSelected())
					days = days + "," + "SUNDAY";
				
				if (courseName.equals("") || semesterName.equals("")|| location.equals(""))
				{//displays error if any field is nll
					JOptionPane.showMessageDialog (panelAddCourse, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (days.equals(""))
				{//displays error if no day is chosen
					JOptionPane.showMessageDialog (panelAddCourse, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (startTime.equals(endTime))
				{//displays error if start time = end time
					JOptionPane.showMessageDialog (panelAddCourse, "Please Check Timings", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//add course to adt
				for(int i=0; i < semesters.size(); i++)
				{
					if(semesters.get(i).getSemesterName().equals(semesterName))
					{	
						semesters.get(i).addCourse(courseName); //gets course name
						semesters.get(i).getCourse().getLast().setLocation(location); //gets location 
						semesters.get(i).getCourse().getLast().setStartTime(startTime);//gets start time
						semesters.get(i).getCourse().getLast().setEndTime(endTime); //gets end time
						
						for(String d:days.split(","))
						{ //stores split values in d
							if(d.equals("")){continue;}
							semesters.get(i).getCourse().getLast().addDay(d);
						}						
					}
				}
				//displays success message
				JOptionPane.showMessageDialog (panelAddCourse, courseName + "\nHas Been Succesfully Added.");
				//goes to rpev panel
				panelAddCourse.setVisible(false);
				panelManage.setVisible(true);
			}
			catch (NullPointerException f)
			{//catches null pointer
				JOptionPane.showMessageDialog (panelAddCourse, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getSource () == btnFinish2)
		{//operates the components on the removeCourse panel
			try
			{
				String courseName = comboBoxCourse.getSelectedItem().toString(); //gets course name
				String semesterName = comboBoxSemesterCourseRemove.getSelectedItem().toString(); //gets smester name
				
				//remove course from adt
				for(int i=0; i < semesters.size(); i++)
				{
					if(semesters.get(i).getSemesterName().equals(semesterName))
					{//if semester name is in adt 
						for(int j=0; j < semesters.get(i).getCourse().size() ; j++)
						{//them remove the semester name from the adt
							if(semesters.get(i).getCourse().get(j).getCourseName().equals(courseName))
							{
								semesters.get(i).getCourse().remove(semesters.get(i).getCourse().get(j));							}
						}	
					}
				}
				//sets successs message
				JOptionPane.showMessageDialog (panelRemoveCourse, courseName + "\nHas Been Succesfully Removed.");
				//goes to prev panel
				panelRemoveCourse.setVisible(false);
				panelManage.setVisible(true);
			}
			catch (NullPointerException f)
			{//catches the nill pointer
				JOptionPane.showMessageDialog (panelRemoveCourse, "Please Select a Course to Remove", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource () == btnFinish1)
		{//if the finish button is pushed in the add assignment panel
			try
			{//gets course name, assignment name, due date, and weight respectively
				String courseName = comboBoxCourses.getSelectedItem().toString();
				String assignmentName = txtAssignmentField.getText();
				Date dueDate = calendarPane.getDate();
				String weight = txtWeight.getText();
				
				if (courseName.equals("") || assignmentName.equals(""))
				{//displays error if any fields are empty
					JOptionPane.showMessageDialog (panelAddAssignment, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//add course to adt
				for(int i = 0; i < activeSemester.getCourse().size(); i++)
				{
					if (activeSemester.getCourse().get(i).getCourseName().equals(courseName))
					{//gets corse name
						activeSemester.getCourse().get(i).addAssignment(assignmentName); //gets assignment name
						activeSemester.getCourse().get(i).getAssignments().getLast().setDueDate(dueDate); //gets due date
						if(weight.equals("")) //gets weight
						{
							activeSemester.getCourse().get(i).getAssignments().getLast().setWeight(0.0); //sets the weight if null
						}
						else{
							//otherwise sets the weight to the weight in the txt
							activeSemester.getCourse().get(i).getAssignments().getLast().setWeight(Double.parseDouble(weight));
						}
					}
				}
				//displays success message
				JOptionPane.showMessageDialog (panelAddAssignment, assignmentName + "\nHas Been Succesfully Added.");
				//returns to prev panel
				panelAddAssignment.setVisible(false);
				panelManageAssignment.setVisible(true);	
			}
			catch (NullPointerException f)
			{//displays null pointer exception error
				JOptionPane.showMessageDialog (panelAddAssignment, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			catch (NumberFormatException f)
			{//displays error if number for weight is not an numb
				JOptionPane.showMessageDialog (panelAddAssignment, "Please only enter a number for weight", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource () == btnAddAssignment)
		{//panel where assignment is added
			
			panelAddAssignment.setVisible(true); //sets assignment panel as true
			panelManageAssignment.setVisible(false);
			txtAssignmentField.setText(""); //presets text fields to empty
			txtWeight.setText("");
			
			comboBoxCourses.removeAllItems(); //removes courses and updates the ocmbo box
			for(int i = 0; i < this.activeSemester.getCourse().size(); i++){
				comboBoxCourses.addItem(activeSemester.getCourse().get(i).getCourseName()); //adds item to combo box
			}
		}
		
		if (e.getSource () == btnEditAssignment)
		{//edits assignment panel
			comboBoxCourses2.removeAllItems(); //updates combo boxes and sets text fields to empty
			comboBoxAssignments.removeAllItems();
			textFieldAssignmentName.setText("");
			calendarPane3.setDate(new Date());
			txtFieldWeight.setText("");
			txtFieldGrade.setText("");
			
			for(int i = 0; i < this.activeSemester.getCourse().size(); i++){
				comboBoxCourses2.addItem(activeSemester.getCourse().get(i).getCourseName()); //addscourse name to combo box
			}
			
			//stays on current panel
			panelManageAssignment.setVisible(false);
			panelEditAssignment.setVisible(true);
		}
		
		if (e.getSource () == btnSelect3)
		{//runs on edit assignment when select is pushed 
			comboBoxAssignments.removeAllItems(); //updates assignment panel
			for(int i = 0; i < this.activeSemester.getCourse().size(); i++){ //adds all assignemnts to the combo box
				if(activeSemester.getCourse().get(i).getCourseName()==comboBoxCourses2.getSelectedItem().toString()){
					//loop assignments
					for(int k = 0; k < activeSemester.getCourse().get(i).getAssignments().size();k++){
						comboBoxAssignments.addItem(activeSemester.getCourse().get(i).getAssignments().get(k).getAssignmentName());
					}
				}
			}
		}
		
		if (e.getSource () == btnSelect2)
		{//runs when select is pushed for the course
			try{
				tempAssignmentName = comboBoxAssignments.getSelectedItem().toString(); //sets temp assignment name
				
				for(int i = 0; i < this.activeSemester.getCourse().size(); i++){
					//gets the item in the text fields respecitvly 
					if(activeSemester.getCourse().get(i).getCourseName()==comboBoxCourses2.getSelectedItem().toString()){
						//loop assignments
						for(int k = 0; k < activeSemester.getCourse().get(i).getAssignments().size();k++){
							if(activeSemester.getCourse().get(i).getAssignments().get(k).getAssignmentName()==comboBoxAssignments.getSelectedItem().toString()){
								textFieldAssignmentName.setText(comboBoxAssignments.getSelectedItem().toString());
								calendarPane3.setDate(activeSemester.getCourse().get(i).getAssignments().get(k).getDueDate());
								txtFieldWeight.setText(activeSemester.getCourse().get(i).getAssignments().get(k).getWeight()+"");
								txtFieldGrade.setText(activeSemester.getCourse().get(i).getAssignments().get(k).getGrade()+"");
							}
						}
					}
				}
			}
			catch(NullPointerException f)
			{
				//shows error for null pointer
				JOptionPane.showMessageDialog (panelEditAssignment, "Please Select a Course", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource () == btnDeleteAssignment)
		{//deletes assignment when button is pushed
			for(int i = 0; i < this.activeSemester.getCourse().size(); i++)
			{ //gets the selected item and sees if it is in adt
				if(activeSemester.getCourse().get(i).getCourseName()==comboBoxCourses2.getSelectedItem().toString()){
					//loop assignments and removes the assignment 
					for(int k = 0; k < activeSemester.getCourse().get(i).getAssignments().size();k++){
						if(activeSemester.getCourse().get(i).getAssignments().get(k).getAssignmentName()==comboBoxAssignments.getSelectedItem().toString()){
							activeSemester.getCourse().get(i).getAssignments().remove(activeSemester.getCourse().get(i).getAssignments().get(k));
						}
					}
				}
			}
			//shows success message and returns to prev panel
			JOptionPane.showMessageDialog (panelEditAssignment, comboBoxAssignments.getSelectedItem().toString() + "\nHas Been Succesfully Deleted.");
			panelManageAssignment.setVisible(true);
			panelEditAssignment.setVisible(false);
			
		}
		
		if (e.getSource() == btnSaveAssignment){
			try
			{//saves the assignment
				String courseName = comboBoxCourses2.getSelectedItem().toString();//gets course name
				String assignmentName = textFieldAssignmentName.getText(); //gets assignment name
				Date dueDate = calendarPane3.getDate(); //gets due date
				String weight = txtFieldWeight.getText(); //gets weight
				String grade = txtFieldGrade.getText(); //gets grade
				
				if (courseName.equals("") || assignmentName.equals(""))
				{//returns error if fields are null
					JOptionPane.showMessageDialog (panelEditAssignment, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//add course to adt
				for(int i = 0; i < activeSemester.getCourse().size(); i++)
				{//edits the adt and dds based on changes 
					if (activeSemester.getCourse().get(i).getCourseName().equals(courseName))
					{//update the assignment name due date weight and grade after save button is pushed
						for(int k = 0; k < activeSemester.getCourse().get(i).getAssignments().size();k++){
							if(activeSemester.getCourse().get(i).getAssignments().get(k).getAssignmentName().equals(tempAssignmentName)){
								activeSemester.getCourse().get(i).getAssignments().get(k).setAssignmentName(assignmentName);
								activeSemester.getCourse().get(i).getAssignments().get(k).setDueDate(dueDate);
								if(weight.equalsIgnoreCase("")){
									activeSemester.getCourse().get(i).getAssignments().get(k).setWeight(0.0);
								}
								else{
									activeSemester.getCourse().get(i).getAssignments().get(k).setWeight(Double.parseDouble(weight));
								}
								
								if(grade.equalsIgnoreCase("")){
									activeSemester.getCourse().get(i).getAssignments().get(k).setGrade(0.0);
								}
								else{
									activeSemester.getCourse().get(i).getAssignments().get(k).setGrade(Double.parseDouble(grade));
								}	
							}
						}
					}
				}
				
				//sets success messagee
				JOptionPane.showMessageDialog (panelEditAssignment, assignmentName + "\nHas Been Succesfully Editted.");
				//returns to prev panel
				panelEditAssignment.setVisible(false);
				panelManageAssignment.setVisible(true);
			}
			catch (NullPointerException f)
			{//catches null pointer
				JOptionPane.showMessageDialog (panelAddAssignment, "Please Make Sure All Fields Are Completed", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			catch (NumberFormatException f)
			{//displays error if weight is not a number
				JOptionPane.showMessageDialog (panelAddAssignment, "Please only enter a number for weight", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		
		}
		if (e.getSource() == btnBack10)
		{//goes back to manage assignment panel
			panelManageAssignment.setVisible(true);
			panelEditAssignment.setVisible(false);
		}
		
		if (e.getSource() == btnEnter)
		{	//home search
			//make list of all courses
			LinkedList<Course> allCourses = new LinkedList<Course>();
			for(int i = 0; i < activeSemester.getCourse().size(); i++){
				allCourses.add(activeSemester.getCourse().get(i));
			}
			
			//search for courseName
			Algorithms alg = new Algorithms();
			int index = alg.searchForCourseName(allCourses, txtSearch.getText());
			
			String temp=""; //temp variable for display
			DateFormat format1 = new SimpleDateFormat("EEE MMMM dd yyyy"); //sets date format
			
			if(index == -1){  //if -1 then course does not exist 
				temp += "Course Not Found";
			}
			else{ ///otherwise update temp 
				temp += "Course Info \n";
				temp += "Course Name: " + allCourses.get(index).getCourseName() + "\n"; //gets course name 
				temp += "Location: " + allCourses.get(index).getLocation() + "\n"; //gers locaton 
				temp += "Time: " + allCourses.get(index).getStartTime() + " - " + allCourses.get(index).getEndTime() + "\n";
				temp += "Days: "; //gets days
				for(String days:allCourses.get(index).getDays()){
					temp += days + " "; //adds spacing for formatting
				}
				temp += "\nCurrent Grade: " + allCourses.get(index).calculateGrade(); //calcs grade
				temp += "\n\nAssignments \n"; //assignments pane
				
				//loop Assignments
				for(Assignment a:allCourses.get(index).getAssignments()){
					temp += "Assignment Name: " +  a.getAssignmentName() + "\n"; //displays assignment name
					temp += "Due Date: " + format1.format(a.getDueDate())+ "\n"; //assignment date
					temp += "Grade: " + a.getGrade()+ "\n"; //assignemnt grade
					temp += "Weight " + a.getWeight()+ "\n\n"; //assignment weight
				}
			}
			textAreaSearchHome.setText(temp);	 //adds all to text area
		}
		if(e.getSource() == btnEnter1){
			//assignment search
			
			Course currentCourse; //gets current course
			LinkedList<Assignment> allAssignments = new LinkedList<Assignment>(); //creates linked list
			
			//make a list of all assignments
			//loop courses
			for(int i = 0; i < activeSemester.getCourse().size(); i++){
				currentCourse = activeSemester.getCourse().get(i);
				//loop assignments
				for(int j = 0; j < currentCourse.getAssignments().size();j++){
					//add to activeAssignments
					allAssignments.add(currentCourse.getAssignments().get(j));
				}
			}
			
			//search for courseName
			Algorithms alg = new Algorithms();
			int index = alg.searchForAssignmentName(allAssignments, txtSearch1.getText());
			
			String temp=""; //temp to hold all values 
			DateFormat format1 = new SimpleDateFormat("EEE MMMM dd yyyy"); //date format 
			
			if(index == -1){
				temp += "Assignment Not Found"; //if -1 then assignment doesnt exist
			}
			else{
				temp += "Assignment Name: " + allAssignments.get(index).getAssignmentName() + "\n"; //sets assignment name 
				temp += "Due Date: " + format1.format(allAssignments.get(index).getDueDate()) + "\n"; ///sets due date
				temp += "Grade: " + allAssignments.get(index).getGrade() + "\n"; //sets grade
				temp += "Weight: " + allAssignments.get(index).getWeight() + "\n"; //sets weight
			}
			textAreaSearchAssignment.setText(temp);	 //sets to text area
		}
	}

	private void showGrades()
	{//function that shows the grades panel
		String temp = ""; //temp variable
		textAreaDisplayGrades.setText(""); //sets the text field as blank initialiiy
		try
		{		
			//loop through courses
			for(int i = 0; i < activeSemester.getCourse().size(); i++)
			{			
				temp += activeSemester.getCourse().get(i).getCourseName(); //adds the cours name
				for (int j = 0; j <= 40 - activeSemester.getCourse().get(i).getCourseName().length(); j++)
				{
					temp += " "; //adds a space to format 
				}
				
				temp += activeSemester.getCourse().get(i).calculateGrade() + "\n";	 //adds the grade
			}
			
			//displays the gpa
			for (int j = 0; j <= 13 ; j++)
			{
				temp += " "; //adds spaces for format
			}
			temp += "Current GPA: "; //text for current gpa
			temp += activeSemester.calculateGPA() + "\n"; //displays the gpa
		}
		catch(NullPointerException e){
			temp = "PLEASE SELECT OR ADD AN ACTIVE SEMESTER"; //sets temp if there is a null
		}
		textAreaDisplayGrades.setText(temp); //sets the text area text
	}

	private void showAssignments()
	{	
		LinkedList<Assignment> activeAssignments = new LinkedList<Assignment>(); //holds active assignments 
		Course currentCourse; //holds current course
		Algorithms algs = new Algorithms(); //uses algorithms to sort
		String temp = ""; //temp variable
		
		//make a list of all assignments
		//loop courses
		for(int i = 0; i < activeSemester.getCourse().size(); i++){
			currentCourse = activeSemester.getCourse().get(i);
			//loop assignments
			for(int j = 0; j < currentCourse.getAssignments().size();j++){
				//add to activeAssignments
				activeAssignments.add(currentCourse.getAssignments().get(j));
			}
		}
		
		//sort list of assignments by due date
		activeAssignments = algs.sortByDueDate(activeAssignments);
		
		//sets date format for assignemtnts to sort by
		DateFormat format1 = new SimpleDateFormat("EEE MMMM dd yyyy");
		
		//display list of assignments
		for (int i = 0; i < activeAssignments.size();i++){
			temp += activeAssignments.get(i).getAssignmentName();
			
			for (int j = 0; j <= 42 - activeAssignments.get(i).getAssignmentName().length()-format1.format(activeAssignments.get(i).getDueDate()).length(); j++)
			{
				temp += " "; //prints spaces for formatting
			}
			
			temp += " " + format1.format(activeAssignments.get(i).getDueDate()) + "\n"; //prints the assignments
		}
		this.textAreaDisplayAssignments.setText(temp);	 //sets the textarea
	}
	
	private void showSchedule()
	{	
		DateFormat dateFormat = new SimpleDateFormat("EEEE"); //sets the day format
		Date date = new Date(); //creates new date
		String temp=""; //temp variable 
		textAreaDisplay.setText(""); //sets the text area as empty
		LinkedList<Course> todaysCourses = new LinkedList<Course>(); //holds current courses
		Algorithms algs = new Algorithms(); //calls algorithsm object
		
		try
		{	
			//make list of todays courses
			for (int i = 0; i < activeSemester.getCourse().size();i++){
				if(activeSemester.getCourse().get(i).getDays().contains(dateFormat.format(date).toUpperCase())){
					todaysCourses.add(activeSemester.getCourse().get(i)); //gets courses
				}
			}
			
			//sort list of courses
			todaysCourses = algs.sortByStartTime(todaysCourses);
			
			//display list of courses
			for (int i = 0; i < todaysCourses.size();i++){
				
				temp += todaysCourses.get(i).getCourseName(); //displays course name
				
				for (int j = 0; j <= 39 - todaysCourses.get(i).getCourseName().length() - todaysCourses.get(i).getStartTime().length() - todaysCourses.get(i).getEndTime().length() ; j++)
				{
					temp += " "; //sets spacing
				}
				
				temp += " " + todaysCourses.get(i).getStartTime(); //adds start time 
				temp += " - " + todaysCourses.get(i).getEndTime(); //adds end time
				temp += "\n" + todaysCourses.get(i).getLocation()+ "\n\n"; //adds locaton
			}
		}
		catch(NullPointerException e)
		{
			temp = "\t    PLEASE ADD A SEMESTER"; //sets temp if null
		}  
		this.textAreaDisplay.setText(temp); //sets text area as temp
	}
	
	private void storeActiveSemester ()
	{		
		File semesterFile = new File("Active Semester"); //creates a new folder
		semesterFile.mkdir(); //makes the folder
		
		try 
		{//creates new print writer and file namee to write too
			PrintWriter writer = new PrintWriter ("Active Semester" + File.separator + "currentSemester.txt");
			writer.print(""); //clears the file
			writer.println (this.activeSemester.getSemesterName()); //writes the latest semester
			writer.close(); //closes the file
		} 
		catch (FileNotFoundException e)
		{
			//does nothing if file is not found
		}
		catch (NullPointerException e)
		{
			//does nothing if semester is null
		}
	}
	
	public static void main(String[] args) 
	{//creates frame and sets as visibly
		HomePage window = new HomePage();
		window.frame.setVisible(true);		
	}
	
	
	private class MyDateListener implements DateListener
	{ //inner class to create date listener for calender
		public void dateChanged(DateEvent e)
		{
			Calendar c = e.getSelectedDate(); //gets calender 
			if (c != null) 
			{
				c.getTime(); //if c is not null, then get the time
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "NO TIME SELECTED"); //otherwise display error
			}
		}
	}
}
