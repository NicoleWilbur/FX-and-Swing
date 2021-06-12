/***
 =================
Name: Nicole Wilbur

Project Name: CSC372-CTA02 -- PORTFOLIO MILESTONE #1 Corrections

4/11/21 updates: comments along side code.
	Summary, corrected spelling mistake, capitalized "withdrawal" on button, included a try/catch handler and message for 
	non-numerical input.
==================
 ***/
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ABCBankSystemFrame extends JFrame {

	private JPanel contentPane;
	private JTextField updatedBalance;
	private JTextField currentBalance;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABCBankSystemFrame frame = new ABCBankSystemFrame();
					frame.setTitle("ABC Bank User Interface");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ABCBankSystemFrame() {
		BankAccount account1 = new BankAccount();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ABC Bank Account Manager");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 6, 261, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(147, 237, 136, 29);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 34, 438, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Current Balance:");
		lblNewLabel_1.setBounds(19, 10, 138, 16);
		panel.add(lblNewLabel_1);
	
		
		JTextField transactionAmount = new JTextField();
		transactionAmount.setBounds(220, 59, 130, 26);
		panel.add(transactionAmount);
		transactionAmount.setColumns(10);
		
		JLabel newBalance = new JLabel("Updated Balance:");
		newBalance.setBounds(35, 159, 107, 16);
		panel.add(newBalance);
		
		updatedBalance = new JTextField();
		updatedBalance.setBounds(220, 154, 130, 26);
		panel.add(updatedBalance);
		updatedBalance.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Transaction Amount:");
		lblNewLabel_2.setBounds(19, 64, 180, 16);
		panel.add(lblNewLabel_2);
		
		currentBalance = new JTextField();
		currentBalance.setColumns(10);
		currentBalance.setBounds(169, 5, 130, 26);
		panel.add(currentBalance);
		
		//gets balance from user
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {                                                                  //4-11-21 created a try/catch with a show message dialog
					double balance = Double.parseDouble(currentBalance.getText());    //to catch non-numeric inputs
					account1.setBalance(balance);
					if (account1.getBalance() < 100000 && account1.getBalance() > 0) {
						//displays the balance
						NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
						String displayBalance = numberFormat.format(account1.getBalance());
					updatedBalance.setText(displayBalance);
					}
					else {
						JOptionPane.showMessageDialog(currentBalance, "Sorry, ABC Bank has a balance limit of 100,000 per day"
								+ " and all accounts must begin with a positive balance.");
					}
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(currentBalance, "Please enter a valid number.");
				}
			}
		});
		enterButton.setBounds(311, 5, 85, 29);
		panel.add(enterButton);
		
		//gets deposit from user
		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//saves the deposit amount
				double amount = Double.parseDouble(transactionAmount.getText());
				if (amount > 9999) {
					JOptionPane.showMessageDialog(transactionAmount, "Sorry, ABC Bank has a transaction limit of 10,000 per day. Please "
							+ "enter a smaller amount.");
				}
				else if (amount < 1) {
					JOptionPane.showMessageDialog(transactionAmount, "Please enter a positive amount.");
				}
				else {
				account1.deposit(amount);
				//displays new balance
				NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
				String displayBalance = numberFormat.format(account1.getBalance());
				updatedBalance.setText(displayBalance);
				}
			}
		}); 
		depositButton.setBounds(282, 86, 117, 29);
		panel.add(depositButton);
		
		//gets withdrawal from user                                       //4-11-21 corrected spelling mistake everywhere
		JButton withdrawButton = new JButton("Withdrawal");				 // 4-11-21 capitalized "Withdrawal
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//saves the withdrawal amount
				double amount = Double.parseDouble(transactionAmount.getText());
				if (amount > 9999) {
					JOptionPane.showMessageDialog(transactionAmount, "Sorry, ABC Bank has a transaction limit of 10,000 per day. Please "
							+ "enter a smaller amount.");
				}
				else if (amount < 1) {
					JOptionPane.showMessageDialog(transactionAmount, "Please enter a positive amount.");
				}
				else {
					account1.withdrawal(amount);
					//displays new balance
					NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
					String displayBalance = numberFormat.format(account1.getBalance());
					if (account1.getBalance() < 0) {
						updatedBalance.setForeground(Color.RED);
					}
						updatedBalance.setText(displayBalance);
				}
			}
		});
		withdrawButton.setBounds(164, 86, 117, 29);
		panel.add(withdrawButton);
		
	}
}
