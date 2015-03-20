package ui;

import io.Preference;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;

import javax.swing.JTextField;

/**
 * PreferenceUI object to read, write preference object
 * Also it represent a window for setting user preference
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class PreferenceUI extends JFrame {
	// attributes
	private Preference pref, //first copy of preference
					   prefCopy; // second copy of preference in order to determine refresh or refresh unit only
	private JLabel lblLocation, lblTemperature; // label for location and temperature unit
	private JRadioButton tempC, tempK, tempF; // radio button for temperature choice
	private ButtonGroup buttonGroup; // button group to make only one radio button can be selected
	private JButton btnSave, btnCancel; // button for save and cancel
	private JTextField textField; // text field for input location

	
	

	/**
	 * constructor for Preference UI who tries to read preference object from disk
	 * generate a frame
	 * @throws Exception when the file not exit, throw exception and let main deal with it
	 * @param name the filename of preference object
	 */
	public PreferenceUI(String name) throws Exception{
		// create an empty preference
		pref = new Preference();
		// create a frame and show it
		init();
		// path to read from
		// put to the same folder whene the jar file is
		// start with dot, so it is hidden
		String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = URLDecoder.decode(path, "UTF-8");
		path = path.substring(0,path.lastIndexOf("/")+1);
		// try to read the saved the file
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(path+name));
		pref = (Preference) input.readObject();
		// make a copy of the file
		prefCopy = pref.clone();
		// set the elected button and text field according to the save file
		JRadioButton[] a = {tempK, tempC, tempF};
		a[pref.getTempUnit()].setSelected(true);
		textField.setText(pref.getLocation());
		System.out.println("Load from saved Preference " + pref.getLocation() + " "+pref.getTempUnit());
		// close the input stream
		input.close();	
	}
	
	/**
	 * constructor to create an empty preference object
	 * generate the preference ui and show it
	 */
	public PreferenceUI(){
		// make an empty preference
		// make a copy of it
		pref = new Preference();
		prefCopy = pref.clone();
		// generate the preference ui and show it
		init();
		this.setVisible(true);
	}
	
	/**
	 * helper method to generate preference ui
	 */
	private void init(){
		// the frame is alway on top
		// the frame use absolute layout
		// the frame is hidden when click the close button on window
		this.setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		
		// create label for location
		// put it to the left top label
		lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocation.setBounds(10, 11, (int)lblLocation.getPreferredSize().getWidth() + 5, (int)lblLocation.getPreferredSize().getHeight() + 5);
		getContentPane().add(lblLocation);
		
		// create label for temperature 
		// put it under the location label
		lblTemperature = new JLabel("Temperature");
		lblTemperature.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTemperature.setBounds(10, 45, (int)lblTemperature.getPreferredSize().getWidth() + 5, (int)lblTemperature.getPreferredSize().getHeight() + 5);
		getContentPane().add(lblTemperature);
		
		// create a radio button for celcius
		// put it to the right of temperature label
		tempC = new JRadioButton("C");
		tempC.setBounds(100, 45, 45, 25);
		// when the radio button is chosen, change preference
		tempC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(1);
			}
		});
		getContentPane().add(tempC);
		
		// create a radio button for farenheit
		// put it to the right of celcius button
		tempF = new JRadioButton("F");
		tempF.setBounds(145, 45, 40, 25);
		// when the radio button is chosen, chagne preference
		tempF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(2);
			}
		});
		getContentPane().add(tempF);
		
		// create a radio button for kelven
		// put it to the right of farenheit button
		tempK = new JRadioButton("K");
		tempK.setBounds(185, 45, 40, 25);
		// when the radio button is chosen, change preference
		tempK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(0);
			}
		});
		getContentPane().add(tempK);
		
		// create a button gourp, so only one radio button is choosen
		// set celcius is defaultly chosen
		buttonGroup = new ButtonGroup();
		buttonGroup.add(tempC);
		buttonGroup.add(tempF);
		buttonGroup.add(tempK);
		tempC.setSelected(true);
		
		// create a button for apply
		// the button is below the temperature label
		btnSave = new JButton("Apply");
		btnSave.setBounds(10, 75, 89, 23);
		// defien a action listener to deal with clicking
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// if the input location is wrong format, return and show an error message
					if(!format(textField.getText().toLowerCase()).matches("(mars)|([a-z]+\\,[a-z]{2})")){
						Main.wrongLocationFormat();
						return;
					}
					// if the input location is in correct format
					// hide the preference window
					// print the content of preference
					pref.setLocation(format(textField.getText()));
					System.out.println("input preference " + pref.getLocation() +" " + pref.getTempUnit());
					setVisible(false);
					// write the content to file
					String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					path = URLDecoder.decode(path, "UTF-8");
					path = path.substring(0,path.lastIndexOf("/")+1);
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path +".Preference"));
					output.writeObject(pref);
					output.close();
					// if the only change is temperature, and there is a copy of data in Main frame, refresh unit
					// if the location is changes or there is no copy of data in Main, pull the data and refresh
					// if nothing changed, do nothing
					if(pref.getLocation().equalsIgnoreCase(prefCopy.getLocation())){
						if(pref.getTempUnit()!=prefCopy.getTempUnit()){
							if(Main.refreshed()){
								Main.refreshUnit(pref.getLocation(),pref.getTempUnit());
								System.out.println("Refreshing Unit");
							}
							else{
								Main.refresh(pref.getLocation(),pref.getTempUnit());
								System.out.println("Refreshing");
							}
						}
						
					}else{
						Main.refresh(pref.getLocation(),pref.getTempUnit());
						System.out.println("Refreshing");
					}
					// update the copy
					prefCopy = pref.clone();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnSave);
		
		// create the cancel button
		btnCancel = new JButton("Cancel");
		// when it is clicked, hide the frame
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(126, 75, 89, 23);
		getContentPane().add(btnCancel);
		
		// create a text field for user to input location
		textField = new JTextField();
		textField.setBounds(100, 15, 105, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		// default text is Mars
		textField.setText("Mars");
		// when user click enter, act as pushing apply button
		textField.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER){
					try {
						// if the input location is wrong format, return and show an error message
						if(!format(textField.getText().toLowerCase()).matches("(mars)|([a-z]+\\,[a-z]{2})")){
							Main.wrongLocationFormat();
							return;
						}
						// if the input location is in correct format
						// hide the preference window
						// print the content of preference
						pref.setLocation(format(textField.getText()));
						System.out.println("input preference " + pref.getLocation() +" " + pref.getTempUnit());
						setVisible(false);
						// write the content to file
						String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
						path = URLDecoder.decode(path, "UTF-8");
						path = path.substring(0,path.lastIndexOf("/")+1);
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path +".Preference"));
						output.writeObject(pref);
						output.close();
						// if the only change is temperature, and there is a copy of data in Main frame, refresh unit
						// if the location is changes or there is no copy of data in Main, pull the data and refresh
						// if nothing changed, do nothing
						if(pref.getLocation().equalsIgnoreCase(prefCopy.getLocation())){
							if(pref.getTempUnit()!=prefCopy.getTempUnit()){
								if(Main.refreshed()){
									Main.refreshUnit(pref.getLocation(),pref.getTempUnit());
									System.out.println("Refreshing Unit");
								}
								else{
									Main.refresh(pref.getLocation(),pref.getTempUnit());
									System.out.println("Refreshing");
								}
							}
						}else{
							Main.refresh(pref.getLocation(),pref.getTempUnit());
							System.out.println("Refreshing");
						}
						prefCopy = pref.clone();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		setSize(240,135);
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
	 * helper method to format the location string
	 * @param s the string to be formatted
	 * @return the formatted string
	 */
	private String format(String s){
		String result = "";
		// remove all the space in the string
		for(int i = 0; i < s.length();i++){
			if(s.charAt(i) != ' '){
				result = result + s.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * method to show the preference window and set all the field to default
	 */
	public void showPreferenceDefault(){
		textField.setText("London,ca");
		tempC.setSelected(true);
		this.setVisible(true);
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
