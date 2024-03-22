//Product Food Storage
public class ProductFood {
	private int product_ID, price;
	private String productName, size;
	
	public ProductFood(int product_ID, String productName, String size, int price) {
		this.product_ID = product_ID;
		this.productName = productName;
		this.size = size;
		this.price = price;
	}
	
	public int getprodID() {
		return product_ID;
	}
	public String getprodName() {
		return productName;
	}
	public String getsize() {
		return size;
	}
	public int getprice() {
		return price;
	}
}
