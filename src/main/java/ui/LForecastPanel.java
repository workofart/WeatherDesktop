
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import weather.CurrentWeather;
import weather.LongForecast;

/**
 * Class for Long Forecast Panel
 * @author team8
 */
public class LForecastPanel extends JPanel{
	// label for temperature, main weather, and icon
	private JLabel tempLabel, sunLabel, iconLabel, maxminLabel, timeLabel;
	private String tmpbkgd="cool";
	
	public LForecastPanel(){
		init();
	}
	public void refreshUnit(int i, int unit){
		setTemp(Main.ldata.getTemp(i, unit), unit);
		setMaxMin(Main.ldata.getMaxTemp(i, unit), Main.ldata.getMinTemp(i, unit), unit);
	}
	public void refresh(int i, int unit){
	
		refreshUnit(i, unit);
		setSky(Main.ldata.getWeather(i));
		setIcon(Main.ldata.getIcon(i));
		setTime(Main.ldata.getTime(i));
	}
	private void init(){
		
		this.setLayout(null);
		
		//icon
		iconLabel = new JLabel();
		iconLabel.setBounds(50,50,50,50);
		this.add(iconLabel);
	
//		timeLabel
		timeLabel=new JLabel("<html><p style=\"font-size:10px\">MMM DD</p></html>");
		timeLabel.setBounds(5,0,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
		timeLabel.setBackground(Color.pink);
		this.add(timeLabel);
		
		//Temp, sky		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.setBackground(Color.yellow);
		tempLabel=new JLabel("<html><p style=\"font-size:30px\">--&#8451</p></html>");
		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
//		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		this.add(tempLabel);
		
//		Max Min temp
		maxminLabel=new JLabel("<html><p style=\"color:black; font-size:10px\">Max: ---&#8451 Min: ---&#8451</p></html>");
		maxminLabel.setBounds(5,110,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		maxminLabel.setBackground(Color.pink);
		this.add(maxminLabel);
		
		//Sky
		sunLabel=new JLabel("<html><p style=\"font-size:12px\">--------</p></html>");
		sunLabel.setBounds(120,60,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
//		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		this.add(sunLabel);
		
		
		
		setPreferredSize(new Dimension(262,127));
		setMinimumSize(new Dimension(262,127));
		setMaximumSize(new Dimension(5000,5000));
		this.setBackground(Color.PINK);
	}
	/**
	 * method to refresh temperature
	 * @param temp the temperature to show
	 * @param unit the flag to indicate unit of temperature
	 */
	private void setTemp(String temp, int unit){
		String s = "<html><p style=\"font-size:30px\">" + temp;
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		
		tempLabel.setText(s+"</p></html>");

		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	
	/**
	 * method to refresh main weather
	 * @param sky the main weather description to show
	 */
	private void setSky(String sky){
		sunLabel.setText("<html><p style=\"font-size:12px\">" + sky + "</p></html>");

		sunLabel.setBounds(120,60,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * method to refresh icon
	 * @param icon the icon code for Open Weather API
	 */
	private void setIcon(String icon){
		ClassLoader cl = this.getClass().getClassLoader();
		iconLabel.setIcon(new ImageIcon(cl.getResource(icon+".png")));
	}
	
	private void setTime(String date){
		timeLabel.setText("<html><p style=\"font-size:10px\">" + date + "</p></html>");
		timeLabel.setBounds(5,0,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
	}
	
	private void setMaxMin(String max, String min, int unit){
		maxminLabel.setBounds(5,110,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
		String s = "<html><p style=\"color:blue; font-size:10px\">Max: " + max;
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
		maxminLabel.setBounds(5,110,(int)maxminLabel.getPreferredSize().getWidth(),(int)maxminLabel.getPreferredSize().getHeight());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage img = null;
		ClassLoader cl = this.getClass().getClassLoader();
//		iconLabel.setIcon(new ImageIcon(cl.getResource("cool_UI_01.png")));
		try {
		    img = ImageIO.read(cl.getResource(tmpbkgd+"_UI_06.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		g.drawImage(img, 0,0,10+(int)this.getPreferredSize().getWidth(), 10+(int)this.getPreferredSize().getHeight(), null);
	}
}
