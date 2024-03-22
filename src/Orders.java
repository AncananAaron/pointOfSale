
public class Orders {
	private String productName, quantity, Price; 
	private int  product_ID;
	

	public Orders(int product_ID, String string, String string2, String int1) {
		this.product_ID = product_ID;
		this.productName = string;
		this.quantity = string2;
		this.Price = int1;
	}
	public int getprodID() {
		return product_ID;
	}
	public String getprodName() {
		return productName;
	}
	public String getquantity() {
		return quantity;
	}
	public String getprice() {
		return Price;
	}
}
