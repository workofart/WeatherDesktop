
package ui;

import io.Preference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import weather.CurrentWeather;
import weather.MarsWeather;
/**
 * Class for Current Weather panel
 * @author team8
 */

public class TodayPanel extends JPanel{
	//label for temperature, wind, pressure, humidity, main weather and icon
	private JLabel tempLabel, winLabel, presLabel, humLabel, sunLabel, iconLabel, refreshLabel,risetLabel, maxminLabel, locationLabel;
	private JButton refresh_b,pref_b;
	/**
	 * constructor to initialize all the text fields to hyphen
	 */
	public TodayPanel(){
		init();
	}
	
	public void refreshMars(int unit){
		risetLabel.setVisible(false);
		setIcon(Main.getMdata().getIcon());
		setWinLabel(Main.getMdata().getSpeed(), Main.getMdata().getDirection());
		setPresLabel(Main.getMdata().getPressure());
		setHumLabel(Main.getMdata().getHumidity());
		setRefreshLabel();
		setSunLabel(Main.getMdata().getWeather());
		setLocationLabel("Mars");
		refreshMarsUnit(unit);
	}
	public void refresh(int unit){
		risetLabel.setVisible(true);
		setIcon(Main.getCdata().getIcon());
		setWinLabel(Main.getCdata().getSpeed(), Main.getCdata().getDirection());
		setPresLabel(Main.getCdata().getPressure());
		setHumLabel(Main.getCdata().getHumidity());
		setRefreshLabel();
		setSunLabel(Main.getCdata().getWeather());
		setLocationLabel(Main.getCdata().getLocation());
		setRisetLabel(Main.getCdata().getSunrise(), Main.getCdata().getSunset());
		refreshUnit(unit);
	}	
	
	
	public void refreshUnit(int unit){
		setTempLabel(Main.getCdata().getTemp(unit),unit);
		setMaxMinLabel(Main.getCdata().getMaxTemp(unit),Main.getCdata().getMinTemp(unit),unit);
	}
	
	public void refreshMarsUnit(int unit){
		setTempLabel(Main.getMdata().getTemp(unit),unit);
		setMaxMinLabel(Main.getMdata().getMaxTemp(unit),Main.getMdata().getMinTemp(unit),unit);
	}
	
	
	private void init(){
//		this.setBackground(Color.cyan);
		this.setMinimumSize(new Dimension(525,282));
		this.setPreferredSize(new Dimension(525,282));
		setLayout(null);
		
		//Temp, wind, pressure, humidity, sun, sky
		iconLabel = new JLabel();
		iconLabel.setBounds(40,40,50,50);
		this.add(iconLabel);
		
		//icon
		
		
		//Refresh button
		refresh_b=new JButton();
		refresh_b.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("reload.png")));
		refresh_b.setBounds(10,5,60,60);
		refresh_b.setContentAreaFilled(false);
		refresh_b.setBorderPainted(false);
		refresh_b.setFocusable(false);
		refresh_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.interupt();
				System.out.println("Refreshing");
				Main.refresh();
			}
		});
		this.add(refresh_b);
		
		
		//Temp
		tempLabel=new JLabel();
		tempLabel.setBackground(Color.WHITE);
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">---&#8451</p></html>");
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		this.add(tempLabel);
		
