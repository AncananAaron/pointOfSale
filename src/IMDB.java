import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.border.CompoundBorder;


@SuppressWarnings("serial")
public class IMDB extends JFrame implements ActionListener {
	JPanel contentPane = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel OrderPanel = new JPanel();
	
	//==================================================================Order Tab Variables
	JLabel OrderTitle = new JLabel("Alitaptap Art Caf\u00E9");
	JLabel CashierIDLabel = new JLabel("Cashier ID");
	JTextField cashIDtxtfd = new JTextField();;
	JLabel OrderIDLabel = new JLabel("Order ID");
	JTextField orderIDtxtfd = new JTextField();
	JPanel MenuPanel = new JPanel();
	JScrollPane OrderPane = new JScrollPane();
	JTable OrderTable = new JTable();
	JButton ShowOrderButton = new JButton("Show Order");
	JLabel TotalPricelabel = new JLabel("Total Price:");
	JTextField TotalPricetxtFD = new JTextField();
	JButton RemoveOrderBtn = new JButton("Remove Order");
	JButton OrderCompletBTN = new JButton("Order Complete");
	JButton CancelOrderBtn = new JButton("Cancel Order");
	JButton NewOrderButton = new JButton("New Order");
	JButton TotalPriceButton = new JButton("Total Price");
	JLabel PaymentLabel = new JLabel("Payment");
	JLabel CustIDLabel = new JLabel("Customer ID");
	JTextField ordercustIDTF;
	JTextField PaymenttxtFD = new JTextField();
	JTextField ChangetxtFD = new JTextField();
	JLabel ChangeLabel = new JLabel("Change");
	JPanel ProductPanel = new JPanel();
	//==============================================Menu Button
	JButton BarBlendButton = new JButton("Barista Blend");
	JButton MangoButton = new JButton("MangCheesecake");
	JButton BrazoButton = new JButton("Brazo Cake");
	JButton MacButton = new JButton("Macaroons");
	JButton SpanishLatte = new JButton("SpanishLatte");
	JButton CafeLatte = new JButton("CafeLatte");
	JButton AmericanoButton = new JButton("Americano");
	JButton NachosButton = new JButton("Nachos");
	//==============================================QUANTITY TEXT FIELD
	
	JTextField BBQty;
	JTextField AmericanotQTy;
	JTextField CafeLatteQTY;
	JTextField SpanishLatteQTY;
	JTextField MacQTY;
	JTextField BCQTY;
	JTextField MCQTY;
	JTextField NACHQTY;
	
	//=======================================================================Product Tab Variables
	JLabel ProductTitle = new JLabel("Alitaptap Art Caf\u00E9");
	JLabel FoodLabel = new JLabel("Food");
	JLabel DrinkLabel = new JLabel("Drink");
	JScrollPane FoodPane = new JScrollPane();
	JTable FoodTable = new JTable();
	JButton AddProdButton1 = new JButton("Add Product");
	JButton EditProdButton1 = new JButton("Edit Product");
	JButton RemoveProdButton1 = new JButton("Remove Product");
	JScrollPane DrinkPane = new JScrollPane();
	JTable DrinkTable = new JTable();
	JButton AddProdButton2 = new JButton("Add Product");
	JButton RemoveProdButton2 = new JButton("Remove Product");
	JButton EditProdButton2 = new JButton("Edit Product");
	JButton refreshProduct = new JButton();
	
	//=======================================================================Customer Tab Variables 
	JPanel CustomerPanel = new JPanel();
	JLabel CustomerTitle = new JLabel("Alitaptap Art Caf\u00E9");
	JLabel CustomerLabel = new JLabel("Customer Record");
	JScrollPane CustomerPane = new JScrollPane();
	JTable CustomerTable = new JTable();
	JButton DeleteCustButton = new JButton("Delete  Customer");
	JButton AddCustButton = new JButton("Add  Customer");
	JButton EditCustButton = new JButton("Edit  Customer");
	JButton refreshCust = new JButton();
	
	//=======================================================================Cashier Tab Variables
	JPanel CashierPanel = new JPanel();
	JLabel CashierTitle = new JLabel("Alitaptap Art Caf\u00E9");
	JLabel CashierLabel = new JLabel("Cashier Record");
	JScrollPane CashierPane = new JScrollPane();
	JTable CashierTable = new JTable();
	JButton AddCashButton = new JButton("Add  Cashier");
	JButton RemoveCashButton = new JButton("Remove  Cashier");
	JButton EditCashButton = new JButton("Edit Cashier");
	JButton refreshCashier = new JButton();
	
	//=======================================================================Employee Tab Variables
	JPanel EmployeePanel = new JPanel();
	JLabel EmployeeTitle = new JLabel("Alitaptap Art Caf\u00E9");
	JLabel EmployeeLabel = new JLabel("Employee Record");
	JScrollPane EmployeePane = new JScrollPane();
	JTable EmployeeTable = new JTable();
	JButton AddEmpButton = new JButton("Add Employee");
	JButton RemoveEmpButton = new JButton("Remove Employee");
	JButton EditEmpButton = new JButton("Edit Employee");
	JButton refresh = new JButton();
	
	
	//=================================================================================================Other Window Variables===================
	//Employee
	EmployeeWindow empWindow;
	UpdateEmployeeWindow updateemployee;
	//Cashier
	AddCashier addcashier;
	UpdateCashier upcashier;
	//Customer
	AddCustomer addcustomer;
	EditCustomer editcustomer;
	//Product
	AddProduct addproduct;
	EditProduct editproduct;
	
	//========================================================================Connection Variables
	private static final String URL = "jdbc:mysql://localhost:3306/artcafe";
	private static final String username = "root";
	private static final String password = "FXQ6F3U5)";
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int q, i, id, selecteditem, deleteitem;
	String rowselected = "";
	double sum = 0;
	
	
	
