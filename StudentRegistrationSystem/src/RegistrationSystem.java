/**
 * @author tomasz golas 
 * project for BFEI CSCA class 12/02/2016
 * Fundamental of Object Oriented Programming in JAVA
 * 
 * basic Student Registration System
 * Program allow user to register students with:
 * - name
 * - surname
 * - one of two courses choice
 * - payment
 * - student ID (created automatically, can not be modify by the user)
 * to database and allow user to access and manipulate data
 * of each student or remove it
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.DecimalFormat;


 public class RegistrationSystem {
	
	 static Scanner input = new Scanner(System.in);
	 static BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));	 
	
	 static ArrayList<Student> allStudents = new ArrayList<Student>();
	 static ArrayList<Student> computerScienceStudents = new ArrayList<Student>();
	 static ArrayList<Student> gameDesignStudents = new ArrayList<Student>();
	 
	 public static Student newStudent;
	 static int studentId = 0;
	 static double coursePrice = 120;
	 static DecimalFormat twoDecimal = new DecimalFormat("0.00");
	  
	   
	 /** program main screen
	  * print three choices for user 
	  * - user can add student to the data base, 
	  * - check students details
	  * - go to admin menu
	  * print error message if user choose invalid option
	  */
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
	 

	 /** create student object, set student ID and add it to database  
	  */
	 public static void setStudentId() {
		 
		studentId++;		
		 newStudent = new Student();

		newStudent.setId(studentId);
		allStudents.add(newStudent);
		setStudentName(studentId -1);
	 }
	 
	  
	 /** print message to the user
	  * set student name and surname if user input is in correct format
	  * print error message otherwise
	  * continue to course option method
	  */
	 public static void setStudentName(int studentIndex) {
		 
		try {
			System.out.println("***********************************");
			System.out.println(" Enter student Name and Surname \n       seperated by space:");
			System.out.println("***********************************\n");

			String studentName = input2.readLine();		
			String formatedStudentName = formatName(studentName); //format user input
			
			//check if user input is in correct format
			if (formatedStudentName.split(" ").length == 2 && correctFormat(formatedStudentName)) {
				allStudents.get(studentIndex).setName(formatedStudentName);
				chooseStudentCourse(studentIndex);
			}
			else {
				printNameErrorMessage();
				setStudentName(studentIndex);
			}
		}
		catch (IOException e) {
			printNameErrorMessage();
			setStudentName(studentIndex);
		}
		chooseStudentCourse(studentIndex);
	 }
	 
	 
	 /** set student course which is chosen by user
	  * continue to pay fee option method
	  * print error message if user choose invalid option
	  */
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
		
	 
	 /** pay fee and/or back to main menu
	  */
	 public static void payFee(int studentIndex) {
		 		 
		System.out.println("***********************************");
		System.out.println(" Press 'y' if student pay fee now \n or any key if pay fee later");
		System.out.println("***********************************\n");
				
		String userChoice = input.next();
		if (userChoice.equals("y")) {
			validateCash(studentIndex);
		}
		else {
			mainMenu();
		}
	 }
	 
	 
	 /** Calculate and print balance of transaction and check if customer insert is in correct format
		 * print warning message if insert is in wrong format
		 * print message with transaction information 
		 * and back to main screen when transaction is finish
		 */
		static void validateCash(int studentIndex) {
			
			double currentCashEntered = 0;
			
			while (currentCashEntered < coursePrice) {
				double balanceRemining = (coursePrice - currentCashEntered);
				System.out.println("==========================================");
				System.out.println(" Insert remining balance: \n " +"€" + twoDecimal.format(balanceRemining) + "\n and hit Enter");
				System.out.println("==========================================");
				String  insert = input.next();
				
				if(correctNumericFormat(insert) && isMaxDecimal(insert, 2)) {
					double convertedInsert = Double.parseDouble(insert);   // convert String to Double
					currentCashEntered += convertedInsert;
				}
				else {
					System.out.println("******************************************");
					System.out.println(
							"Wrong currency! \n inserted value must be numeric \n and maximum of two decimal places.");
					System.out.println("******************************************");
				}
			}
				
			String finishTransactionMessage = " ";
				if  (currentCashEntered > coursePrice) {
					double customerChange = (currentCashEntered - coursePrice);
					finishTransactionMessage = "\n Pickup your  \n €"
							+  twoDecimal.format(customerChange) + " change.";
				}
			System.out.println("Thank You " + finishTransactionMessage +  "\n ");
			
			allStudents.get(studentIndex).payFee();
			mainMenu();
		}
             
	 
	 /** set list of student chooses by user to be printed by followed method 
	  * user can chose only one course student details or all students details
	  * or back to main menu
	  * print error message if user choose invalid option
	  */
	public static void studentInformation() {
		
		System.out.println("***********************************");
		System.out
				.println(" Press 1 for Computer Science class \n Press 2 for Game Design class \n"
						+ " Press 3 for all allStudents \n Press 4 for main menu");
		System.out.println("***********************************\n");

		String userChoice = input.next();
		
		switch (userChoice) {
			case "1": {
				computerScienceStudents.clear();
				for (Student studentCourse : allStudents) {
					if (studentCourse.getCourse() == "Computer Science") {
						computerScienceStudents.add(studentCourse);
					}
				}				
				printStudentsDetails(computerScienceStudents);
				studentInformation();
				break;
			}
			case "2": {  
				gameDesignStudents.clear();
				for (Student studentCourse : allStudents) {
					if (studentCourse.getCourse() == "Game Design     ") {
						gameDesignStudents.add(studentCourse);
					}
				}
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
	
	
	/** print all informations about students in the data base
	 * or message if chosen course has no student registered
	 */
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

	
	/** admin menu where user can choose option to  
	 * check and manipulate all student details (except ID)
	 *  or back to main menu
	 * print error message if user choose invalid option
	 */
	public static void adminMenu() {
		
		System.out.println("***********************************");
		System.out.println(" Press 1 to change or delete student \n"
				+ " Press 2 to check students payments \n"
				+ " Press 3 to make payment \n"
				+ " Press 4 to back to main menu");		
		System.out.println("***********************************\n");
		
		String adminChoice = input.next();
		
		switch (adminChoice) {
			case "1": {
				changeStudentDetails();
				break;
			}
			case "2": {
				printPayments();
				break;
			}
			case "3": {
				makePayment();
				break;
			}
			case "4": {
				mainMenu();
				break;
			}
			default: {
				System.out.println("Wrong input, try again");
				adminMenu();
			}
		}
	}
	
	
	/** print all students details
	 * user can change or delete student details (except ID)
	 * by choosing student ID
     * print error message if user choose invalid option
	 */
	public static void changeStudentDetails() {
		
		System.out.println("***********************************");
		System.out.println(" Choose student by ID \n from the list below:");
		System.out.println("***********************************\n");

		printStudentsDetails(allStudents);

		try {
			
			int studentIndex = input.nextInt() -1;
			
/*			next code line is just for visual reason 
 			'kick' catch() method in case of any error
  		    before any line is printed to the console */ 
			allStudents.get(studentIndex);
			
			System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
			System.out.print("---------------------------------------------------------\n");
			allStudents.get(studentIndex).print();
			System.out.print("---------------------------------------------------------\n \n");
			
			System.out.println("***********************************");
			System.out.println(" Press 1 to change details of abowe student \n"
					+ " Press 2 to delete student \n "
					+ " Press any key to back to admin menu");
			System.out.println("***********************************\n");
			
			String adminChoice = input.next();
			
			switch (adminChoice) {
				case "1": {
					setStudentName(studentIndex);
					break;
				}
				case "2": {
					deleteStudent(studentIndex);
					break;
				}
				default: {
					adminMenu();
				}
			}
		}		
		catch (Exception e) {
			
			System.out.println("Wrong input, try again");
			adminMenu();
		}	
	}
	
	
	/** delete student from database
	 * and back to admin menu
	 */
	public static void deleteStudent(int studentIndex) {
		
		allStudents.remove(studentIndex);
		adminMenu();
	}
	
	
	/** print student with or without payment
	 * and back to admin menu
	 */
	public static void printPayments() {
		
		System.out.println("***********************************");
		System.out.println(" Press 1 to print students who didn't pay  \n "
				+ "Press 2 to print students who have paid");
		System.out.println("*********************************** \n");
		
		String adminChoice = input.next();
		
		switch (adminChoice) {
			case "1": {
				System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
				System.out.print("---------------------------------------------------------\n");
				for (int studentIndex = 0; studentIndex < allStudents.size(); studentIndex++) {
					if (!allStudents.get(studentIndex).getFee()) {
						allStudents.get(studentIndex).print();
					}
				}
				System.out.print("---------------------------------------------------------\n \n");					
				break;
			}
			case "2": {
				System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
				System.out.print("---------------------------------------------------------\n");
				for (int studentIndex = 0; studentIndex < allStudents.size(); studentIndex++) {
					if (allStudents.get(studentIndex).getFee()) {
						allStudents.get(studentIndex).print();
					}
				}
				System.out.print("---------------------------------------------------------\n \n");					
				break;
			}
			default: {
				System.out.println("Wrong input, try again");
				printPayments();
			}
		}
		adminMenu();
	}
	
	
	/** make a payment for student
	 * print message if chosen student has paid
	 * print error message if user choose invalid option
	 * back to admin menu
	 */
	public static void makePayment() {
		
		System.out.println("***********************************");
		System.out.println(" Choose student by ID \n from the list below:");
		System.out.println("***********************************\n");

		printStudentsDetails(allStudents);

		try {
			
			int studentIndex = input.nextInt() -1;
			
/*			next code line is just for visual reason 
 			'kick' catch() method in case of any error
  		    before any line is printed to the console */ 
			allStudents.get(studentIndex);

			System.out.print("   ID \t   Student Name \t Course \t Fee Paid \n");
			System.out.print("---------------------------------------------------------\n");
			allStudents.get(studentIndex).print();
			System.out.print("---------------------------------------------------------\n \n");
			
			System.out.println("***********************************");
			System.out.println(" Press 'y' to make a payment \n for abowe student \n"
					+ " or any key to back to admin menu");
			System.out.println("*********************************** \n");
			
			String adminChoice = input.next();
			if (adminChoice.equals("y")) {
				if (allStudents.get(studentIndex).getFee()) {
					System.out.println("This student has paid all ready");
					adminMenu();
				}
				else {
					payFee(studentIndex);
				}
			}
		}		
		catch (Exception e) {
			
			System.out.println("Wrong input, try again");
			makePayment();
		}
		adminMenu();
	}
	
	
	/** method print Error message with instructions for user
	 * is called from setName() method if user input is wrong type
	 */
	public static void printNameErrorMessage() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(" Enter one word for name and one for surname, \n "
				+ "if name or surname has more than one word \n seperate them by dash key '-' \n"
				+ "only alphabetic characters are allowed");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");		
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
	 * correctFormat(Fred 32)
	 * >>> false
	 */
	static boolean correctFormat(String userInput) {
		int loopLength = userInput.length();
		
		for (int charIndx = 0; charIndx < loopLength; charIndx++) {	
			char spaceCharacter = ' ';
			char dashCharacter = '-';
			char charInput = userInput.charAt(charIndx);
			if (!Character.isLetter(charInput) && charInput != spaceCharacter 
					&& charInput != dashCharacter) {
				return false;
			}
		}
		return true;
	}
	
	/** helper function to format student name
	 * returns name with Upper Case first letter of name and surname
	 * and Lower Case the rest of the name and surname
	 * remove Leading and Trailing Spaces
	 * 
	 * (String) --> (String)
	 * 
	 * formatName(" joE bloGGs")
	 * >>> "Joe Bloggs"
	 * 
	 * formatName("AlEX gReen  ")
	 * >>> "Alex Green"
	 * 
	 * formatName("  jaMes bond")
	 * >>> "James Bond"
	 */
	public static String formatName(String stringToFormat) {
		
		String returnName = "";
		char spaceCharacter = ' ';
		String studentName = stringToFormat.trim();
		int loopLength = studentName.length();
		
		for (int charIndex = 0; charIndex < loopLength; charIndex++) {
			char charInput = studentName.charAt(charIndex);
			if (charIndex == 0) {
				returnName += Character.toUpperCase(charInput);
			}
			else if (charInput == spaceCharacter) {
				returnName += spaceCharacter;
				returnName += Character.toUpperCase(studentName.charAt(charIndex + 1));
				charIndex++;
			}
			else {
				returnName += Character.toLowerCase(charInput);
			}
		}
		return returnName;
	}
	
	
	/** helper function to check if inserted amount of money is in correct format 
	 * returns true if dot is maximum at decimal argument and false otherwise
	 * 
	 * (String, int) --> (boolean)
	 * 
	-- in this case maximum 2 decimal places --
	
	isMaxDecimal("123.23", 2)
	>>> true
	
	isMaxDecimal("2.123.23", 2)
	>>> true
	
	isMaxDecimal("1.2323", 2)
	>>> false
	*/
	static boolean isMaxDecimal(String userInput, int decimal) {
		int loopLength = userInput.length();
		char testChar = '.';
		int decimalPlaces = decimal;
		
		for (int charIndx = (loopLength -1); charIndx >= 0; charIndx--) {
			char charInput = userInput.charAt(charIndx);

			if (charInput == testChar) {
				if (decimalPlaces >= 0) {
					return true;
				}
				else {
					return false;
				}
			}
			decimalPlaces -= 1;
		}
		return true;
	}
	
	/** helper function to check if character/s in user input are in correct format
	 * returns true if all characters in user input are either integer or dot, 
	 * dot is not first character and all dot's are separated with one or more integers, 
	 * return false otherwise 
	 * 
	 * (String) --> (boolean)
	 * 
	 * correctFormat(234.5)
	 * >>> true
	 * 
	 * correctFormat(12)
	 * >>> true
	 * 
	 * correctFormat(abc)
	 * >>> false
	 */
	static boolean correctNumericFormat(String userInput) {
		int loopLength = userInput.length();
		char dotChar = '.';
		int dotIndx = 0;
		
		for (int charIndx = 0; charIndx < loopLength; charIndx++) {	
			char charInput = userInput.charAt(charIndx);
			if (!Character.isDigit(charInput) && charInput != dotChar) {
				return false;
			}
			else if (charInput == dotChar) {
				if ((charIndx - dotIndx) < 2 && charIndx != 1) {
					return false;
				}
				else {
					dotIndx = charIndx;
				}
			}
		}
		return true;
	}


	 public static void main(String[] args) {
		
		mainMenu();
	}
	 
}
