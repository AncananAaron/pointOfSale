import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AddProduct extends JFrame implements ActionListener {

	JPanel contentPane;
	JLabel ProductName;
	JLabel ProductSize;
	JLabel ProductPrice;
	JTextField ProductNameTF;
	JTextField ProductSizeTF;
	JTextField ProductPriceTF;
	JButton AddButton;
	public static AddProduct addproduct;
	//=============================Connection
	private static final String URL = "jdbc:mysql://localhost:3306/artcafe";
	private static final String username = "root";
	private static final String password = "FXQ6F3U5)";
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int q;
	public String foodquery;

	public AddProduct(String n) 
	{
		super(n);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProductName = new JLabel("Product Name");
		ProductName.setBounds(10, 24, 94, 14);
		contentPane.add(ProductName);
		
		ProductSize = new JLabel("Product Size");
		ProductSize.setBounds(10, 72, 94, 14);
		contentPane.add(ProductSize);
		
		ProductPrice = new JLabel("Product Price");
		ProductPrice.setBounds(10, 116, 94, 14);
		contentPane.add(ProductPrice);
		
		ProductNameTF = new JTextField();
		ProductNameTF.setColumns(10);
		ProductNameTF.setBounds(114, 21, 166, 20);
		contentPane.add(ProductNameTF);
		
		ProductSizeTF = new JTextField();
		ProductSizeTF.setColumns(10);
		ProductSizeTF.setBounds(114, 69, 166, 20);
		contentPane.add(ProductSizeTF);
		
		ProductPriceTF = new JTextField();
		ProductPriceTF.setColumns(10);
		ProductPriceTF.setBounds(114, 113, 166, 20);
		contentPane.add(ProductPriceTF);
		
		
		AddButton = new JButton("Add");
		AddButton.setBounds(159, 161, 89, 23);
		contentPane.add(AddButton);
		AddButton.addActionListener(this);
		setVisible(true);
		
		//Closing the JFrame only
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent x) {
		if(x.getSource() == AddButton) {
			String prodName = ProductNameTF.getText();
			String size = ProductSizeTF.getText();
			String price = ProductPriceTF.getText();
			if(prodName.isEmpty() || size.isEmpty() || price.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Record Failed , NO INPUT", "Alert",JOptionPane.WARNING_MESSAGE);
				if(prodName.isEmpty())
					ProductNameTF.requestFocus();
				else if(size.isEmpty())
					ProductSizeTF.requestFocus();
				else
					ProductPriceTF.requestFocus();
			}
			else 
			{
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement(foodquery);
					pst.setString(1, prodName);
					pst.setString(2, size);
					pst.setString(3, price);
					q = pst.executeUpdate();
					
					if(q == 1) {
						JOptionPane.showMessageDialog(this, "Product Record Added");
						ProductNameTF.setText("");
						ProductSizeTF.setText("");
						ProductPriceTF.setText("");
						}
					else 
						JOptionPane.showMessageDialog(this, "Adding Product Failed", "Alert",JOptionPane.WARNING_MESSAGE);
					dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		addproduct = new AddProduct ("");
	}
}