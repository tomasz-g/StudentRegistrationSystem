import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 public class RegistrationSystem {
	
	 static Scanner input = new Scanner(System.in);
	 static BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));
	 
	
	 static ArrayList<Student> students = new ArrayList<Student>();
	 static ArrayList<Student> computerScienceStudents = new ArrayList<Student>();
	 static ArrayList<Student> gameDesignStudents = new ArrayList<Student>();
	 
	 public static Student newStudent;
	 static int studentId = 0;
	 static String course;
	 static String name;

	
	 private static void mainMenu() {
		
		System.out.println("Press 1 to add stdent");
		System.out.println("Press 2 for student/s data");
		System.out.println("Press 3 for admin menu");
		
		String userChoice = input.next();
		
		switch(userChoice) {
		
			case "1": {
				studentName();
				break;
			}
			case "2": {
				studentInformation();
				break;
			}
			case "3": {
				System.out.println("TODO admin menu method...");
				mainMenu();

				break;
			}
			default:{
				System.out.println("Wrong input, try again");
				mainMenu();
				break;
			}
		}
	}
	 
	 
	 private static void studentName() {
		 
		try {
			
			System.out.println("Enter student Name and Surname:");
			name = input2.readLine();			
		}
		
		catch (IOException e) {
			System.out.println("Error, please try again");
			studentName();
		}
		courseChoice();
	}
	 
	 
	private static void courseChoice() {
		
		System.out.println("*********************************");
		System.out.println(" Choose student Course \n Press 1 for Computer Science \n Press 2 for Game Design" );
		System.out.println("*********************************");
		
		String userChoice = input.next();
		
		switch (userChoice) {
			case "1":{
				course = "Computer Science";
				addStudent();
				break;
			}
			
			case "2":{
				course = "Game Design";
				addStudent();
				break;
			}
			
			default:{
				System.out.println("Wrong number, try again");
				courseChoice();
				break;
			}
		}
	}
	
	public static void addStudent() {
		
		Student newStudent = new Student();
		students.add(newStudent);
		if (newStudent.getCourse() == "Computer Science") {
			computerScienceStudents.add(newStudent);
		}
		else if (newStudent.getCourse() == "Game Design") {
			gameDesignStudents.add(newStudent);
		}
		mainMenu();
//		payFee();
	}
	
	public static void payFee() {
		
		System.out.println("*********************************");
		System.out
				.println("Press 'y' and hit enter if you want to pay for the course now \n Press any key to pay later");
		System.out.println("*********************************");
		
		String userChoice = input.next();
		
		switch (userChoice) {
			case "y": {
				newStudent.payFee(true);
				newStudent.print();
				break;
			}
			
			default:{
				newStudent.print();
				break;
			}			
		}
		mainMenu();
	}
	
	public static void studentInformation() {
		
		System.out.println("*********************************");
		System.out
				.println("Press 1 for Computer Science class \n Press 2 for Game Design class \n Press 3 for all Students");
		System.out.println("*********************************");

		String userChoice = input.next();
		
		switch (userChoice) {
			case "1": {
				for(Student studentDetails : computerScienceStudents) {
					studentDetails.print();
				}
			}
			
			case "2": {
				for(Student studentDetails : gameDesignStudents) {
					studentDetails.print();
					break;
				}
			}
			
			case "3": {
				for(Student studentDetails : students) {
					studentDetails.print();
					break;
				}
			}
			
			default:{
				System.out.println("Wrong number, try again");
				studentInformation();
				break;
			}
		}
		
		mainMenu();
	}


	 public static void main(String[] args) {
		
		mainMenu();
	}

}
