package entity;

public class Score {
	private int id;
	private Employee emp;
	private Department dep;
	private Project pro;
	private Integer value;
	private String grade;
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public Project getPro() {
		return pro;
	}

	public void setPro(Project pro) {
		this.pro = pro;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
