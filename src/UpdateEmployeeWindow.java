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
public class UpdateEmployeeWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JTextField empNameTF;
	public JTextField empAddressTF;
	public JTextField empNumTF;
	JButton SaveButton;
	public static UpdateEmployeeWindow updateemp;
	
	//=============================Connection
	private static final String URL = "jdbc:mysql://localhost:3306/artcafe";
	private static final String username = "root";
	private static final String password = "FXQ6F3U5)";
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	public String selectitem;
	int q;

	public UpdateEmployeeWindow(String n) 
	{
		super(n);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel EmpNameLabel = new JLabel("Employee Name");
		EmpNameLabel.setBounds(37, 70, 94, 14);
		contentPane.add(EmpNameLabel);
		
		JLabel EmpAddress = new JLabel("Employee Address");
		EmpAddress.setBounds(37, 109, 94, 14);
		contentPane.add(EmpAddress);
		
		JLabel lblEmployeeNumber = new JLabel("Employee Number");
		lblEmployeeNumber.setBounds(37, 150, 94, 14);
		contentPane.add(lblEmployeeNumber);
		
		empNameTF = new JTextField();
		empNameTF.setBounds(141, 67, 211, 20);
		contentPane.add(empNameTF);
		empNameTF.setColumns(10);
		
		empAddressTF = new JTextField();
		empAddressTF.setColumns(10);
		empAddressTF.setBounds(141, 106, 211, 20);
		contentPane.add(empAddressTF);
		
		empNumTF = new JTextField();
		empNumTF.setColumns(10);
		empNumTF.setBounds(141, 147, 211, 20);
		contentPane.add(empNumTF);
		
		SaveButton = new JButton("Save");
		SaveButton.setBounds(161, 206, 89, 23);
		contentPane.add(SaveButton);
		SaveButton.addActionListener(this);
		setVisible(true);
		
		//Close Button
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//==============================================================Action Performed==============================================
	public void actionPerformed(ActionEvent x) {
		if(x.getSource() == SaveButton) {
			String empName = empNameTF.getText();
			String empAddress = empAddressTF.getText();
			String empNumber = empNumTF.getText();
			if(empName.isEmpty() || empAddress.isEmpty() || empNumber.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Record Failed , NO INPUT", "Alert",JOptionPane.WARNING_MESSAGE);
				if(empName.isEmpty())
					empNameTF.requestFocus();
				else if(empAddress.isEmpty())
					empAddressTF.requestFocus();
				else
					empNumTF.requestFocus();
			}
			else 
			{
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("UPDATE artcafe.employee SET empName=?, empAddress=?,empNumber=? WHERE emp_ID =?");
					pst.setString(1, empName);
					pst.setString(2, empAddress);
					pst.setString(3, empNumber);
					pst.setString(4, selectitem);
					
					q = pst.executeUpdate();
					
					if(q == 1) {
						JOptionPane.showMessageDialog(this, "Record Updated");
						empNameTF.setText("");
						empAddressTF.setText("");
						empNumTF.setText("");
						empNameTF.requestFocus();
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
	
	
	//Maint Function
	public static void main(String[] args) 
	{
		updateemp = new UpdateEmployeeWindow ("");
		
	}
}