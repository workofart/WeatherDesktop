package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Today Panel is the panel to show current weather for open weather map or Mars weather
 * This object take signal from Main window
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class TodayPanel extends JPanel{
	// attributes
	private JLabel tempLabel, //label for temperature
				   winLabel, //label for wind speed and wind direction
				   presLabel, //label for air pressure
				   humLabel, //label for humidity
				   sunLabel, //label for sky condition
				   iconLabel, //label for weather condition icon
				   refreshLabel, //label for refresh time
				   risetLabel, //label for sunrise and sunset time
				   maxminLabel, //label for maximum minimum temperature
				   locationLabel; //label for current weather location
	private JButton refresh_b, //button for refresh
				    pref_b; //button for preference window
	
	// constructor to prepare and show the panel
	public TodayPanel(){
		init();
	}
	
	/**
	 * method to refresh panel as Mars panel
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	public void refreshMars(int unit){
		// hide the sunset sunrise label
		risetLabel.setVisible(false);
		// refresh all the label according to the data
		// fit the content
		refreshMarsUnit(unit);
		setIcon(Main.getMdata().getIcon());
		setWinLabel(Main.getMdata().getSpeed(), Main.getMdata().getDirection());
		setPresLabel(Main.getMdata().getPressure());
		setHumLabel(Main.getMdata().getHumidity());
		setRefreshLabel();
		setSunLabel(Main.getMdata().getWeather());
		setLocationLabel("Mars");
	}
	
	/**
	 * method to refresh panel as current weather panel
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	public void refresh(int unit){
		// show the sunset sunrise label
		risetLabel.setVisible(true);
		// refresh all the label according to the data
		// fit the content
		refreshUnit(unit);
		setIcon(Main.getCdata().getIcon());
		setWinLabel(Main.getCdata().getSpeed(), Main.getCdata().getDirection());
		setPresLabel(Main.getCdata().getPressure());
		setHumLabel(Main.getCdata().getHumidity());
		setRefreshLabel();
		setSunLabel(Main.getCdata().getWeather());
		setLocationLabel(Main.getCdata().getLocation());
		setRisetLabel(Main.getCdata().getSunrise(), Main.getCdata().getSunset());
	}	
	
	/**
	 * method to refresh data unit as current weather panel
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	public void refreshUnit(int unit){
		setTempLabel(Main.getCdata().getTemp(unit),unit);
		setMaxMinLabel(Main.getCdata().getMaxTemp(unit),Main.getCdata().getMinTemp(unit),unit);
	}
	
	/**
	 * method to refresh data unit as Mars weather panel
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	public void refreshMarsUnit(int unit){
		setTempLabel(Main.getMdata().getTemp(unit),unit);
		setMaxMinLabel(Main.getMdata().getMaxTemp(unit),Main.getMdata().getMinTemp(unit),unit);
	}
	
	/**
	 * helper method to initiate all the label and button
	 */
	private void init(){
		// use absolute layout
		setLayout(null);		
		this.setSize(520,280);	
		
		// initiate icon label and leave blank
		iconLabel = new JLabel();
		iconLabel.setBounds(40,40,50,50);
		this.add(iconLabel);
			
		// initiate refresh button
		refresh_b = new JButton();
		// use a picture from resource as the icon for button
		refresh_b.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("reload.png")));
		// the button is at the left upper corner of the panel with size 60, 60
		refresh_b.setBounds(10,5,60,60);
		refresh_b.setContentAreaFilled(false);
		refresh_b.setBorderPainted(false);
		// make all the components not focusable
		refresh_b.setFocusable(false);
		// define action for clicking
		refresh_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// when the button is clicked, interrupt all the pulling threads of Main windows
				Main.interrupt();
				System.out.println("Refreshing");
				// invoke refresh method in Main to pull again and refresh
				Main.refresh();
			}
		});
		// add this button to the panel
		this.add(refresh_b);
		
		
		// initiate temperature label
		tempLabel = new JLabel();
		// initiate as dash temperature
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">---&#8451</p></html>");
		// put the label at 50, 50 and fit the content
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		this.add(tempLabel);
		
		// initiate maximum minimum temperature label
		// initiate the content to dash
		maxminLabel=new JLabel("<html><p style=\"color:blue; font-size:12px\">Max: ---&#8451Min: ---&#8451</p></html>");
		maxminLabel.setBounds(25,210,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		this.add(maxminLabel);
		
		
		// initiate preferences Button
		pref_b = new JButton();
		// set the icon according to the icon resource
		pref_b.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("gear.png")));
		// put the icon to the right upper corner
		pref_b.setBounds(450,5,60,60);
		pref_b.setContentAreaFilled(false);
		pref_b.setBorderPainted(false);
		pref_b.setFocusable(false);
		// define action for clicking
		pref_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// when the button is clicked, interrupt all the pulling threads of Main Windows
				Main.interrupt();
				System.out.println("Open PreferenceUI");
				// invoke method in Main to show preference window
				Main.showPreference();;
			}
		});
		this.add(pref_b);
		
		// initiate wind label with speed and direction set to dash
		winLabel=new JLabel();
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>m/s --</p></html>");
		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
		this.add(winLabel);
		
		// initiate air pressure label with data set to dash
		presLabel=new JLabel();
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>hPa</p></html>");
		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
		this.add(presLabel);
		
		// initiate humidity label with data set to dash
		humLabel=new JLabel();
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">-- % humidity</p></html>");
		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
		this.add(humLabel);
		
		// initiate sky condition label
		sunLabel=new JLabel();
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">--------</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-70,(int)tempLabel.getPreferredSize().getHeight()+50,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		
		// initiate sunrise sunset label
		risetLabel=new JLabel("<html><p style=\"color:blue; font-size:12px\">Sunrise: --:-- Sunset: --:--</p></html>");
		risetLabel.setBounds(25,247,(int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		this.add(risetLabel);
		
		
		// initiate refresh time label
		refreshLabel=new JLabel();
		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:----------</p></html>");
		refreshLabel.setBounds((int)(this.getSize().getWidth()-refreshLabel.getPreferredSize().getWidth()-30),(int)(this.getSize().getHeight()-refreshLabel.getPreferredSize().getHeight()),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
		this.add(refreshLabel);
		
		// initiate location label
		locationLabel = new JLabel("<html><p style=\"color:blue; font-size:16px\"><b>------,--</b></p></html>");
		locationLabel.setBounds((int)(this.getSize().getWidth()-locationLabel.getPreferredSize().getWidth()-160),(int)(locationLabel.getPreferredSize().getHeight()+10),(int)locationLabel.getPreferredSize().getWidth()+5,(int)locationLabel.getPreferredSize().getHeight()+5);
		add(locationLabel);
			
	}
	/**
	 * refresh method for icon
	 * @param icon the icon code to be shown
	 */
	private void setIcon(String icon){
		// load icon from resource and update
		ClassLoader cl = this.getClass().getClassLoader();
		iconLabel.setIcon(new ImageIcon(cl.getResource(icon+".png")));
	}


	/**
	 * refresh method for main temperature
	 * @param temp the temperature data to be shown
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	private void setTempLabel(String temp, int unit) {
		// generate content string
		String s = "<html><p style=\"font-size:75px\">" + temp;
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		// update the label
		tempLabel.setText(s +"</p></html>");
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for maximum minimum temperature label
	 * @param max the maximum temperature to be shown
	 * @param min the minimum temperature to be shown
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	private void setMaxMinLabel(String max, String min, int unit){
		// generate string accoring to the data and unit
		String s = "<html><p style=\"color:blue; font-size:12px\">Max: " + max;
		// add unit symbol according to the unit flag
		switch(unit){
			case 0: s = s + "&#8490 Min: ";
				break;
			case 1: s = s + "&#8451 Min: ";
				break;
			case 2: s = s + "&#8457 Min: ";
				break;
		}
		switch(unit){
			case 0: s = s + min + "&#8490";
				break;
			case 1: s = s + min + "&#8451";
				break;
			case 2: s = s + min +"&#8457";
				break;
		}
		// update the label according to the string
		maxminLabel.setText(s +"</p></html>");
		maxminLabel.setBounds(25,210,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
	}


	/**
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	private void setWinLabel(String speed, String direction) {
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + speed + "</b> m/s " + direction  + "</p></html>");
		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for air pressure label
	 * @param pressure the air pressure data in hPa to be shown
	 */
	private void setPresLabel(String pressure) {
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + pressure + "</b> hPa</p></html>");
		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for humidity
	 * @param humidity the humidity data in % to be shown
	 */
	private void setHumLabel(String humidity) {
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + humidity + " % humidity</p></html>");
		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for main weather
	 * @param weather the weather data to be shown
	 */
	private void setSunLabel(String weather) {
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + weather + "</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-70,(int)tempLabel.getPreferredSize().getHeight()+50,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
	}
	
	
	/**
	 * helper method to refresh refresh time label using the current time
	 */
	private void setRefreshLabel() {
		// get the current time 
		Date date = new Date();
		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:"+date.toString().substring(4,19)+"</p></html>");
		refreshLabel.setBounds((int)(this.getSize().getWidth()-refreshLabel.getPreferredSize().getWidth()-5),(int)(this.getSize().getHeight()-refreshLabel.getPreferredSize().getHeight()),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
	}
	/**
	 * helper method to update location label
	 * @param s location as String
	 */
	private void setLocationLabel(String s){
		locationLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + s + "</b></p></html>");
		locationLabel.setBounds((int)(this.getSize().getWidth()-locationLabel.getPreferredSize().getWidth()-160),(int)(locationLabel.getPreferredSize().getHeight()+10),(int)locationLabel.getPreferredSize().getWidth()+5,(int)locationLabel.getPreferredSize().getHeight()+5);
	}
	/**
	 * helper method to update sunset sunrise label
	 * @param sunrise sunrise time as String
	 * @param sunset sunset time as String
	 */
	private void setRisetLabel(String sunrise, String sunset){
		risetLabel.setText("<html><p style=\"color:blue; font-size:12px\">Sunrise:" + sunrise + " Sunset:" + sunset + "</p></html>");
		risetLabel.setBounds(25,247,(int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		
	}
	
	/**
	 * method to paint background
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// load the background picture from resource
		ClassLoader cl = this.getClass().getClassLoader();
		try {
			// paint the background using the picture
		    g.drawImage(ImageIO.read(cl.getResource("cool_UI_01.png")), 0,0,10+(int)this.getSize().getWidth(), 10+(int)this.getSize().getHeight(), null);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
