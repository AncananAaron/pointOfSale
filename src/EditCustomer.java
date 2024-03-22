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
public class EditCustomer extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JTextField CustNameTF;
	public JTextField CustNumTF;
	JButton SaveButton;
	public static EditCustomer editcustomer;
	
	//=============================Connection variables
	private static final String URL = "jdbc:mysql://localhost:3306/artcafe";
	private static final String username = "root";
	private static final String password = "FXQ6F3U5)";
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int q;
	public String selectitem;

	public EditCustomer(String n) 
	{
		super(n);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CustomerName = new JLabel("Customer Name");
		CustomerName.setBounds(10, 49, 94, 14);
		contentPane.add(CustomerName);
		
		JLabel CustNumLabel = new JLabel("Customer Number");
		CustNumLabel.setBounds(10, 105, 94, 14);
		contentPane.add(CustNumLabel);
		
		CustNameTF = new JTextField();
		CustNameTF.setColumns(10);
		CustNameTF.setBounds(114, 46, 131, 20);
		contentPane.add(CustNameTF);
		
		CustNumTF = new JTextField();
		CustNumTF.setColumns(10);
		CustNumTF.setBounds(114, 102, 131, 20);
		contentPane.add(CustNumTF);
		
		SaveButton = new JButton("Save");
		SaveButton.setBounds(138, 153, 89, 23);
		contentPane.add(SaveButton);
		SaveButton.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

//==============================================================Action Performed==============================================
	public void actionPerformed(ActionEvent x) {
		if(x.getSource() == SaveButton) {
			String custName = CustNameTF.getText();
			String custNum = CustNumTF.getText();
			if(custName.isEmpty() || custNum.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Record Failed , NO INPUT", "Alert",JOptionPane.WARNING_MESSAGE);
				if(custName.isEmpty())
					CustNameTF.requestFocus();
				else
					CustNumTF.requestFocus();
			}
			else 
			{
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("UPDATE artcafe.customer SET customerName=?, customerNumber=? WHERE customer_ID=?");
					pst.setString(1, custName);
					pst.setString(2, custNum);
					pst.setString(3, selectitem);
					
					q = pst.executeUpdate();
					
					if(q == 1) {
						JOptionPane.showMessageDialog(this, "Record Updated");
						CustNameTF.setText("");
						CustNumTF.setText("");
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
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
		editcustomer = new EditCustomer ("");
	}
}
