package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class PreferenceUI {

	private JFrame frame;
	private JTextField txtLocation;
	private JTextField txtUnit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferenceUI window = new PreferenceUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PreferenceUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtLocation = new JTextField();
		txtLocation.setText("Location");
		txtLocation.setBounds(10, 11, 48, 20);
		frame.getContentPane().add(txtLocation);
		txtLocation.setColumns(10);
		
		JList list = new JList();
		list.setBounds(68, 31, 80, -17);
		frame.getContentPane().add(list);
		
		txtUnit = new JTextField();
		txtUnit.setText("Unit");
		txtUnit.setBounds(10, 122, 30, 20);
		frame.getContentPane().add(txtUnit);
		txtUnit.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("K");
		rdbtnNewRadioButton.setBounds(46, 121, 48, 21);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("C");
		rdbtnNewRadioButton_1.setBounds(96, 121, 48, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(146, 119, 109, 23);
		frame.getContentPane().add(rdbtnF);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(109, 227, 89, 23);
		frame.getContentPane().add(btnClose);
		
		
	}
}
