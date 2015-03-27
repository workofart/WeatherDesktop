package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	// position data for labels and buttons
	private final int tempLabelX=85, tempLabelY=85,
						winLabelX=330,winLabelY=80,
						presLabelX=330, presLabelY=130,
						humLabelX=330, humLabelY=180,
						sunLabelX=130, sunLabelY=60,
						iconLabelX=35, iconLabelY=20,
						risetLabelX=5, risetLabelY=260,
						maxminLabelX=90, maxminLabelY=200,
						locationLabelX=0, locationLabelY=5,
						refreshLabelX=290, refreshLabelY=255, 
						refresh_bX=0, refresh_bY=0,
						pref_bX=465, pref_bY=0,
						drop_bX=245, drop_bY=255;
	private String skyCondition="";
	private JButton refresh_b, //button for refresh
				    pref_b; //button for preference window
	private BufferedImage refresh_icon,refresh_pressed;
	
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
		
		
		/*
		 ********Buttons***********
		 */
		
		// initiate refresh button
		refresh_b = new JButton();
		// use a picture from resource as the icon for button
		try{
			refresh_icon=ImageIO.read(this.getClass().getClassLoader().getResource("reload.png"));
			refresh_icon=Main.imageResize(refresh_icon,42,33);
			refresh_b.setIcon(new ImageIcon(refresh_icon));
			
			refresh_pressed=ImageIO.read(this.getClass().getClassLoader().getResource("busy.png"));
			refresh_pressed=Main.imageResize(refresh_pressed,42,33);
			refresh_b.setPressedIcon(new ImageIcon(refresh_pressed));
		}catch(IOException e){
			System.out.println("Refresh button icon: "+e.getMessage());
			refresh_b.setText("Refresh");
		}
		// the button is at the left upper corner of the panel with size 60, 60
		refresh_b.setBounds(refresh_bX,refresh_bY,50,50);
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
		
		// initiate preferences Button
		pref_b = new JButton();
		// set the icon according to the icon resource
		try{
			BufferedImage icon=ImageIO.read(this.getClass().getClassLoader().getResource("gear.png"));
			icon=Main.imageResize(icon,35,35);
			pref_b.setIcon(new ImageIcon(icon));
			//setting icon for when button is pressed
			BufferedImage pressed=ImageIO.read(this.getClass().getClassLoader().getResource("gear-pressed.png"));
			pressed=Main.imageResize(pressed,35,35);
			pref_b.setPressedIcon(new ImageIcon(pressed));
		}catch(IOException e){
			System.out.println("Preference button icon: "+e.getMessage());
			pref_b.setText("Options");
		}
		
		// put the icon to the right upper corner
		pref_b.setBounds(pref_bX,pref_bY,50,50);
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
		
		JButton drop_b=new JButton();
		try{
			BufferedImage icon=ImageIO.read(this.getClass().getClassLoader().getResource("dropdown.png"));
			icon=Main.imageResize(icon,25,25);
			drop_b.setIcon(new ImageIcon(icon));
			//setting icon for when button is pressed
//			BufferedImage pressed=ImageIO.read(this.getClass().getClassLoader().getResource(".png"));
//			pressed=Main.imageResize(pressed,35,35);
//			pref_b.setPressedIcon(new ImageIcon(pressed));
		}catch(IOException e){
			System.out.println("Dropdown button icon: "+e.getMessage());
			drop_b.setText("Drop");
		}
		
		drop_b.setBounds(drop_bX,drop_bY,25,25);
		drop_b.setContentAreaFilled(false);
		drop_b.setBorderPainted(false);
		drop_b.setFocusable(false);
		drop_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// when the button is clicked, interrupt all the pulling threads of Main Windows
				Main.interrupt();
				// invoke method in Main to show preference window
				Main.shrinkGrow();
			}
		});
		this.add(drop_b);
		
		
		
		/*
		 ********Labels***********
		 */
		
		
		//  icon label and leave blank
		iconLabel = new JLabel();
		iconLabel.setBounds(iconLabelX,iconLabelY,100,100);
		this.add(iconLabel);
		
		//  temperature label with temperature and unit set to dash as default
		tempLabel = new JLabel();
		tempLabel.setText("<html><p style=\"color:white;font-size:75px\">---&#8451</p></html>");
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		this.add(tempLabel);
		
		//  maximum minimum temperature label
		// initiate the content to dash
		maxminLabel=new JLabel("<html><p style=\"color:white; font-size:12px\">Max: ---&#8451Min: ---&#8451</p></html>");
		maxminLabel.setBounds(maxminLabelX,maxminLabelY,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		this.add(maxminLabel);
		
		
		//  wind label with speed and direction set to dash
		winLabel=new JLabel();
		winLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>--</b>m/s --</p></html>");
		winLabel.setBounds(winLabelX,winLabelY,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
		this.add(winLabel);
		
		//  air pressure label with data set to dash
		presLabel=new JLabel();
		presLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>--</b>hPa</p></html>");
		presLabel.setBounds(presLabelX,presLabelY,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
		this.add(presLabel);
		
		//  humidity label with data set to dash
		humLabel=new JLabel();
		humLabel.setText("<html><p style=\"color:white; font-size:16px\">-- % humidity</p></html>");
		humLabel.setBounds(humLabelX,humLabelY,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
		this.add(humLabel);
		
		//  sky condition label
		sunLabel=new JLabel();
		sunLabel.setText("<html><p style=\"color:white; font-size:16px\">--------</p></html>");
		sunLabel.setBounds(sunLabelX,sunLabelY,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		
		//  sunrise sunset label
		risetLabel=new JLabel("<html><p style=\"color:white; font-size:12px\">Sunrise: --:-- Sunset: --:--</p></html>");
		risetLabel.setBounds(risetLabelX,risetLabelY,(int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		this.add(risetLabel);
		
		
		//  refresh time label
		refreshLabel=new JLabel();
		refreshLabel.setText("<html><p style=\"color:white; font-size:12px\">last updated:----------</p></html>");
		refreshLabel.setBounds(refreshLabelX,refreshLabelY,(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
		this.add(refreshLabel);
		
		//  location label
		locationLabel = new JLabel("<html><p style=\"color:white; font-size:16px\"><b>------</b></p></html>", SwingConstants.CENTER);
		locationLabel.setBounds(locationLabelX,locationLabelY,(int)this.getPreferredSize().getWidth(),30);
		add(locationLabel);
			
	}
	/**
	 * refresh method for icon
	 * @param icon the icon code to be shown
	 */
	private void setIcon(String icon){
		// load icon from resource and update
		//Resize image to maintain consistency
		try{
			BufferedImage img=ImageIO.read(this.getClass().getClassLoader().getResource(icon+".png"));
			img=Main.imageResize(img,80,80);
			iconLabel.setIcon(new ImageIcon(img));
		}catch(IOException e){
			System.out.println("Today forcast UI icon: "+e.getMessage());
		}
		//Determine what the  background image should be (matching the icon)
		int code=Integer.parseInt(icon.substring(0,2));
		if(code<13&&code>4){
			skyCondition="_rain";
		}else if(code==13){
			skyCondition="_snow";
		}else{
			skyCondition="";
		}
	}


	/**
	 * refresh method for main temperature
	 * @param temp the temperature data to be shown
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	private void setTempLabel(String temp, int unit) {
		// generate content string
		String s = "<html><p style=\" color:white; font-size:75px;\">" + temp;
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
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		// adjust position if the temp string is too long
		if(tempLabel.getSize().getWidth() + tempLabelX > humLabelX-5){
			tempLabel.setLocation((int)(humLabelX-5-tempLabel.getSize().getWidth()),tempLabelY);
		}
	}
	
	/**
	 * refresh method for maximum minimum temperature label
	 * @param max the maximum temperature to be shown
	 * @param min the minimum temperature to be shown
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	private void setMaxMinLabel(String max, String min, int unit){
		// generate string accoring to the data and unit
		String s = "<html><p style=\"color:white; font-size:12px\">Max: " + max;
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
		maxminLabel.setSize((int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
	}


	/**
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	private void setWinLabel(String speed, String direction) {
		winLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>" + speed + "</b> m/s " + direction  + "</p></html>");
		winLabel.setSize((int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for air pressure label
	 * @param pressure the air pressure data in hPa to be shown
	 */
	private void setPresLabel(String pressure) {
		presLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>" + pressure + "</b> hPa</p></html>");
		presLabel.setSize((int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for humidity
	 * @param humidity the humidity data in % to be shown
	 */
	private void setHumLabel(String humidity) {
		humLabel.setText("<html><p style=\"color:white; font-size:16px\">" + humidity + " % humidity</p></html>");
		humLabel.setSize((int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for main weather
	 * @param weather the weather data to be shown
	 */
	private void setSunLabel(String weather) {
		sunLabel.setText("<html><p style=\"color:white; font-size:16px\">" + weather + "</p></html>");
		sunLabel.setSize((int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
	}
	
	
	/**
	 * helper method to refresh refresh time label using the current time
	 */
	private void setRefreshLabel() {
		// get the current time 
		Date date = new Date();
		refreshLabel.setText("<html><p style=\"color:white; font-size:12px\">last updated:"+date.toString().substring(4,19)+"</p></html>");
		refreshLabel.setSize((int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
	}
	/**
	 * helper method to update location label
	 * @param s location as String
	 */
	private void setLocationLabel(String s){
		// for earth city, only show city name
		if(s.equalsIgnoreCase("Mars")){
			locationLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>" + s+ "</b></p></html>");
		}else{
			int i = s.indexOf(',');
			locationLabel.setText("<html><p style=\"color:white; font-size:16px\"><b>" + s.substring(0,i) + "</b></p></html>");
		}
		
		locationLabel.setToolTipText(s);
//		locationLabel.setSize((int)locationLabel.getPreferredSize().getWidth()+5,(int)locationLabel.getPreferredSize().getHeight()+5);
	}
	/**
	 * helper method to update sunset sunrise label
	 * @param sunrise sunrise time as String
	 * @param sunset sunset time as String
	 */
	private void setRisetLabel(String sunrise, String sunset){
		risetLabel.setText("<html><p style=\"color:white; font-size:12px\">Sunrise:" + sunrise + " Sunset:" + sunset + "</p></html>");
		risetLabel.setSize((int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		
	}
	
	/**
	 * method to set refresh icon to relax
	 */
	public void relax(){
		refresh_b.setIcon(new ImageIcon(refresh_icon));
	}
	/**
	 * method to set refresh icon to busy
	 */
	public void busy(){
		refresh_b.setIcon(new ImageIcon(refresh_pressed));
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
			
		    g.drawImage(Main.imageDarken(ImageIO.read(cl.getResource("new_UI_01"+skyCondition+".png"))), 0,0,10+(int)this.getSize().getWidth(), 10+(int)this.getSize().getHeight(), null);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getSkyString(){
		return skyCondition;
	}
	
}
