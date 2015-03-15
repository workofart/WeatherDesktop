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
	 * generate a frame and show it
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
	
	public PreferenceUI(){
		pref = new Preference();
		prefCopy = pref.clone();
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
		tempC.setBounds(100, 45, 45, 25);
		tempC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(1);
			}
		});
		getContentPane().add(tempC);
		
		tempF = new JRadioButton("F");
		tempF.setBounds(140, 45, 40, 25);
		tempF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pref.setTempUnit(2);
			}
		});
		getContentPane().add(tempF);
		
		tempK = new JRadioButton("K");
		tempK.setBounds(180, 45, 40, 25);
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
					if(!format(textField.getText().toLowerCase()).matches("(mars)|([a-z]+\\,[a-z]{2})")){
						Main.wrongLocationFormat();
						return;
					}
					pref.setLocation(format(textField.getText()));
					System.out.println("input preference " + pref.getLocation() +" " + pref.getTempUnit());
					setVisible(false);
					String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					path = URLDecoder.decode(path, "UTF-8");
					path = path.substring(0,path.lastIndexOf("/")+1);
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path +".Preference"));
					output.writeObject(pref);
					output.close();
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
		textField.setBounds(100, 15, 105, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("Mars");
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				if(e.getSource() == textField && key == KeyEvent.VK_ENTER){
					try {
						if(!format(textField.getText().toLowerCase()).matches("(mars)|([a-z]+\\,[a-z]{2})")){
							Main.wrongLocationFormat();
							return;
						}
						pref.setLocation(format(textField.getText()));
						System.out.println("input preference " + pref.getLocation() +" " + pref.getTempUnit());
						setVisible(false);
						String path = PreferenceUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
						path = URLDecoder.decode(path, "UTF-8");
						path = path.substring(0,path.lastIndexOf("/")+1);
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path +".Preference"));
						output.writeObject(pref);
						output.close();
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

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		if(System.getProperty("os.name").toLowerCase().startsWith("win") || System.getProperty("os.name").toLowerCase().startsWith("mac")){
			setSize(240,150);
		}else{
			setSize(240,120);
		}
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
	
	private String format(String s){
		String result = "";
		for(int i = 0; i < s.length();i++){
			if(s.charAt(i) != ' '){
				result = result + s.charAt(i);
			}
		}
		return result;
	}
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