//		Max Min temp
		maxminLabel=new JLabel("<html><p style=\"color:blue; font-size:12px\">Max: ---&#8451Min: ---&#8451</p></html>");
		maxminLabel.setBounds(25,210,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		maxminLabel.setBackground(Color.pink);
		this.add(maxminLabel);
		
		
		//Preferences Button
		pref_b=new JButton();
		pref_b.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("gear.png")));
		pref_b.setBounds((int)(this.getPreferredSize().getWidth()-pref_b.getPreferredSize().getWidth()),0,(int)pref_b.getPreferredSize().getWidth()+5,(int)pref_b.getPreferredSize().getHeight()+5);
		pref_b.setContentAreaFilled(false);
		pref_b.setBorderPainted(false);
		pref_b.setFocusable(false);
		pref_b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.interupt();
				System.out.println("Open PreferenceUI");
				Main.getPrefUI().showPreference();;
			}
		});
		this.add(pref_b);
		
		//Wind
		winLabel=new JLabel();
		winLabel.setBackground(Color.green);
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>m/s --</p></html>");
		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
		this.add(winLabel);
		//Pressure
		presLabel=new JLabel();
		presLabel.setBackground(Color.green);
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>hPa</p></html>");
		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
		this.add(presLabel);
		//Humidity
		humLabel=new JLabel();
		humLabel.setBackground(Color.green);
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">-- % humidity</p></html>");
		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
		this.add(humLabel);
		//Sun
		sunLabel=new JLabel();
		sunLabel.setBackground(Color.green);
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">--------</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-70,(int)tempLabel.getPreferredSize().getHeight()+50,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		
//		rise and set times
		risetLabel=new JLabel("<html><p style=\"color:blue; font-size:12px\">Sunrise: --:-- Sunset: --:--</p></html>");
		risetLabel.setBounds(25,247,(int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		risetLabel.setBackground(Color.pink);
		this.add(risetLabel);
		
		
		//Refresh label
		refreshLabel=new JLabel();
		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:----------</p></html>");
		refreshLabel.setBounds((int)(this.getPreferredSize().getWidth()-refreshLabel.getPreferredSize().getWidth()-30),(int)(this.getPreferredSize().getHeight()-refreshLabel.getPreferredSize().getHeight()),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
		this.add(refreshLabel);
		
		locationLabel = new JLabel("<html><p style=\"color:blue; font-size:16px\"><b>------,--</b></p></html>");
		locationLabel.setBounds((int)(this.getPreferredSize().getWidth()-locationLabel.getPreferredSize().getWidth()-160),(int)(locationLabel.getPreferredSize().getHeight()+10),(int)locationLabel.getPreferredSize().getWidth()+5,(int)locationLabel.getPreferredSize().getHeight()+5);
		add(locationLabel);
		
		
	}
	/**
	 * refresh method for icon
	 * @param icon the icon code to be shown
	 */
	private void setIcon(String icon){
		ClassLoader cl = this.getClass().getClassLoader();
		iconLabel.setIcon(new ImageIcon(cl.getResource(icon+".png")));
	}


	/**
	 * refresh method for main temperature
	 * @param temp the temperature data to be shown
	 */
	private void setTempLabel(String temp, int unit) {
		String s = "<html><p style=\"font-size:75px\">" + temp;
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		
		tempLabel.setText(s +"</p></html>");
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	private void setMaxMinLabel(String max, String min, int unit){
		String s = "<html><p style=\"color:blue; font-size:12px\">Max: " + max;
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
		
		maxminLabel.setText(s +"</p></html>");
		maxminLabel.setBounds(25,210,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
	}


	/**
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	private void setWinLabel(String speed, String direction) {
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + speed + "</b> m/s" + direction  + "</p></html>");
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
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	private void setRefreshLabel() {
		Date date = new Date();
		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:"+date.toString().substring(4,19)+"</p></html>");
		refreshLabel.setBounds((int)(this.getPreferredSize().getWidth()-refreshLabel.getPreferredSize().getWidth()),(int)(this.getPreferredSize().getHeight()-refreshLabel.getPreferredSize().getHeight()),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
	}

	
	private void setLocationLabel(String s){
		locationLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + s + "</b></p></html>");
		locationLabel.setBounds((int)(this.getPreferredSize().getWidth()-locationLabel.getPreferredSize().getWidth()-160),(int)(locationLabel.getPreferredSize().getHeight()+10),(int)locationLabel.getPreferredSize().getWidth()+5,(int)locationLabel.getPreferredSize().getHeight()+5);
	}
	
	private void setRisetLabel(String sunrise, String sunset){
		risetLabel.setText("<html><p style=\"color:blue; font-size:12px\">Sunrise:" + sunrise + " Sunset:" + sunset + "</p></html>");
		risetLabel.setBounds(25,247,(int)risetLabel.getPreferredSize().getWidth(),(int)risetLabel.getPreferredSize().getHeight());
		
	}
	
	/**
	 * method to
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ClassLoader cl = this.getClass().getClassLoader();
		try {
		    g.drawImage(ImageIO.read(cl.getResource("cool_UI_01.png")), 0,0,10+(int)this.getPreferredSize().getWidth(), 10+(int)this.getPreferredSize().getHeight(), null);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
