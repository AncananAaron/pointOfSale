//Cashier Table Storage
public class Cashier {
	private int cashier_ID, emp_ID, cashier_Number;
	private String datetime;
	public Cashier(int cashier_ID, int cashier_Number, int emp_ID, String datetime) 
	{
		this.cashier_ID = cashier_ID;
		this.cashier_Number = cashier_Number;
		this.emp_ID = emp_ID;
		this.datetime = datetime;
	}
	
	public int getempID() {
		return emp_ID;
	}
	public int getcashierID() {
		return cashier_ID;
	}
	public int getcashierNum() {
		return cashier_Number;
	}
	public String getdatetime(){
		return datetime;
	}
}
