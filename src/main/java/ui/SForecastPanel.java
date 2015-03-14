
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
 * Class for Short Forecast Panel
 * @author team8
 */
public class SForecastPanel extends JPanel{
	//label for temperature, main weahter and icon
	private JLabel tempLabel, sunLabel, iconLabel, timeLabel;
	private String tmpbkgd="cool";
	/**
	 * constructor that initialize all the texts to hyphen
	 */
	public SForecastPanel(){
		this.setLayout(null);
		
		//icon
		iconLabel = new JLabel();
		iconLabel.setBounds(38,34,50,50);
		this.add(iconLabel);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.setBackground(Color.yellow);
		
		//Temp, sky

		tempLabel=new JLabel("<html><p style=\"font-size:30px\">--&#8451</p></html>");
		tempLabel.setBounds(10,20,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
//		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		this.add(tempLabel);
		//Sky
		sunLabel=new JLabel("<html><p style=\"font-size:12px\">----------</p></html>");
		sunLabel.setBounds(120,45,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
//		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		this.add(sunLabel);
		
//		timeLabel
		timeLabel=new JLabel("<html><p style=\"font-size:10px\">--:--</p></html>");
		timeLabel.setBounds(5,0,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
		timeLabel.setBackground(Color.pink);
		this.add(timeLabel);
		

		
		setPreferredSize(new Dimension(262,80));
		setMinimumSize(new Dimension(262,80));
		setMaximumSize(new Dimension(5000,5000));
		
	}
	public void setTime(String time){
		timeLabel.setText("<html><p style=\"font-size:10px\">" + time + "</p></html>");
		timeLabel.setBounds(5,0,(int)timeLabel.getPreferredSize().getWidth(),(int)timeLabel.getPreferredSize().getHeight());
	}
	/**
	 * refresh method for temperature
	 * @param temp the temperature to be shown
	 * @param unit the flag to indicate temperature unit
	 */
	public void setTemp(String temp, int unit){
		String s = "<html><p style=\"font-size:30px\">" + temp;
		switch(unit){
			case 0: s = s + "&#8490";
				break;
			case 1: s = s + "&#8451";
				break;
			case 2: s = s + "&#8457";
				break;
		}
		
		tempLabel.setText(s +"</p></html>");
		
		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for main weather
	 * @param sky the sky condition to be shown
	 */
	public void setSky(String sky){
		sunLabel.setText("<html><p style=\"font-size:12px\">" + sky + "</p></html>");

		sunLabel.setBounds(120,45,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
	}
	
	/**
	 * refresh method for icon
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
		try {
		    img = ImageIO.read(cl.getResource(tmpbkgd+"_UI_06.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		g.drawImage(img, 0,0,10+(int)this.getPreferredSize().getWidth(), 10+(int)this.getPreferredSize().getHeight(), null);
	}
	public String getTime(){
		return this.timeLabel.getText().substring(0,2);
	}
}
