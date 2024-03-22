import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField UsernameTF;
	JLabel LoginLabel;
	JButton LoginButton;
	private JPasswordField passwordField;
	Connection conn;
	IMDB imdb;

	public Login(String n) {
		super (n);
		//Window Layout UI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(508, 284, 300, 250);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Login Label Title
		LoginLabel = new JLabel("Login");
		LoginLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		LoginLabel.setBounds(119, 11, 60, 25);
		contentPane.add(LoginLabel);
		
		//Username Label UI
		JLabel Username = new JLabel("Username: ");
		Username.setFont(new Font("Tahoma", Font.BOLD, 13));
		Username.setBounds(36, 54, 73, 25);
		contentPane.add(Username);
		
		//Password Label UI
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.BOLD, 13));
		password.setBounds(36, 101, 73, 25);
		contentPane.add(password);
		
		//User name Text Field
		UsernameTF = new JTextField();
		UsernameTF.setBounds(119, 57, 96, 20);
		contentPane.add(UsernameTF);
		UsernameTF.setColumns(10);
		
		//Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 104, 96, 20);
		contentPane.add(passwordField);
		
		//Login Button
		LoginButton = new JButton("Login");
		LoginButton.setBounds(119, 147, 89, 23);
		contentPane.add(LoginButton);
		LoginButton.addActionListener(this);
		
		setVisible(true);
	}
	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent x) 
	{
		if(x.getSource() == LoginButton) {
			String URL = "jdbc:mysql://localhost:3306/artcafe";
			String Username = UsernameTF.getText();
			String password = passwordField.getText();
			
			try {
				conn = DriverManager.getConnection(URL, Username, password);
				JOptionPane.showMessageDialog(null, "Connected Successfully");
				dispose();
				imdb = new IMDB("Alitaptap ArtCafe Database Management System");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Login function = new Login("Login");
	    function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
