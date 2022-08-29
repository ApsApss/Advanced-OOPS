package driver;

/**
 * Student Structure with name, redId and gpa
 */
public class Student {
	private String name;
	private int redId;
	private double gpa;

	/**
	 * @param name
	 * @param redId
	 * @param gpa
	 */
	public Student(String name, int redId, double gpa) {
		this.name = name;
		this.redId = redId;
		this.gpa = gpa;
	}

	public Student() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRedId() {
		return redId;
	}

	public void setRedId(int redId) {
		this.redId = redId;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
}
