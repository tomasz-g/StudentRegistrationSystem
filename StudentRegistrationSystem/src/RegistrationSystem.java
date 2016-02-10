import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 public class RegistrationSystem {
	
	 static Scanner input = new Scanner(System.in);
	 static BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));
	 
	
	 static ArrayList<Student> allStudents = new ArrayList<Student>();
	 static ArrayList<Student> computerScienceallStudents = new ArrayList<Student>();
	 static ArrayList<Student> gameDesignallStudents = new ArrayList<Student>();
	 
	 static int studentId = 1;
	 
	 
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
			System.out.println(" Enter student Name and Surname \n       seperated by space:");
			System.out.println("*********************************");

			String studentName = input2.readLine();		
			
			if (studentName.split(" ").length != 2) {
				System.out.println(" Enter one word for name and one for surname, \n "
						+ "if name or surname has more than one word seperate them by dash key '-'");
				addStudent();
			}
			newStudent.setName(studentName);
		}
		
		catch (IOException e) {
			System.out.println(" Error, wrong input  \n please try again");
			addStudent();
		}
		
		newStudent.setId(studentId);
		studentId++;
		
		while (newStudent.getCourse() == null) {
			
			System.out.println("*********************************");
			System.out.println(" Choose student Course \n Press 1 for Computer Science \n Press 2 for Game Design" );
			System.out.println("*********************************");
			
			String userChoice = input.next();
			
			switch(userChoice) {
			
				case "1": {
					newStudent.chooseCourse("Computer Science");
					computerScienceallStudents.add(newStudent);
					allStudents.add(newStudent);
					break;
				}
				case "2": {
					newStudent.chooseCourse("Game Design     ");
					gameDesignallStudents.add(newStudent);
					allStudents.add(newStudent);
					break;
				}
				default : {
					System.out.println("Error, please try again");
					break;
				}
			}
		}
		
		System.out.println(" Press 'y' if student pay fee now \n Press any other key if student pay fee later");
		
		String userChoice = input.next();
		
		if (userChoice.equals("y")) {
			newStudent.payFee();
			}
		
		mainMenu();
	}
	
	public static void studentInformation() {
		
		System.out.println("*********************************");
		System.out
				.println(" Press 1 for Computer Science class \n Press 2 for Game Design class \n"
						+ " Press 3 for all allStudents \n Press 4 for main menu");
		System.out.println("*********************************");

		String userChoice = input.next();
		ArrayList<Student> allStudentsToPrint = null;
		
		switch (userChoice) {
			case "1": {
				allStudentsToPrint = computerScienceallStudents;
				break;
			}
			case "2": {  
				allStudentsToPrint = gameDesignallStudents;
				break;
			}
			case "3": {
				allStudentsToPrint = allStudents;
				break;
			}
			case "4": {
				mainMenu();
				break;
			}
			default: {
				System.out.println("Wrong number, try again"); 
				studentInformation();
				break;
			}
		}
		
		if (allStudentsToPrint.size() < 1) {
			System.out.println("No allStudents registered in this class");
		}
		else {
			System.out.println("______________________________________________________________________________________________");
			for(Student studentDetails : allStudentsToPrint) {
				studentDetails.print();
			}
		}
		
		System.out.println("______________________________________________________________________________________________");
		System.out.println("*" );
		studentInformation();
	}


	 public static void main(String[] args) {
		
		mainMenu();
	}

}
