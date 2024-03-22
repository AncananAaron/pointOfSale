
//Customer Table Storage
public class Customer {
	private int customer_ID;
	private String customerName, customerNumber;
	
	public Customer(int customer_ID, String customerName, String customerNumber) 
	{
		this.customer_ID = customer_ID;
		this.customerName = customerName;
		this.customerNumber = customerNumber;
	}
	public int getcustID() {
		return customer_ID;
	}
	
	public String getcustName() {
		return customerName;
	}
	
	public String getcustNum() {
		return customerNumber;
	}
}
