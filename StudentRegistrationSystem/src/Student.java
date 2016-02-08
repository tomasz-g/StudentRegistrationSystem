
public class Student {
	
	private int studentId;
	private String studentName;
	private String studentCourse;
	private boolean feePaid;
	
	public Student(int studentId, String studentName, String studentCourse) {
		
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentCourse = studentCourse;		
		feePaid = false;
	}
	
	public int getId() {
		return studentId;
	}	
	
	void changeId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return studentName;
	}
	
	public void changeName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getCourse() {
		return studentCourse;
	}
	
	public void changeCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}
	
	public boolean getFee() {
		return feePaid;
	}
	
	public void payFee(boolean feePaid) {
		this.feePaid = feePaid;
	}
	
	public void print() {
		String studentFee;
		if (feePaid) {
			studentFee = "yes";
		}
		else {
			studentFee = "no";
		}
		System.out.println("Student ID: " + studentId + "\t" + "Name: " + studentName + "\t" + "Course: " + studentCourse + "\t"
				+ "Fee Paid: " + studentFee);
	}

}
