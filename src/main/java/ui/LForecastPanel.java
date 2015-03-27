
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Long Forecast Panel object represent an entry of the Long Forecast
 * This have label for temperature, sky condition, icon for sky condition
 * maximum minimum temperature, data time
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class LForecastPanel extends JPanel{
	private JLabel tempLabel,// label for temperature
				   sunLabel, // label for sky condition
				   iconLabel, // label for icon represent sky condition
				   maxminLabel, // label for maximum and minimum temperature
				   timeLabel; // label for data time
	private final int tempLabelX=85, tempLabelY=30, //Constants for the coordinates of the JLabels
			sunLabelX=180, sunLabelY=95,
			iconLabelX=33, iconLabelY=16, 
			timeLabelX=5,timeLabelY=0,
			maxminLabelX=5,maxminLabelY=105;
	private String tmpbkgd="cool", skyCondition=""; // string represent the color of backgournd picture
	
	/**
	 * constructor to use generate and show the panel
	 */
	public LForecastPanel(){
		init();
	}
	
	/**
	 * method for refresh unit for long forecast entry
	 * @param i index for long forecast object entry
	 * @param unit temperature 0 - K, 1 - C, 2 - F
	 */
	public void refreshUnit(int i, int unit){
		// use helper method to update the data
		setTemp(Main.getLdata().getTemp(i, unit), unit);
		setMaxMin(Main.getLdata().getMaxTemp(i, unit), Main.getLdata().getMinTemp(i, unit), unit);
	}
	
	/**
	 * method for refresh for long forecast entry
	 * @param i index for long forecast object entry
	 * @param unit temperature 0 - K, 1 - C, 2 - F
	 */
	public void refresh(int i, int unit){
		// use helper method to refresh unit first
		// becasue the sky condition's position is relative to the temperature
		refreshUnit(i, unit);
		// use helper method to update sky condition, icon and data time
		setSky(Main.getLdata().getWeather(i));
		setIcon(Main.getLdata().getIcon(i));
		setTime(Main.getLdata().getTime(i));
	}
	
	/**
	 * helper method to generate and show the panel
	 */
	private void init(){
		// use absolute layout
		this.setLayout(null);
		this.setOpaque(false);
		// add a black board for each panel
		this.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		setSize(260,128);
		
		// initiate icon label
		// the icon use absolute position below the temperature label
		// the icon's size is 50 by 50
		iconLabel = new JLabel();
		iconLabel.setBounds(iconLabelX,iconLabelY,65,65);
		this.add(iconLabel);
	
		// initiate data time label with dash content
		// the time label use absolute position at the left upper coner
		// change the size according to the text
		timeLabel = new JLabel("<html><p style=\"color:white;font-size:10px\">--- --</p></html>");
		timeLabel.setBounds(timeLabelX,timeLabelY,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
		this.add(timeLabel);
		
		// initiate temperateure label with dash content
		// the temperature label use absolute position below the time label
		// change the size according to the text
		tempLabel=new JLabel("<html><p style=\"color:white;font-size:40px\">--&#8451</p></html>");
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		this.add(tempLabel);
		
		// initiate maximum minimum temperature label with dash content
		// the maximum minimum temperature label uses absolute position below the temp label
		// change the size according to the text
		maxminLabel=new JLabel("<html><p style=\"color:white; font-size:10px\">Max: ---&#8451 Min: ---&#8451</p></html>");
		maxminLabel.setBounds(maxminLabelX,maxminLabelY,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		this.add(maxminLabel);
		
		// initiate sky condition label with dash content
		// the sky condition label uses absolute position to the right of icon
		// change the size according to the text
		sunLabel=new JLabel("<html><p style=\"color:white;font-size:12px\">--------</p></html>");
		sunLabel.setBounds(sunLabelX,sunLabelY,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
		this.add(sunLabel);
	}
	
	/**
	 * method to refresh temperature label
	 * @param temp the temperature to show
	 * @param unit the flag to indicate unit of temperature
	 */
	private void setTemp(String temp, int unit){
		String s = "<html><p style=\"color:white;font-size:40px\">" + temp;
		// change unit according to unit
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		// update label
		tempLabel.setText(s+"</p></html>");
		// change the size according to the text
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	
	/**
	 * method to refresh main weather label
	 * @param sky the main weather description to show
	 */
	private void setSky(String sky){
		// update sky condition
		// change the size according to the text
		sunLabel.setText("<html><p style=\"color:white;font-size:12px\">" + sky + "</p></html>");
		sunLabel.setBounds(sunLabelX,sunLabelY,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * method to refresh icon label
	 * @param icon the icon code for Open Weather API
	 */
	private void setIcon(String icon){
		// load the icon from resource and update icon label
		//Resize the icon to maintain consistency if icon files are changed
		try{
			BufferedImage img=ImageIO.read(this.getClass().getClassLoader().getResource(icon+".png"));
			img=Main.imageResize(img,55,55);
			iconLabel.setIcon(new ImageIcon(img));
		}catch(IOException e){
			System.out.println("Shortterm forcast UI icon: "+e.getMessage());
		}
		//Determine what the background image should be
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
	 * method to refresh date label
	 * @param date the time of data for long forecast entry
	 */
	private void setTime(String date){
		// update the date
		// change the size according to the text
		timeLabel.setText("<html><p style=\"color:white;font-size:10px\">" + date + "</p></html>");
		timeLabel.setBounds(timeLabelX,timeLabelY,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * helper method to refresh maximum and minimum label
	 * @param max the maximum temperature data
	 * @param min the minimum temperature data
	 * @param unit temperature unit 0-K 1-C 2-F
	 */
	private void setMaxMin(String max, String min, int unit){
		// generate data string
		String s = "<html><p style=\"color:white;font-size:10px\">Max: " + max;
		// change unit according to unit
		switch(unit){
			case 0: s = s + "&#8490 Min: ";
				break;
			case 1: s = s + "&#8451 Min: ";
				break;
			case 2: s = s + "&#8457 Min: ";
				break;
		}
		// change unit according to unit
		switch(unit){
			case 0: s = s + min + "&#8490";
				break;
			case 1: s = s + min + "&#8451";
				break;
			case 2: s = s + min +"&#8457";
				break;
		}
		// update label
		maxminLabel.setText(s +"</p></html>");
		// change size according to the text
		maxminLabel.setBounds(maxminLabelX,maxminLabelY,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * method to paint the backgournd
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage img = null;
		// load the picture from resource
		ClassLoader cl = this.getClass().getClassLoader();
		try {
		    img = ImageIO.read(cl.getResource(tmpbkgd+"_UI_03"+skyCondition+".png"));
		} catch (IOException e) {
			// if the picture not exists, print message
			System.out.println(e.getMessage());
		}
//		g.drawImage(img, 0,0,10+(int)this.getSize().getWidth(), 10+(int)this.getSize().getHeight(), null);
	}
}
