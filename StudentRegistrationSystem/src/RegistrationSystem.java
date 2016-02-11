import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
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
				addStudentName();
				break;
			}
			case "2": {
				studentInformation();
				break;
			}
			case "3": {
				adminMenu();
				break;
			}
			default:{
				System.out.println("Wrong input, try again");
				mainMenu();
				break;
			}
		}
	}
	 
	 
	 public static void addStudentName() {
		 
		 newStudent = new Student();
		 
		try {
			System.out.println("***********************************");
			System.out.println(" Enter student Name and Surname \n       seperated by space:");
			System.out.println("***********************************\n");

			String studentName = input2.readLine();		
			
			if (studentName.split(" ").length == 2 && correctFormat(studentName)) {
				newStudent.setName(studentName);
			}
			else {
				printNameErrorMessage();
			}
		}
		
		catch (IOException e) {
			printNameErrorMessage();
		}
		
		chooseStudentCourse();
	 }
	 
	 
	 public static void chooseStudentCourse() {
		 
			System.out.println("***********************************");
			System.out.println(" Choose student Course: \n Press 1 for Computer Science \n Press 2 for Game Design" );
			System.out.println("***********************************\n");
			
			String userChoice = input.next();
			
			switch(userChoice) {
			
				case "1": {
					newStudent.chooseCourse("Computer Science");
					computerScienceStudents.add(newStudent);
					allStudents.add(newStudent);
					break;
				}
				case "2": {
					newStudent.chooseCourse("Game Design     ");
					gameDesignStudents.add(newStudent);
					allStudents.add(newStudent);
					break;
				}
				default : {
					System.out.println("Wrong input, try again");
					chooseStudentCourse();
					break;
				}
			}
						
			payFee();
	 }
		
	 
	 public static void payFee() {
		 
		System.out.println("***********************************");
		System.out.println(" Press 'y' if student pay fee now \n or any key if pay fee later");
		System.out.println("***********************************\n");
				
		String userChoice = input.next();
				
		if (userChoice.equals("y")) {
			newStudent.payFee();
		}
		
		setStudentId();
	 }
	 
	 
	 public static void setStudentId() {
			newStudent.setId(studentId);
			studentId++;
			
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
	
	
	public static void adminMenu() {
		
		System.out.println("***********************************");
		System.out.println(" Choose student by ID \n from the list below:");
		System.out.println("***********************************\n");

		printStudentsDetails(allStudents);

		try {
			
			int studentIndex = input.nextInt() -1;
			allStudents.get(studentIndex);
			System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
			System.out.print("---------------------------------------------------------\n");
			allStudents.get(studentIndex).print();
			System.out.print("---------------------------------------------------------\n \n");
			
			chooseAction(studentIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			
			System.out.println("Wrong input, try again");
			adminMenu();
		}	
	}
	
	
	public static void chooseAction(int studentIndex) {
		
		System.out.println("***********************************");
		System.out.println("Press 1 to change student details \n"
				+ "Press 2 to delete student");		
		System.out.println("***********************************\n");
		
		String adminChoice = input.next();
		
		switch (adminChoice) {
			case "1": {
				changeStudentDetails(studentIndex);
				break;
			}
			case "2": {
				deleteStudent(studentIndex);
				break;
			}
			default: {
				System.out.println("Wrong input, try again");
				chooseAction(studentIndex);
			}
		}
	}
	
	
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
	
	public static void changeStudentDetails(int studentIndex) {
		
		System.out.println();
		
		String adminChoice = input.next();
		
		switch (adminChoice) {
			case "1": {
				
			}
		}
	}

	
	public static void deleteStudent(int studentIndex) {
		
	}
	
	
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
	
	
	public static void printNameErrorMessage() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(" Enter one word for name and one for surname, \n "
				+ "if name or surname has more than one word \n seperate them by dash key '-' \n"
				+ "only alphabetic characters are allowed");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
		
		addStudentName();
	}


	 public static void main(String[] args) {
		
		mainMenu();
	}

}
