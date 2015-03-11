
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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
	private JLabel tempLabel, sunLabel, iconLabel;
	
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
		tempLabel=new JLabel("<html><p style=\"font-size:30px\">--&deg C</p></html>");
		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		this.add(tempLabel);
		//Sky
		sunLabel=new JLabel("<html><p style=\"font-size:12px\">----------</p></html>");
		sunLabel.setBounds(120,45,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		this.add(sunLabel);
		
		
		
		setPreferredSize(new Dimension(262,80));
		setMinimumSize(new Dimension(262,80));
		setMaximumSize(new Dimension(5000,5000));
		
	}
	
	/**
	 * refresh method for temperature
	 * @param temp the temperature to be shown
	 * @param unit the flag to indicate temperature unit
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
		iconLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/UI/Icon/"+icon+".png")));
	}
}
