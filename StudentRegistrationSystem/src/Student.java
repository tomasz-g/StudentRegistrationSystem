
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
		
		String formatedId = String.format("%03d", studentId);
		String formatedName = studentName;
		int nameSpace = (18 - studentName.length());
		for (int i=0; i<nameSpace; i++) {
			formatedName += " ";
		}
		String studentFee = " no  ";
		if (feePaid == true) {
			studentFee = " yes ";
		}  
		
		System.out.print("|  " + formatedId + "  | " + formatedName 
				+ " | " + studentCourse + " | " + studentFee + " |\n");	
	}

}
