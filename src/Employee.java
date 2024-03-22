//Employee Table Storage
public class Employee {
	private int emp_ID;
	private String empName, empAddress, empNumber;
	
	public Employee(int emp_ID, String empName, String empAddress, String empNumber) 
	{
		this.emp_ID = emp_ID;
		this.empName = empName;
		this.empAddress = empAddress;
		this.empNumber = empNumber;
	}
	
	public int getempID() {
		return emp_ID;
	}
	
	public String getempName() {
		return empName;
	}
	
	public String getempAddress() {
		return empAddress;
	}
	
	public String getempNum() {
		return empNumber;
	}
}
