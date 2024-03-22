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
public class AddCashier extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JTextField CashierNumberTF;
	public JTextField Emp_ID;
	JButton SaveButton;
	public static AddCashier addcashier;
	
	//=============================Connection
	private static final String URL = "jdbc:mysql://localhost:3306/artcafe";
	private static final String username = "root";
	private static final String password = "FXQ6F3U5)";
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int q;

	public AddCashier(String n) 
	{
		super(n);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CashierNumber = new JLabel("Cashier Number");
		CashierNumber.setBounds(10, 49, 94, 14);
		contentPane.add(CashierNumber);
		
		JLabel EmployeeID = new JLabel("Employee ID: ");
		EmployeeID.setBounds(10, 105, 94, 14);
		contentPane.add(EmployeeID);
		
		CashierNumberTF = new JTextField();
		CashierNumberTF.setColumns(10);
		CashierNumberTF.setBounds(114, 46, 131, 20);
		contentPane.add(CashierNumberTF);
		
		Emp_ID = new JTextField();
		Emp_ID.setColumns(10);
		Emp_ID.setBounds(114, 102, 131, 20);
		contentPane.add(Emp_ID);
		
		SaveButton = new JButton("Save");
		SaveButton.setBounds(138, 153, 89, 23);
		contentPane.add(SaveButton);
		SaveButton.addActionListener(this);
		setVisible(true);
		
		//Close button
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
	//==============================================================Action Performed==============================================
	public void actionPerformed(ActionEvent x) {
		if(x.getSource() == SaveButton) {
			String cashierNumber = CashierNumberTF.getText();
			String emp_ID = Emp_ID.getText();
			if(cashierNumber.isEmpty() || emp_ID.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Record Failed , NO INPUT", "Alert",JOptionPane.WARNING_MESSAGE);
				if(cashierNumber.isEmpty())
					CashierNumberTF.requestFocus();
				else
					Emp_ID.requestFocus();
			}
			else 
			{
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.cashier(cashier_Number, emp_ID, Date_Time)VALUES(?,?,now())");
					pst.setString(1, cashierNumber);
					pst.setString(2, emp_ID);
					
					q = pst.executeUpdate();
					
					if(q == 1) {
						JOptionPane.showMessageDialog(this, "Record Successfully");
						CashierNumberTF.setText("");
						Emp_ID.setText("");
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
	
	//Main Function
	public static void main(String[] args) 
	{
		addcashier = new AddCashier ("");
	}
}
