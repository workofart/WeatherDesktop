
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Short Forecast Panel is the panel to show one short forecast entry
 * This object take signal from Main window
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class SForecastPanel extends JPanel{
	private JLabel tempLabel, // label for temperature
				   sunLabel, // label for sky condition
				   iconLabel, // label for icon
				   timeLabel; // label for data time
	private final int tempLabelX=100, tempLabelY=20,
			sunLabelX=190, sunLabelY=55,
			iconLabelX=44, iconLabelY=-20, 
			timeLabelX=5,timeLabelY=0;
	private String tmpbkgd="cool", skyCondition=""; //string to determine the backgroung picture
	
	/**
	 * constructor that initialize all the texts to hyphen
	 * show the panel
	 */
	public SForecastPanel(){
		init();
	}
	
	/**
	 * helper method to generate and show the panel
	 */
	private void init(){
		
		// use absolute layout
		// create a black board
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		
		// create icon label, put it to the left of the panel
		iconLabel = new JLabel();
		iconLabel.setBounds(iconLabelX,iconLabelY,100,100);
		this.add(iconLabel);
		
		// create temperature label with dash as content
		tempLabel=new JLabel("<html><p style=\"color:white;font-size:30px\">--&#8451</p></html>");
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		this.add(tempLabel);
		
		// create ksy condition label with dash as content
		sunLabel=new JLabel("<html><p style=\"color:white;font-size:12px\">----------</p></html>");
		sunLabel.setBounds(sunLabelX,sunLabelY,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
		this.add(sunLabel);
		
		// create data time label with dash as content
		timeLabel=new JLabel("<html><p style=\"color:white;font-size:10px\">--:--</p></html>");
		timeLabel.setBounds(timeLabelX,timeLabelY,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
		this.add(timeLabel);
		

		setSize(260,80);
	}
	
	/**
	 * method to refresh using data in Main frame
	 * @param i index of data in Long forecst object in Main frame
	 * @param unit temperature unit 0 - K, 1 - C, 2 - F
	 */
	public void refresh(int  i,int unit){
		// use refresh unit method to refresh temperature data first
		refreshUnit(i,unit);
		// refresh icon, sky condition and data time
		setIcon(Main.getSdata().getIcon(i));
		setSky(Main.getSdata().getWeather(i));
		setTime(Main.getSdata().getTime(i));
	}
	
	/**
	 * method to refresh unit using data in Main frame
	 * @param i index of data in Long forecst object in Main frame
	 * @param unit temperature unit 0 - K, 1 - C, 2 - F
	 */
	public void refreshUnit(int i, int unit){
		setTemp(Main.getSdata().getTemp(i, unit), unit);
	}
	/**
	 * helper method to update time label
	 * @param time the time of data in short forecast represent
	 */
	private void setTime(String time){
		timeLabel.setText("<html><p style=\"color:white;font-size:10px\">" + time + "</p></html>");
		timeLabel.setBounds(timeLabelX,timeLabelY,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for temperature
	 * @param temp the temperature to be shown
	 * @param unit the flag to indicate temperature unit
	 */
	private void setTemp(String temp, int unit){
		// generate content string
		String s = "<html><p style=\"color:white;font-size:30px\">" + temp;
		// according data unit to generate unit symbol
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		// update the lable using the string and chagne size
		tempLabel.setText(s +"</p></html>");
		tempLabel.setBounds(tempLabelX,tempLabelY,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for main weather
	 * @param sky the sky condition to be shown
	 */
	private void setSky(String sky){
		// update the lable using the string and chagne size
		sunLabel.setText("<html><p style=\"color:white;font-size:12px\">" + sky + "</p></html>");
		sunLabel.setBounds(sunLabelX,sunLabelY,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for icon
	 * @param icon the icon code for Open Weather API
	 */
	private void setIcon(String icon){
		// update the icon by loading resource according to the icon code
		//Resize the icon to maintain consistency if icon files are changed
		try{
			BufferedImage img=ImageIO.read(this.getClass().getClassLoader().getResource(icon+".png"));
			img=Main.imageResize(img,50,50);
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
	 * method to paint the background
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// load teh image first
		BufferedImage img = null;
		ClassLoader cl = this.getClass().getClassLoader();
		// paint the background
		try {
		    img = ImageIO.read(cl.getResource(tmpbkgd+"_UI_06"+skyCondition+".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
//		g.drawImage(img, 0,0,10+(int)this.getSize().getWidth(), 10+(int)this.getSize().getHeight(), null);
	}
}
