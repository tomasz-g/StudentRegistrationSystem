
public class Student {
	
	private int studentId;
	private String studentName;
	private String studentCourse;
	private boolean feePaid;
	
	public Student() {
		
		this.studentId = 0;
		this.studentName = null;
		this.studentCourse = null;		
		feePaid = false;
	}
	
	public int getId() {
		return studentId;
	}	
	
	public void setId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return studentName;
	}
	
	public void setName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getCourse() {
		return studentCourse;
	}
	
	public void chooseCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}
	
	public boolean getFee() {
		return feePaid;
	}
	
	public void payFee() {
		feePaid = true;
	}
	
	public void print() {
		String studentFee;
		if (feePaid == true) {
			studentFee = "yes";
		}  
		else {
			studentFee = "no";
		}
		String formatedId = String.format("%03d", studentId);
		System.out.print("| Student ID: " + formatedId + " | " + " Student Name: " + studentName 
				+ " | " + "Course: " + studentCourse + " | "
				+ "Fee Paid: " + studentFee + " |\n");
	}

}
