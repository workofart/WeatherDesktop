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
import java.net.URLDecoder;

import javax.swing.JTextField;

import data.Query;

public class PreferenceUI extends JFrame {

	private Preference pref;
	private JLabel lblLocation, lblTemperature;
	private JRadioButton tempC, tempK, tempF;
	private ButtonGroup buttonGroup;
	private JButton btnSave, btnCancel;
	private JTextField textField;

	
	

	/**
	 * constructor for Preference UI who tries to read preference object from disk
	 */
	public PreferenceUI(String name) throws Exception{
		
		pref = new Preference();
		init();

		String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = URLDecoder.decode(path, "UTF-8");
		System.out.println(path);
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(path+name));
		pref = (Preference) input.readObject();
		JRadioButton[] a = {tempK, tempC, tempF};
		a[pref.getTempUnit()].setSelected(true);
		textField.setText(pref.getLocation());
		input.close();	
	}
	
	public PreferenceUI(){
		pref = new Preference();
		init();
		this.setVisible(true);
	}
	
	
	private void init(){

		this.setAlwaysOnTop(true);
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
		tempC.setSelected(true);
		btnSave = new JButton("Apply");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pref.setLocation(textField.getText());
					System.out.println(pref.getLocation());
					System.out.println(pref.getTempUnit());
					setVisible(false);
					String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					path = URLDecoder.decode(path, "UTF-8");
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path +".Preference"));
					output.writeObject(pref);
					output.close();
					Main.refresh(pref.getLocation(),pref.getTempUnit());
					
				} catch (IOException e) {
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

		textField.setText("Mars");
		
		pack();
		setBounds(100, 100, 241, 144);
		setVisible(false);
		setResizable(false);
	}
	
	/**
	 * method to show the frame with the location in text field and radio button selected as the temperature unit
	 */
	public void showPreference(){
		//set the selected part according to the preference object
		textField.setText(pref.getLocation());
		JRadioButton[] a = {tempK, tempC, tempF};
		a[pref.getTempUnit()].setSelected(true);
		//show the frame
		this.setVisible(true);
	}
	
	/**
	 * getter method for location selection
	 * @return the location selection as String
	 */
	public String getLocationPref(){
		return pref.getLocation();
	}
	
	/**
	 * getter method for temperature unit
	 * @return temperature unit, 0 - k, 1 - C, 2 - F
	 */
	public int getUnitPref(){
		return pref.getTempUnit();
	}
	
	/**
	 * test method for PreferenceUI
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// create this object and show the frame
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
	
}
