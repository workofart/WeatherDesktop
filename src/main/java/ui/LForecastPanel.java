
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for Long Forecast Panel
 * @author team8
 */
public class LForecastPanel extends JPanel{
	// label for temperature, main weather, and icon
	private JLabel tempLabel, sunLabel, iconLabel, maxminLabel, timeLabel;
	private String tmpbkgd="cool";
	
	public LForecastPanel(){
		this.setLayout(null);
		
		//icon
		iconLabel = new JLabel();
		iconLabel.setBounds(50,50,50,50);
		this.add(iconLabel);
	
//		timeLabel
		timeLabel=new JLabel("<html><p style=\"font-size:10px\">June 12</p></html>");
		timeLabel.setBounds(5,0,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
		timeLabel.setBackground(Color.pink);
		this.add(timeLabel);
		
		//Temp, sky		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.setBackground(Color.yellow);
		tempLabel=new JLabel("<html><p style=\"font-size:30px\">--&deg C</p></html>");
		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
//		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		this.add(tempLabel);
		
//		Max Min temp
		maxminLabel=new JLabel("<html><p style=\"color:black; font-size:10px\">Max: 49&deg Min: 40&deg</p></html>");
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
	public void setTemp(String temp, int unit){
		String s = "<html><p style=\"font-size:30px\">" + temp + "&deg ";
		switch(unit){
			case 0: s = s + "K";
				break;
			case 1: s = s + "C";
				break;
			case 2: s = s + "F";
				break;
		}
		
		tempLabel.setText(s+"</p></html>");

		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	
	/**
	 * method to refresh main weather
	 * @param sky the main weather description to show
	 */
	public void setSky(String sky){
		sunLabel.setText("<html><p style=\"font-size:12px\">" + sky + "</p></html>");

		sunLabel.setBounds(120,60,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * method to refresh icon
	 * @param icon the icon code for Open Weather API
	 */
	public void setIcon(String icon){
		ClassLoader cl = this.getClass().getClassLoader();
		iconLabel.setIcon(new ImageIcon(cl.getResource(icon+".png")));
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
