package ui;

import io.Preference;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;

import data.Query;

public class PreferenceUI extends JFrame {

	private JPanel contentPane;
	private Preference pref;
	private JLabel lblLocation, lblTemperature;
	private JRadioButton tempC, tempK, tempF;
	private ButtonGroup buttonGroup;
	private JButton btnSave, btnCancel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferenceUI frame = new PreferenceUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PreferenceUI() {
		
		pref = new Preference();
		init();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(".Preference"));
			pref = (Preference) input.readObject();
			JRadioButton[] a = {tempK, tempC, tempF};
			a[pref.getTempUnit()].setSelected(true);
			textField.setText(pref.getLocation());
		} catch (FileNotFoundException e) {
			this.setVisible(true);
			tempC.setSelected(true);
			textField.setText("Toronto,ca");
			this.setAlwaysOnTop(true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 144);
		getContentPane().setLayout(null);
		
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocation.setBounds(10, 11, 47, 26);
		getContentPane().add(lblLocation);
		
		lblTemperature = new JLabel("Temperature");
		lblTemperature.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTemperature.setBounds(10, 44, 76, 20);
		getContentPane().add(lblTemperature);
		
		
		
		tempC = new JRadioButton("C");
		tempC.setBounds(100, 45, 35, 25);
		tempC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(1);
			}
		});
		getContentPane().add(tempC);
		
		tempF = new JRadioButton("F");
		tempF.setBounds(135, 45, 35, 25);
		tempF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(2);
			}
		});
		getContentPane().add(tempF);
		
		tempK = new JRadioButton("K");
		tempK.setBounds(170, 45, 35, 25);
		tempK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(0);
			}
		});
		getContentPane().add(tempK);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(tempC);
		buttonGroup.add(tempF);
		buttonGroup.add(tempK);
		
		btnSave = new JButton("Apply");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pref.setLocation(textField.getText());
					System.out.println(pref.getLocation());
					System.out.println(pref.getTempUnit());
					setVisible(false);
					if(pref.getLocation().toLowerCase().equals("mars") || new Query(pref.getLocation(),0).toString().length() > 50){
						
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(".Preference"));
						output.writeObject(pref);
						output.close();
						
					}
					else{
						setVisible(true);
						JOptionPane.showMessageDialog(null," is invalid.");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(10, 75, 89, 23);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(126, 75, 89, 23);
		getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(100, 15, 105, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public void showPreference(){
		textField.setText(pref.getLocation());
		JRadioButton[] a = {tempK, tempC, tempF};
		a[pref.getTempUnit()].setSelected(true);
		this.setVisible(true);
	}
	
	public String getLocationPref(){
		return pref.getLocation();
	}
	
	public int getUnitPref(){
		return pref.getTempUnit();
	}
	
}