	//GUI
	public IMDB(String n) 
	{
		super(n);
		//Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 1320, 700);
		setVisible(true);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Tab
		tabbedPane.setBounds(0, 0, 1306, 662);
		contentPane.add(tabbedPane);
		OrderPanel.setBorder(new CompoundBorder());
		OrderPanel.setBackground(SystemColor.menu);
		
		//==============================================Order Tab UI================================================================
		//OrderTab
		tabbedPane.addTab("Order", null, OrderPanel, null);
		tabbedPane.setEnabledAt(0, true);
		OrderPanel.setLayout(null);
		
		//Title of Order
		OrderTitle.setFont(new Font("Tahoma", Font.BOLD, 55));
		OrderTitle.setBounds(352, 0, 538, 78);
		OrderPanel.add(OrderTitle);
		
		//Cashier Number Label
		CashierIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		CashierIDLabel.setBounds(45, 94, 170, 25);
		OrderPanel.add(CashierIDLabel);
		
		CustIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		CustIDLabel.setBounds(269, 94, 170, 25);
		OrderPanel.add(CustIDLabel);
		
		//Cashier Number TextField
		cashIDtxtfd.setBounds(178, 89, 45, 30);
		OrderPanel.add(cashIDtxtfd);
		cashIDtxtfd.setColumns(10);
		
		//Employee ID Label
		OrderIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		OrderIDLabel.setBounds(631, 94, 152, 25);
		OrderPanel.add(OrderIDLabel);
		
		//Employee ID TextField
		orderIDtxtfd.setBounds(752, 89, 120, 33);
		OrderPanel.add(orderIDtxtfd);
		orderIDtxtfd.setColumns(10);
		
		//Menu Panel
		MenuPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MenuPanel.setBounds(45, 140, 538, 471);
		OrderPanel.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		//Buttons in Menu
		//Barista Blend Button
		BarBlendButton.setBounds(24, 79, 99, 59);
		MenuPanel.add(BarBlendButton);
		BarBlendButton.addActionListener(this);
		
		//American Button
		AmericanoButton.setBounds(150, 79, 99, 59);
		MenuPanel.add(AmericanoButton);
		AmericanoButton.addActionListener(this);
		
		//Cafe Latte Button
		CafeLatte.setBounds(281, 79, 99, 59);
		MenuPanel.add(CafeLatte);
		CafeLatte.addActionListener(this);
		
		//Spanish Latte Button
		SpanishLatte.setBounds(409, 79, 99, 59);
		MenuPanel.add(SpanishLatte);
		SpanishLatte.addActionListener(this);
		
		//Macaroon Button
		MacButton.setBounds(24, 272, 99, 59);
		MenuPanel.add(MacButton);
		MacButton.addActionListener(this);
		
		//Brazo Cake Button
		BrazoButton.setBounds(150, 272, 99, 59);
		MenuPanel.add(BrazoButton);
		BrazoButton.addActionListener(this);
		
		//Mango Button
		MangoButton.setBounds(281, 272, 99, 59);
		MenuPanel.add(MangoButton);
		MangoButton.addActionListener(this);
		
		//Nachos Button
		NachosButton.setBounds(409, 272, 99, 59);
		MenuPanel.add(NachosButton);
		NachosButton.addActionListener(this);
		
		//Barista Blend Text Field
		BBQty = new JTextField();
		BBQty.setBounds(52, 144, 43, 27);
		MenuPanel.add(BBQty);
		BBQty.setColumns(10);
		
		//Americano quantity text field
		AmericanotQTy = new JTextField();
		AmericanotQTy.setColumns(10);
		AmericanotQTy.setBounds(177, 144, 43, 27);
		MenuPanel.add(AmericanotQTy);
		
		//CafeLatte Text Field
		CafeLatteQTY = new JTextField();
		CafeLatteQTY.setColumns(10);
		CafeLatteQTY.setBounds(314, 149, 43, 27);
		MenuPanel.add(CafeLatteQTY);
		
		//SpanishLatte Quantity text field
		SpanishLatteQTY = new JTextField();
		SpanishLatteQTY.setColumns(10);
		SpanishLatteQTY.setBounds(439, 147, 43, 27);
		MenuPanel.add(SpanishLatteQTY);
		
		//Macaroons Text Field
		MacQTY = new JTextField();
		MacQTY.setColumns(10);
		MacQTY.setBounds(52, 342, 43, 27);
		MenuPanel.add(MacQTY);
		
		//Brazo Cake Text field
		BCQTY = new JTextField();
		BCQTY.setColumns(10);
		BCQTY.setBounds(177, 342, 43, 27);
		MenuPanel.add(BCQTY);
		
		//mango text field
		MCQTY = new JTextField();
		MCQTY.setColumns(10);
		MCQTY.setBounds(314, 342, 43, 27);
		MenuPanel.add(MCQTY);
		
		//Nacho text field 
		NACHQTY = new JTextField();
		NACHQTY.setColumns(10);
		NACHQTY.setBounds(439, 342, 43, 27);
		MenuPanel.add(NACHQTY);
		
		//Order Panel
		OrderPane.setBounds(631, 140, 621, 253);
		OrderPanel.add(OrderPane);
		
		OrderTable.setShowVerticalLines(false);
		
		//Order Table
		OrderPane.setViewportView(OrderTable);
		//Inside the Table
		OrderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product", "Quantity", "Price"
			}
		));
		OrderTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		OrderTable.getColumnModel().getColumn(0).setMinWidth(12);
		OrderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					selecteditem = OrderTable.getSelectedRow();
					rowselected = OrderTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		//Total Price Label
		TotalPricelabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		TotalPricelabel.setBounds(890, 418, 130, 30);
		OrderPanel.add(TotalPricelabel);
	
		//Total Price Text Field
		TotalPricetxtFD.setBounds(1027, 421, 202, 32);
		OrderPanel.add(TotalPricetxtFD);
		TotalPricetxtFD.setColumns(10);
		
		//Remove Order Button
		RemoveOrderBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RemoveOrderBtn.setBounds(631, 428, 179, 47);
		OrderPanel.add(RemoveOrderBtn);
		RemoveOrderBtn.addActionListener(this);
		
		//Order Complete Button
		OrderCompletBTN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OrderCompletBTN.setBounds(1050, 564, 179, 47);
		OrderPanel.add(OrderCompletBTN);
		CancelOrderBtn.addActionListener(this);
		
		//Cancel Order Button
		CancelOrderBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		CancelOrderBtn.setBounds(631, 493, 179, 47);
		OrderPanel.add(CancelOrderBtn);
		OrderCompletBTN.addActionListener(this);
		
		
		//Payment Label
		PaymentLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		PaymentLabel.setBounds(890, 459, 107, 30);
		OrderPanel.add(PaymentLabel);
		
		//Payment TextField
		PaymenttxtFD.setColumns(10);
		PaymenttxtFD.setBounds(1027, 464, 202, 30);
		OrderPanel.add(PaymenttxtFD);
		
		//Change TextField
		ChangetxtFD.setColumns(10);
		ChangetxtFD.setBounds(1027, 504, 202, 33);
		OrderPanel.add(ChangetxtFD);
		
		//Change Label
		ChangeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ChangeLabel.setBounds(890, 501, 107, 30);
		OrderPanel.add(ChangeLabel);
		
		//New Order Button
		NewOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewOrderButton.setBounds(631, 564, 179, 47);
		OrderPanel.add(NewOrderButton);
		NewOrderButton.addActionListener(this);
	
		//order cust ID TF
		ordercustIDTF = new JTextField();
		ordercustIDTF.setColumns(10);
		ordercustIDTF.setBounds(415, 89, 45, 30);
		OrderPanel.add(ordercustIDTF);
		
		//Show Order Button
		ShowOrderButton.setBounds(1150, 99, 102, 23);
		OrderPanel.add(ShowOrderButton);
		ShowOrderButton.addActionListener(this);
		
		//Total Price Button
		TotalPriceButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TotalPriceButton.setBounds(841, 564, 179, 47);
		OrderPanel.add(TotalPriceButton);
		TotalPriceButton.addActionListener(this);
		
		//=====================================Product Tab UI==============================================================
		//Product Panel
		tabbedPane.addTab("Product", null, ProductPanel, null);
		ProductPanel.setLayout(null);
		
		//Product Title Label
		ProductTitle.setFont(new Font("Tahoma", Font.BOLD, 55));
		ProductTitle.setBounds(363, 0, 538, 78);
		ProductPanel.add(ProductTitle);
		
		//Food Label 
		FoodLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		FoodLabel.setBounds(259, 133, 133, 39);
		ProductPanel.add(FoodLabel);
		
		//Drink Label
		DrinkLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		DrinkLabel.setBounds(911, 133, 133, 49);
		ProductPanel.add(DrinkLabel);
		
		//Food Panel
		FoodPane.setBounds(50, 294, 566, 334);
		ProductPanel.add(FoodPane);
		FoodTable.setShowVerticalLines(false);
		
		
		//Food Table Panel
		FoodTable.setFont(new Font("Tahoma", Font.PLAIN, 11));
		FoodPane.setViewportView(FoodTable);
		FoodTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product Name", "Size", "Product Price"
			}
		));
		FoodTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					selecteditem = FoodTable.getSelectedRow();
					rowselected = FoodTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		//Add Product Button
		AddProdButton1.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddProdButton1.setBounds(50, 236, 166, 47);
		ProductPanel.add(AddProdButton1);
		AddProdButton1.addActionListener(this);
		
		//Edit Product Button
		EditProdButton1.setFont(new Font("Tahoma", Font.BOLD, 15));
		EditProdButton1.setBounds(450, 236, 166, 47);
		ProductPanel.add(EditProdButton1);
		EditProdButton1.addActionListener(this);
		
		//Remove Product Button
		RemoveProdButton1.setFont(new Font("Tahoma", Font.BOLD, 15));
		RemoveProdButton1.setBounds(252, 236, 166, 47);
		ProductPanel.add(RemoveProdButton1);
		RemoveProdButton1.addActionListener(this);
		
		//Drink Panel
		DrinkPane.setBounds(677, 294, 566, 334);
		ProductPanel.add(DrinkPane);
		DrinkTable.setShowVerticalLines(false);
		
		//Drink Table
		DrinkPane.setViewportView(DrinkTable);
		DrinkTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product Name", "Size", "Product Price"
			}
		));
		DrinkTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					selecteditem = DrinkTable.getSelectedRow();
					rowselected = DrinkTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		//Add Product for Drinks
		AddProdButton2.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddProdButton2.setBounds(677, 236, 166, 47);
		ProductPanel.add(AddProdButton2);
		AddProdButton2.addActionListener(this);
		
		//Remove Product for Drinks
		RemoveProdButton2.setFont(new Font("Tahoma", Font.BOLD, 15));
		RemoveProdButton2.setBounds(878, 236, 166, 47);
		ProductPanel.add(RemoveProdButton2);
		RemoveProdButton2.addActionListener(this);
		
		//Edit Product for Drinks
		EditProdButton2.setFont(new Font("Tahoma", Font.BOLD, 15));
		EditProdButton2.setBounds(1077, 236, 166, 47);
		ProductPanel.add(EditProdButton2);
		EditProdButton2.addActionListener(this);
		
		//refreshProduct
		refreshProduct.setIcon(new ImageIcon("D:\\IM\\IMDB\\img\\refresh-icon.png"));
		refreshProduct.setBounds(632, 257, 28, 26);
		ProductPanel.add(refreshProduct);
		refreshProduct.addActionListener(this);
		
		//======================================================Customer Tab UI ================================================
		//Customer Panel
		tabbedPane.addTab("Customer", null, CustomerPanel, null);
		CustomerPanel.setLayout(null);
		
		//Customer Title Label
		CustomerTitle.setFont(new Font("Tahoma", Font.BOLD, 55));
		CustomerTitle.setBounds(383, 0, 538, 78);
		CustomerPanel.add(CustomerTitle);
		
		//Customer Label 
		CustomerLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		CustomerLabel.setBounds(464, 92, 365, 47);
		CustomerPanel.add(CustomerLabel);
		
		//Customer Panel 
		CustomerPane.setBounds(278, 231, 751, 381);
		CustomerPanel.add(CustomerPane);
		CustomerTable.setShowVerticalLines(false);
		
		//Customer Table
		CustomerPane.setViewportView(CustomerTable);
		CustomerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer ID", "Customer Name", "Customer Number"
			}
		));
		CustomerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					selecteditem = CustomerTable.getSelectedRow();
					rowselected = CustomerTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		//Delete Customer Button
		DeleteCustButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		DeleteCustButton.setBounds(481, 173, 193, 47);
		CustomerPanel.add(DeleteCustButton);
		DeleteCustButton.addActionListener(this);
		
		//Add Customer Button
		AddCustButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddCustButton.setBounds(278, 173, 193, 47);
		CustomerPanel.add(AddCustButton);
		AddCustButton.addActionListener(this);
		
		//Edit Customer Button
		EditCustButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		EditCustButton.setBounds(684, 173, 193, 47);
		CustomerPanel.add(EditCustButton);
		EditCustButton.addActionListener(this);
		refreshCust.setIcon(new ImageIcon("D:\\IM\\IMDB\\img\\refresh-icon.png"));
		
		refreshCust.setBounds(1001, 194, 28, 26);
		CustomerPanel.add(refreshCust);
		refreshCust.addActionListener(this);
		
		//===========================================================Cashier Tab UI =============================================
		//Cashier Panel
		tabbedPane.addTab("Cashier", null, CashierPanel, null);
		CashierPanel.setLayout(null);
		
		//Cashier Title Label
		CashierTitle.setFont(new Font("Tahoma", Font.BOLD, 55));
		CashierTitle.setBounds(376, 0, 538, 78);
		CashierPanel.add(CashierTitle);
		
		//Cashier Record Label
		CashierLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		CashierLabel.setBounds(472, 89, 339, 47);
		CashierPanel.add(CashierLabel);
		
		//Cashier Panel Scroll
		CashierPane.setBounds(317, 238, 671, 383);
		CashierPanel.add(CashierPane);
		CashierTable.setShowVerticalLines(false);
		
		//Cashier Table
		CashierPane.setViewportView(CashierTable);
		CashierTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cashier ID", "Cashier Number", "Employee ID", "Date and Time"
			}
		));
		CashierTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			try {
					selecteditem = CashierTable.getSelectedRow();
					rowselected = CashierTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		
		//Add Cashier Button
		AddCashButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddCashButton.setBounds(317, 180, 193, 47);
		CashierPanel.add(AddCashButton);
		AddCashButton.addActionListener(this);
		
		//Remove Cashier Button
		RemoveCashButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		RemoveCashButton.setBounds(520, 180, 193, 47);
		CashierPanel.add(RemoveCashButton);
		RemoveCashButton.addActionListener(this);
		
		//Edit Cashier Button
		EditCashButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		EditCashButton.setBounds(723, 180, 193, 47);
		CashierPanel.add(EditCashButton);
		EditCashButton.addActionListener(this);
		
		
		//Refresh Cashier
		refreshCashier.setIcon(new ImageIcon("D:\\IM\\IMDB\\img\\refresh-icon.png"));
		refreshCashier.setBounds(960, 201, 28, 26);
		CashierPanel.add(refreshCashier);
		refreshCashier.addActionListener(this);
		
		//===============================================================Employee Tab=====================================================================
		//Employee Tab
		tabbedPane.addTab("Employee", null, EmployeePanel, null);
		EmployeePanel.setLayout(null);
		
		//Employee Title 
		EmployeeTitle.setFont(new Font("Tahoma", Font.BOLD, 55));
		EmployeeTitle.setBounds(378, 0, 538, 78);
		EmployeePanel.add(EmployeeTitle);
		
		//Employee Record
		EmployeeLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		EmployeeLabel.setBounds(450, 89, 375, 47);
		EmployeePanel.add(EmployeeLabel);
		
		//Employee Panel
		EmployeePane.setBounds(318, 240, 686, 376);
		EmployeePanel.add(EmployeePane);
		
		//Employee Table
		EmployeePane.setViewportView(EmployeeTable);
		EmployeeTable.setShowVerticalLines(false);
		EmployeeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Employee Name", "Employee Address", "Employee Number"
			}
		));
		EmployeeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			try {
					selecteditem = EmployeeTable.getSelectedRow();
					rowselected = EmployeeTable.getValueAt(selecteditem, 0).toString();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "NO INPUT TO THE TABLE", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	
		
		//Add Employee Button
		AddEmpButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddEmpButton.setBounds(318, 182, 193, 47);
		EmployeePanel.add(AddEmpButton);
		AddEmpButton.addActionListener(this);
		
		//Remove Employee Button
		RemoveEmpButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		RemoveEmpButton.setBounds(521, 182, 193, 47);
		EmployeePanel.add(RemoveEmpButton);
		RemoveEmpButton.addActionListener(this);
		
		//Edit Employee Button
		EditEmpButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		EditEmpButton.setBounds(724, 182, 193, 47);
		EmployeePanel.add(EditEmpButton);
		EditEmpButton.addActionListener(this);
		
		
		//Refresh
		refresh.setIcon(new ImageIcon("D:\\IM\\IMDB\\img\\refresh-icon.png"));
		refresh.setBounds(976, 203, 28, 26);
		EmployeePanel.add(refresh);
		refresh.addActionListener(this);
		
		//Show Tables at start up
		showtableEmp();
		showtableCash();
		showtableCustomer();
		showtableProductFood();
		showtableProductDrinks();
 
	}
	
	

	//===============================================Action Performed============================================================================
	public void actionPerformed(ActionEvent x) 
	{
		//===============================================OrderButtons=======================================================================
		//===================================================New ORder 
		if(x.getSource() == NewOrderButton) {
			String casID = cashIDtxtfd.getText();
			String cusID = ordercustIDTF.getText();
			String neworderID = "";
			if(casID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Can't Create no Cashier ID selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(!cusID.isEmpty()) {
					//==================================================Creating new order
					try {
						conn = DriverManager.getConnection(URL, username, password);
						pst = conn.prepareStatement("INSERT INTO artcafe.mainorder(customer_ID, cashier_ID, date_time )VALUES(?,?,now())");
						pst.setString(1, cusID);
						pst.setString(2, casID);
						q = pst.executeUpdate();
						
						pst = conn.prepareStatement("SELECT * FROM artcafe.mainorder");
						rs = pst.executeQuery();
						while(rs.next()) 
						{
							neworderID = rs.getString("order_ID");
						}
						orderIDtxtfd.setText(neworderID);
						
						if(q == 1) {
							JOptionPane.showMessageDialog(this, "Order Recorded Successfully");
							}
						else 
							JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
				else
				{//==============================================No customer id
					//Create a customer
					cusID = addcust();
					//Creating New Order
					try {
						conn = DriverManager.getConnection(URL, username, password);
						pst = conn.prepareStatement("INSERT INTO artcafe.mainorder(customer_ID, cashier_ID, date_time )VALUES(?,?,now())");
						pst.setString(1, cusID);
						pst.setString(2, casID);
						q = pst.executeUpdate();
						
						pst = conn.prepareStatement("SELECT * FROM artcafe.mainorder");
						rs = pst.executeQuery();
						while(rs.next()) 
						{
							if(rs.isLast()) {
								neworderID = rs.getString("order_ID");
							}
						}
						if(q == 1) {
							JOptionPane.showMessageDialog(this, "Order Recorded Successfully");
							}
						else 
							JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		}
		//========================================================================================================Cancel Order
		else if(x.getSource() == CancelOrderBtn) {
			String cancelorder = orderIDtxtfd.getText();
			if(cancelorder.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No order Id selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.mainorder WHERE order_ID =?");
	    				pst.setString(1, cancelorder);
	    				q = pst.executeUpdate();
	    				pst = conn.prepareStatement("ALTER TABLE artcafe.mainorder AUTO_INCREMENT=1");
	    				q = pst.executeUpdate();
	    				JOptionPane.showMessageDialog(this, "order Record Deleted");
	    				orderIDtxtfd.setText("");
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
		}
		//=================================================================================================Remove Order
		else if(x.getSource() == RemoveOrderBtn) {
			String orderid = orderIDtxtfd.getText();
			if(rowselected.isEmpty() || orderid.isEmpty()) {
				if(rowselected.isEmpty())
					JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Can't Delete no order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.orders WHERE product_ID =? AND order_id=?");
	    				pst.setString(1, rowselected);
	    				pst.setString(2, orderid);
	    				q = pst.executeUpdate();
	    				
	    				//Enabling the Buttons
	    				switch(rowselected) {
	    					case "1":
	    						BarBlendButton.setEnabled(true);
		    					BBQty.setText("");
		    					BBQty.setEnabled(true);
		    					break;
	    					case "2":
	    						AmericanoButton.setEnabled(true);
		    					AmericanotQTy.setText("");
								AmericanotQTy.setEnabled(true);
								break;
	    					case "3":
	    						CafeLatte.setEnabled(true);
	    						CafeLatteQTY.setText("");
	    						CafeLatteQTY.setEnabled(true);
	    						break;
	    					case "4":
	    						SpanishLatte.setEnabled(true);
	    						SpanishLatteQTY.setText("");
	    						SpanishLatteQTY.setEnabled(true);
	    						break;
	    					case "5":
	    						MacButton.setEnabled(true);
	    						MacQTY.setText("");
	    						MacQTY.setEnabled(true);
	    						break;
	    					case "6":
	    						BrazoButton.setEnabled(true);
	    						BCQTY.setText("");
	    						BCQTY.setEnabled(true);
	    						break;
	    					case "7":
	    						MangoButton.setEnabled(true);
	    						MCQTY.setText("");
	    						MCQTY.setEnabled(true);
	    						break;
	    					case "8":
	    						NachosButton.setEnabled(true);
	    						NACHQTY.setText("");
	    						NACHQTY.setEnabled(true);
	    						break;
	    				}
	    				rowselected = "";
	    				showtableOrders(orderid);
	    				JOptionPane.showMessageDialog(this, "Order Record Deleted");
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
		}
		
		//=================================================================================================Show Order
		else if(x.getSource() == ShowOrderButton) {
			String orderid = orderIDtxtfd.getText();
			if(orderid.isEmpty()) {
				DefaultTableModel recordtable = (DefaultTableModel)OrderTable.getModel();
				recordtable.setRowCount(0);
				JOptionPane.showMessageDialog(null, "Can't Show no order Id in its field", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				showtableOrders(orderid);
			}
		}
		//==================================================================================Total Price Button
		else if(x.getSource() == TotalPriceButton) {
			String totalprice = TotalPricetxtFD.getText();
			if(totalprice.isEmpty()) {
				
				for(i = 0; i<OrderTable.getRowCount(); i++) {
					sum = sum + Double.parseDouble(OrderTable.getValueAt(i, 3).toString());
				}
				TotalPricetxtFD.setText("Php "+String.valueOf(sum));
			}
			else if(!totalprice.isEmpty()) {
				double change = 0;
				double payment = Double.parseDouble(PaymenttxtFD.getText());
				change = payment - sum;
				ChangetxtFD.setText("Php "+String.valueOf(change));
			}
		}
		//====================================================================Order Complete Button
		else if(x.getSource() == OrderCompletBTN) {
			int finishorder;
			finishorder = JOptionPane.showConfirmDialog(null, "Confirm if the order is complete", "Warning", JOptionPane.YES_NO_OPTION);
            if(finishorder == JOptionPane.YES_OPTION) {
            	clear();
            }
		}
		
		//================================================Menu Buttons
		//================================================Drinks Buttons
		else if(x.getSource() == BarBlendButton) {
			String orderid = orderIDtxtfd.getText();
			String quantity = BBQty.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'1',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						BarBlendButton.setEnabled(false);
						BBQty.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == AmericanoButton) {	
			String orderid = orderIDtxtfd.getText();
			String quantity = AmericanotQTy.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'2',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						AmericanoButton.setEnabled(false);
						AmericanotQTy.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == SpanishLatte) {
			String orderid = orderIDtxtfd.getText();
			String quantity = SpanishLatteQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'4',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						SpanishLatte.setEnabled(false);
						SpanishLatteQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == CafeLatte) {	
			String orderid = orderIDtxtfd.getText();
			String quantity = CafeLatteQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'3',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						CafeLatte.setEnabled(false);
						CafeLatteQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		
		//================================================Food Buttons
		else if(x.getSource() == MacButton) {	
			String orderid = orderIDtxtfd.getText();
			String quantity = MacQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'5',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						MacButton.setEnabled(false);
						MacQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == BrazoButton) {	
			String orderid = orderIDtxtfd.getText();
			String quantity = BCQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'6',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						BrazoButton.setEnabled(false);
						BCQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == MangoButton) {	
			String orderid = orderIDtxtfd.getText();
			String quantity = MCQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'7',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						MangoButton.setEnabled(false);
						MCQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		else if(x.getSource() == NachosButton) {
			String orderid = orderIDtxtfd.getText();
			String quantity = NACHQTY.getText();
			if(orderid.isEmpty() || quantity.isEmpty()) {
				if(quantity.isEmpty())
					JOptionPane.showMessageDialog(null, "No input in qty", "Alert",JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "No input in Order ID", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					conn = DriverManager.getConnection(URL, username, password);
					pst = conn.prepareStatement("INSERT INTO artcafe.orders(order_ID, product_ID, quantity)VALUES(?,'8',?)");
					pst.setString(1, orderid);
					pst.setString(2, quantity);
					q = pst.executeUpdate();
					
					if(q == 1) {
						showtableOrders(orderid);
						JOptionPane.showMessageDialog(this, "Order Added");
						NachosButton.setEnabled(false);
						NACHQTY.setEnabled(false);
						}
					else 
						JOptionPane.showMessageDialog(this, "Record Failed", "Alert",JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		//===========================================Product Buttons===============================================================================
		//================================================Food Button
		else if(x.getSource() == AddProdButton1) {
			addproduct = new AddProduct("Add Food");
			addproduct.foodquery = "INSERT INTO artcafe.product(productName, size, price, food, drinks) VALUES(?,?,?,'1','0')";
		}
		else if(x.getSource() == EditProdButton1) {
			DefaultTableModel recordtable = (DefaultTableModel)FoodTable.getModel();
			editproduct = new EditProduct("Edit Food Product");
			editproduct.ProductNameTF.setText(recordtable.getValueAt(selecteditem, 1).toString());
			editproduct.ProductSizeTF.setText(recordtable.getValueAt(selecteditem, 2).toString());
			editproduct.ProductPriceTF.setText(recordtable.getValueAt(selecteditem, 3).toString());
			editproduct.selectitem = rowselected;
		}
		else if(x.getSource() == RemoveProdButton1) {
			if(rowselected.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.product WHERE product_ID =?");
	    				pst.setString(1, rowselected);
	    				q = pst.executeUpdate();
	    				pst = conn.prepareStatement("ALTER TABLE artcafe.product AUTO_INCREMENT=1");
	    				q = pst.executeUpdate();
	    				JOptionPane.showMessageDialog(this, "Product Record Deleted");
	    				showtableProductFood();
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
			
		}
		//================================================Drink Button
		else if(x.getSource() == AddProdButton2) {
			addproduct = new AddProduct("Add Drinks");
			addproduct.foodquery = "INSERT INTO artcafe.product(productName, size, price, food, drinks) VALUES(?,?,?,'0','1')";
		}
		else if(x.getSource() == EditProdButton2) {
			DefaultTableModel recordtable = (DefaultTableModel)DrinkTable.getModel();
			editproduct = new EditProduct("Edit Drinks Product");
			editproduct.ProductNameTF.setText(recordtable.getValueAt(selecteditem, 1).toString());
			editproduct.ProductSizeTF.setText(recordtable.getValueAt(selecteditem, 2).toString());
			editproduct.ProductPriceTF.setText(recordtable.getValueAt(selecteditem, 3).toString());
			editproduct.selectitem = rowselected;
			
		}	
		else if(x.getSource() == RemoveProdButton2) {
			if(rowselected.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.product WHERE product_ID =?");
	    				pst.setString(1, rowselected);
	    				q = pst.executeUpdate();
	    				pst = conn.prepareStatement("ALTER TABLE artcafe.product AUTO_INCREMENT=1");
	    				q = pst.executeUpdate();
	    				JOptionPane.showMessageDialog(this, "Product Record Deleted");
	    				rowselected = "";
	    				showtableProductDrinks();
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
		}
		else if(x.getSource() == refreshProduct) {
			showtableProductFood();
			showtableProductDrinks();
		}
			
		
		//===========================================Customer Buttons=============================================================================
		else if(x.getSource()==AddCustButton) {
			addcustomer = new AddCustomer ("Add Customer");
		}
		else if(x.getSource() == EditCustButton ) {
			DefaultTableModel recordtable = (DefaultTableModel)CustomerTable.getModel();
			editcustomer = new EditCustomer("Edit Customer");
			editcustomer.CustNameTF.setText(recordtable.getValueAt(selecteditem, 1).toString());
			editcustomer.CustNumTF.setText(recordtable.getValueAt(selecteditem, 2).toString());
			editcustomer.selectitem = rowselected;
		}
		else if(x.getSource() == DeleteCustButton) {
			if(rowselected.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.customer WHERE customer_ID =?");
	    				pst.setString(1, rowselected);
	    				q = pst.executeUpdate();
	    				pst = conn.prepareStatement("ALTER TABLE artcafe.customer AUTO_INCREMENT=1");
	    				q = pst.executeUpdate();
	    				JOptionPane.showMessageDialog(this, "Customer Record Deleted");
	    				rowselected = "";
	    				showtableCustomer();
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
		}
		else if(x.getSource() == refreshCust) {
			showtableCustomer();
		}
		//==========================================Cashier buttons===================================================
		else if(x.getSource() == AddCashButton) {
			addcashier = new AddCashier ("Add Cashier");
		}
		else if(x.getSource() == EditCashButton) {
			DefaultTableModel recordtable = (DefaultTableModel)CashierTable.getModel();
			upcashier = new UpdateCashier("Edit Cashier");
			upcashier.CashierNumberTF.setText(recordtable.getValueAt(selecteditem, 1).toString());
			upcashier.Emp_ID.setText(recordtable.getValueAt(selecteditem, 2).toString());
			upcashier.selectitem = rowselected;
		}
		else if(x.getSource() == RemoveCashButton) {
			if(rowselected.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
			}
			else {
				deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
	            if(deleteitem == JOptionPane.YES_OPTION) {
	            	try {
	    				conn = DriverManager.getConnection(URL, username, password);
	    				pst = conn.prepareStatement("DELETE FROM artcafe.cashier WHERE cashier_ID =?");
	    				pst.setString(1, rowselected);
	    				q = pst.executeUpdate();
	    				pst = conn.prepareStatement("ALTER TABLE artcafe.cashier AUTO_INCREMENT=1");
	    				q = pst.executeUpdate();
	    				JOptionPane.showMessageDialog(this, "Cashier Record Deleted");
	    				rowselected = "";
	    				showtableCash();
	    			}
	    			catch(Exception e){
	    				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
	    				e.printStackTrace();
	    			}
	            }
			}
		}
		else if(x.getSource() == refreshCashier)
      	  showtableCash();

		//====================================employee buttons==============================
		else if(x.getSource() == AddEmpButton) {
			empWindow = new EmployeeWindow("Add Employee");
		}
		else if(x.getSource() == EditEmpButton) {
			DefaultTableModel recordtable = (DefaultTableModel)EmployeeTable.getModel();
			updateemployee = new UpdateEmployeeWindow("Edit Employee");
			updateemployee.empNameTF.setText(recordtable.getValueAt(selecteditem, 1).toString());
			updateemployee.empAddressTF.setText(recordtable.getValueAt(selecteditem, 2).toString());
			updateemployee.empNumTF.setText(recordtable.getValueAt(selecteditem, 3).toString());
			updateemployee.selectitem = rowselected;
		}
		else if(x.getSource() == RemoveEmpButton) {
            if(rowselected.isEmpty()) {	
            	JOptionPane.showMessageDialog(null, "Can't Delete no item selected", "Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
            	deleteitem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item", "Warning", JOptionPane.YES_NO_OPTION);
                if(deleteitem == JOptionPane.YES_OPTION) {
                	try {
        				conn = DriverManager.getConnection(URL, username, password);
        				pst = conn.prepareStatement("DELETE FROM artcafe.employee WHERE emp_ID =?");
        				pst.setString(1, rowselected);
        				q = pst.executeUpdate();
        				pst = conn.prepareStatement("ALTER TABLE artcafe.employee AUTO_INCREMENT=1");
        				q = pst.executeUpdate();
        				JOptionPane.showMessageDialog(this, "Employee Record Deleted");
        				rowselected = "";
        				showtableEmp();
        			}
        			catch(Exception e){
        				JOptionPane.showMessageDialog(null, "Can't Delete not connected", "Alert",JOptionPane.WARNING_MESSAGE);
        				e.printStackTrace();
        			}
                }
            }
		}
		else if(x.getSource()==refresh)
			showtableEmp();
	}
	
//======================================END OF ACTION PERFORMED==================================================================================

//=================================================Order Table Function==========================================================================
	public ArrayList<Orders> ordersList(String ID){
		ArrayList<Orders> ordersList = new ArrayList<>();
		try{
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement("SELECT  orders.product_ID, productName, quantity, price*quantity as 'Price'\r\n"
					+ "FROM artcafe.orders\r\n"
					+ "JOIN artcafe.product\r\n"
					+ "	ON orders.product_ID = product.product_ID WHERE order_ID =?");
			pst.setString(1, ID);
			
			rs = pst.executeQuery();
			
			Orders order;
			while(rs.next()) {
				order = new Orders(rs.getInt("product_ID"), rs.getString("productName"), rs.getString("quantity"), rs.getString("Price"));
				ordersList.add(order);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return ordersList;
	}
	
	public void showtableOrders(String ID) 
	{
		ArrayList<Orders> list = ordersList(ID);
		DefaultTableModel recordtable = (DefaultTableModel)OrderTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getprodID();
			row[1] = list.get(i).getprodName();
			row[2] = list.get(i).getquantity();
			row[3] = list.get(i).getprice();
			recordtable.addRow(row);
		}
	}
	
//=================================================Product Food Table Function===================================================================
	public ArrayList<ProductFood> prodFoodList(String type){
		ArrayList<ProductFood> prodFoodList = new ArrayList<>();
		try{
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement(type);
			
			rs = pst.executeQuery();
			
			ProductFood productfood;
			while(rs.next()) {
				productfood = new ProductFood(rs.getInt("product_ID"), rs.getString("productName"), rs.getString("size"), rs.getInt("price"));
				prodFoodList.add(productfood);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return prodFoodList;
	}
	
	public void showtableProductFood() 
	{
		ArrayList<ProductFood> list = prodFoodList("SELECT * FROM artcafe.product WHERE food ='1'");
		DefaultTableModel recordtable = (DefaultTableModel)FoodTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getprodID();
			row[1] = list.get(i).getprodName();
			row[2] = list.get(i).getsize();
			row[3] = list.get(i).getprice();
			recordtable.addRow(row);
		}
	}
	
	public void showtableProductDrinks() 
	{
		ArrayList<ProductFood> list = prodFoodList("SELECT * FROM artcafe.product WHERE drinks ='1'");
		DefaultTableModel recordtable = (DefaultTableModel)DrinkTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getprodID();
			row[1] = list.get(i).getprodName();
			row[2] = list.get(i).getsize();
			row[3] = list.get(i).getprice();
			recordtable.addRow(row);
		}
	}
	
	//================================================Customer Table Function====================================================================
	public ArrayList<Customer> customerList(){
		ArrayList<Customer> customerList = new ArrayList<>();
		try{
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement("SELECT * FROM artcafe.customer");
		
			rs = pst.executeQuery();
			
			Customer cust;
			while(rs.next()) {
				cust = new Customer(rs.getInt("customer_ID"), rs.getString("customerName"), rs.getString("customerNumber"));
				customerList.add(cust);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return customerList;
	}
	
	public void showtableCustomer() 
	{
		ArrayList<Customer> list = customerList();
		DefaultTableModel recordtable = (DefaultTableModel)CustomerTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getcustID();
			row[1] = list.get(i).getcustName();
			row[2] = list.get(i).getcustNum();
			recordtable.addRow(row);
		}
	}
	

	//================================================Cashier Table Function ====================================================================
	public ArrayList<Cashier> cashierList(){
		ArrayList<Cashier> cashierList = new ArrayList<>();
		try{
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement("SELECT * FROM artcafe.cashier");
		
			rs = pst.executeQuery();
			
			Cashier cash;
			while(rs.next()) {
				cash = new Cashier(rs.getInt("cashier_ID"), rs.getInt("cashier_Number"), rs.getInt("emp_ID"), rs.getString("Date_Time"));
				cashierList.add(cash);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return cashierList;
	}
	
	public void showtableCash() 
	{
		ArrayList<Cashier> list = cashierList();
		DefaultTableModel recordtable = (DefaultTableModel)CashierTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getcashierID();
			row[1] = list.get(i).getcashierNum();
			row[2] = list.get(i).getempID();
			row[3] = list.get(i).getdatetime();
			recordtable.addRow(row);
		}
	}
	
	//================================================Employee Table Function=====================================================================
	public ArrayList<Employee> empList(){
		ArrayList<Employee> empList = new ArrayList<>();
		try{
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement("SELECT * FROM artcafe.employee");
		
			rs = pst.executeQuery();
			
			Employee emp;
			while(rs.next()) {
				emp = new Employee(rs.getInt("emp_ID"), rs.getString("empName"), rs.getString("empAddress"), rs.getString("empNumber"));
				empList.add(emp);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		return empList;
	}
	
	public void showtableEmp() 
	{
		ArrayList<Employee> list = empList();
		DefaultTableModel recordtable = (DefaultTableModel)EmployeeTable.getModel();
		recordtable.setRowCount(0);
		Object[] row = new Object[4];
		for(i=0; i<list.size();i++) 
		{
			row[0] = list.get(i).getempID();
			row[1] = list.get(i).getempName();
			row[2] = list.get(i).getempAddress();
			row[3] = list.get(i).getempNum();
			recordtable.addRow(row);
		}
	}
	
	//=====================================================Clear BUtton==========================================================================
	public void clear() {
		DefaultTableModel recordtable = (DefaultTableModel)OrderTable.getModel();
		recordtable.setRowCount(0);
		BarBlendButton.setEnabled(true);
		BBQty.setText("");
		BBQty.setEnabled(true);
		AmericanoButton.setEnabled(true);
		AmericanotQTy.setText("");
		AmericanotQTy.setEnabled(true);
		CafeLatte.setEnabled(true);
		CafeLatteQTY.setText("");
		CafeLatteQTY.setEnabled(true);
		SpanishLatte.setEnabled(true);
		SpanishLatteQTY.setText("");
		SpanishLatteQTY.setEnabled(true);
		MacButton.setEnabled(true);
		MacQTY.setText("");
		MacQTY.setEnabled(true);
		BrazoButton.setEnabled(true);
		BCQTY.setText("");
		BCQTY.setEnabled(true);
		MangoButton.setEnabled(true);
		MCQTY.setText("");
		MCQTY.setEnabled(true);
		NachosButton.setEnabled(true);
		NACHQTY.setText("");
		NACHQTY.setEnabled(true);
		TotalPricetxtFD.setText("");
		PaymenttxtFD.setText("");
		ChangetxtFD.setText("");
		cashIDtxtfd.setText("");
		orderIDtxtfd.setText("");
		ordercustIDTF.setText("");
		sum = 0;
	}
	
	public String addcust() {
		String cus = "";
		try {// If no customer name and number
			conn = DriverManager.getConnection(URL, username, password);
			pst = conn.prepareStatement("INSERT INTO artcafe.customer(customerName, customerNumber)VALUES('','')");
			q = pst.executeUpdate();
			pst = conn.prepareStatement("SELECT customer_ID FROM artcafe.customer");
			rs = pst.executeQuery();
			int n = 0;
			while(rs.next()) 
			{
				n = rs.getInt("customer_ID");
			}
			ordercustIDTF.setText(String.valueOf(n));
			cus = String.valueOf(n);
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error Can't Connect to database", "Alert",JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
		}
		return cus;
	}
	
	//================================================MainFunction===================================================================================
	//application start
	public static void main(String[] args) 
	{
		IMDB function = new IMDB("Alitaptap Art Cafe Database Management System");
	    function.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
