import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;

 public class RegistrationSystem {
	
	 static Scanner input = new Scanner(System.in);
	 static BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));
	 
	
	 static ArrayList<Student> allStudents = new ArrayList<Student>();
	 static ArrayList<Student> computerScienceStudents = new ArrayList<Student>();
	 static ArrayList<Student> gameDesignStudents = new ArrayList<Student>();
	 
	 public static Student newStudent;
	 static int studentId = 1;
	  
	  
	 public static void mainMenu() {
		
		System.out.println("***********************************");
		System.out.println("Press 1 to add stdent");
		System.out.println("Press 2 for students data");
		System.out.println("Press 3 for admin menu");
		System.out.println("***********************************\n");

		
		String userChoice = input.next();
		   
		switch(userChoice) {
		
			case "1": {
				setStudentId();
				break;
			}
			case "2": {
				studentInformation();
				break;
			}
			case "3": {
//				adminMenu();
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
	 

	 public static void setStudentId() {
		 
		 newStudent = new Student();

		newStudent.setId(studentId);
		allStudents.add(newStudent);
		setStudentName(studentId -1);
		studentId++;		
	 }
	 
	  
	 public static void setStudentName(int studentIndex) {
		 
		 
		try {
			System.out.println("***********************************");
			System.out.println(" Enter student Name and Surname \n       seperated by space:");
			System.out.println("***********************************\n");

			String studentName = input2.readLine();		
			
			if (studentName.split(" ").length == 2 && correctFormat(studentName)) {
				allStudents.get(studentIndex).setName(studentName);
			}
			else {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(" Enter one word for name and one for surname, \n "
						+ "if name or surname has more than one word \n seperate them by dash key '-' \n"
						+ "only alphabetic characters are allowed");
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
				setStudentName(studentIndex);
//				printNameErrorMessage();
			}
		}
		
		catch (Exception e) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(" Enter one word for name and one for surname, \n "
					+ "if name or surname has more than one word \n seperate them by dash key '-' \n"
					+ "only alphabetic characters are allowed");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
			setStudentName(studentIndex);
//			printNameErrorMessage();
		}
		
		chooseStudentCourse(studentIndex);
	 }
	 
	 
	 public static void chooseStudentCourse(int studentIndex) {
		 
			System.out.println("***********************************");
			System.out.println(" Choose student Course: \n Press 1 for Computer Science \n Press 2 for Game Design" );
			System.out.println("***********************************\n");
			
			String userChoice = input.next();
			
			switch(userChoice) {
			
				case "1": {
					allStudents.get(studentIndex).chooseCourse("Computer Science");
					break;
				}
				case "2": {
					allStudents.get(studentIndex).chooseCourse("Game Design     ");
					break;
				}
				default : {
					System.out.println("Wrong input, try again");
					chooseStudentCourse(studentIndex);
					break;
				}
			}
						
			payFee(studentIndex);
	 }
		
	 
	 public static void payFee(int studentIndex) {
		 
		System.out.println("***********************************");
		System.out.println(" Press 'y' if student pay fee now \n or any key if pay fee later");
		System.out.println("***********************************\n");
				
		String userChoice = input.next();
				
		if (userChoice.equals("y")) {
			allStudents.get(studentIndex).payFee();
		}
		
		mainMenu();
	 }
             
	 
	public static void studentInformation() {
		
		System.out.println("***********************************");
		System.out
				.println(" Press 1 for Computer Science class \n Press 2 for Game Design class \n"
						+ " Press 3 for all allStudents \n Press 4 for main menu");
		System.out.println("***********************************\n");

		String userChoice = input.next();
		
		switch (userChoice) {
			case "1": {
				printStudentsDetails(computerScienceStudents);
				studentInformation();
				break;
			}
			case "2": {  
				printStudentsDetails(gameDesignStudents);
				studentInformation();
				break;
			}
			case "3": {
				printStudentsDetails(allStudents);
				studentInformation();
				break;
			}
			case "4": {
				mainMenu();
				break;
			}
			default: {
				System.out.println("Wrong input, try again");
				studentInformation();
				break;
			}
		}		
	}
	
	
//	public static void adminMenu() {
//		
//		System.out.println("***********************************");
//		System.out.println("Press 1 to change student details \n"
//				+ "Press 2 to delete student \n Press 3 to check students payments");		
//		System.out.println("***********************************\n");
//		
//		String adminChoice = input.next();
//		
//		switch (adminChoice) {
//			case "1": {
//				changeStudentDetails();
//				break;
//			}
//			case "2": {
//				deleteStudent(studentIndex);
//				break;
//			}
//			case "3": {
//				printPayment();
//				break;
//			}
//			default: {
//				System.out.println("Wrong input, try again");
//				adminMenu();
//			}
//		}
//	}
	
	
//	public static void changeStudentDetails() {
//		
//		System.out.println("***********************************");
//		System.out.println(" Choose student by ID \n from the list below:");
//		System.out.println("***********************************\n");
//
//		printStudentsDetails(allStudents);
//
//		try {
//			
//			int studentIndex = input.nextInt() -1;
//			allStudents.get(studentIndex);
//			System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
//			System.out.print("---------------------------------------------------------\n");
//			allStudents.get(studentIndex).print();
//			System.out.print("---------------------------------------------------------\n \n");
//			
//			chooseAction(studentIndex);
//		}
//		
//		catch (Exception e) {
//			
//			System.out.println("Wrong input, try again");
//			adminMenu();
//		}	
//	}
//	
//	
//	public static void chooseAction(int studentIndex) {
//		
//		System.out.println(" Press 1 to change student Name \n"
//				+ "Press 2 to change student class \n"
//				+ "Press 3 for payment \n"
//				+ "Press 4 to remove student fro the system"
//				+ "Press 5 to back to Main Menu");
//		
//		String adminChoice = input.next();
//		
//		switch (adminChoice) {
//			
//			case "1": {
////				NEED TO CHANGE IN THE GAME OR SCIENCE CLASS TO!!!
//				
//				
//				break;
//			}
//			case "2": {
//				break;
//			}
//			case "3": {
//				break;
//			}
//			case "4": {
//				deleteStudent(studentIndex);
//				break;
//			}
//			case "5": {
//				mainMenu();
//				break;
//			}
//			default: {
//				System.out.println("Wrong input, try again");
//				break;
//			}
//			
//		}
//	}
	
	
	public static void printStudentsDetails(ArrayList<Student>  courseName) {
		
		if (courseName.size() < 1) {
			System.out.println("***********************************");
			System.out.println("No Students registered in this class");
			System.out.println("***********************************\n");
		}
		else {
			System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
			System.out.print("---------------------------------------------------------\n");
				for(Student studentDetails : courseName) {
					studentDetails.print();
				}
				System.out.print("---------------------------------------------------------\n \n");
		}
	}


	
//	public static void deleteStudent(int studentIndex) {
//		
//		allStudents.remove(studentIndex);
////		NEED TO REMOVE FROM THE GAME OR SCIENCE CLASS TO!!!
//		mainMenu();
//		
//	}
//	
//	
//	public static void printPayment() {
//		
//		System.out.println(" Press 1 to check students with payment \n "
//				+ "Press 2 to check students without payment");
//		
//		String adminChoice = input.next();
//		
//		switch (adminChoice) {
//			case "1": {
//				for (int studentIndex = 0; studentIndex < allStudents.size(); studentIndex++) {
//					
//					System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
//					System.out.print("---------------------------------------------------------\n");
//					if (allStudents.get(studentIndex).getFee()) {
//						allStudents.get(studentIndex).print();
//					}
//					System.out.print("---------------------------------------------------------\n \n");					
//				}
//				
//			}
//			case "2": {
//				for (int studentIndex = 0; studentIndex < allStudents.size(); studentIndex++) {
//					
//					System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
//					System.out.print("---------------------------------------------------------\n");
//					if (!allStudents.get(studentIndex).getFee()) {
//						allStudents.get(studentIndex).print();
//					}
//					System.out.print("---------------------------------------------------------\n \n");					
//				}
//			}
//			default: {
//				System.out.println("Wrong input, try again");
//				printPayment();
//			}
//		}
//	}
	
	
	/** helper function to check if character/s in user input are in correct format
	 * returns true if all characters in user input are alphabetic character or space
	 * returns false otherwise 
	 * 
	 * (String) --> (boolean)
	 * 
	 * correctFormat("Joe Bloggs")
	 * >>> true
	 * 
	 * correctFormat("John Coffe")
	 * >>> true
	 * 
	 * correctFormat(Fred12)
	 * >>> false
	 */
	static boolean correctFormat(String userInput) {
		int loopLength = userInput.length();
		
		for (int charIndx = 0; charIndx < loopLength; charIndx++) {	
			char spaceCharacter = ' ';
			char charInput = userInput.charAt(charIndx);
			if (!Character.isLetter(charInput) && charInput != spaceCharacter) {
				return false;
			}
		}
		return true;
	}
	
	
//	public static void printNameErrorMessage() {
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		System.out.println(" Enter one word for name and one for surname, \n "
//				+ "if name or surname has more than one word \n seperate them by dash key '-' \n"
//				+ "only alphabetic characters are allowed");
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
//		
//		addStudentName();
//	}


	 public static void main(String[] args) {
		
		mainMenu();
	}

}
