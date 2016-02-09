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
	 
	 static int studentId = 1;
	 
	 static String course;
	 static String name;

	
	 private static void mainMenu() {
		
		System.out.println("Press 1 to add stdent");
		System.out.println("Press 2 for student/s data");
		System.out.println("Press 3 for admin menu");
		
		String userChoice = input.next();
		
		switch(userChoice) {
		
			case "1": {
				addStudent();
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
	 
	 private static void addStudent() {
		 
		Student newStudent = new Student();
		
		try {
			System.out.println("*********************************");
			System.out.println("Enter student Name and Surname:");
			System.out.println("*********************************");

			String studentName = input2.readLine();		
			newStudent.setName(studentName);
		}
		
		catch (IOException e) {
			System.out.println("Error, please try again");
			addStudent();
		}
		
		newStudent.setId(studentId);
		
		while (newStudent.getCourse() == null) {
			
			System.out.println("*********************************");
			System.out.println(" Choose student Course \n Press 1 for Computer Science \n Press 2 for Game Design" );
			System.out.println("*********************************");
			
			String userChoice = input.next();
			
			switch(userChoice) {
			
				case "1": {
					newStudent.chooseCourse("Computer Science");
					computerScienceStudents.add(newStudent);
					break;
				}
				case "2": {
					newStudent.chooseCourse("Game Design");
					gameDesignStudents.add(newStudent);
					break;
				}
				default : {
					System.out.println("Error, please try again");
					break;
				}
			}
		}
		
		System.out.println("Press 'y' if student pay fee now \n Press any other key if student pay fee later");
		
		String userChoice = input.next();
		
		if (userChoice == "y") {
			newStudent.payFee();
		}
		
		students.add(newStudent);
		studentId++;
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
