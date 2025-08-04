package ch06;
//Page393, P01
class Student {
	private String name; 
	private int idNum;
	
	public Student(String name, int idNum) {
		this.idNum = idNum;
		this.name = name;
	}
	
	public String getName() { return name; }
	public int getIdNum() { return idNum; }
	
	public String toString() {
		return "학번이 " + idNum + "인 " + name;
	}
	
	public boolean equals(Student c) {
		if(getName().equals(c.getName()) && getIdNum() == c.getIdNum()) return true;
		else return false;
	}
}
public class StudentApp {

	public static void main(String[] args) {
		Student a = new Student("황기태", 23);
		Student b = new Student("황기태", 77);
		System.out.println(a);
		if (a.equals(b))			
			System.out.println("같은 학생입니다.");
		else
			System.out.println("다른 학생입니다.");
	}

}
