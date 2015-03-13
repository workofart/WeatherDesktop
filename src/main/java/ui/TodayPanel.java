
package ui;

import io.Preference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import weather.CurrentWeather;
/**
 * Class for Current Weather panel
 * @author team8
 */

public class TodayPanel extends JPanel implements ActionListener{
	//label for temperature, wind, pressure, humidity, main weather and icon
	private JLabel tempLabel, winLabel, presLabel, humLabel, sunLabel, iconLabel, refreshLabel;
	
	/**
	 * constructor to initialize all the text fields to hyphen
	 */
	public TodayPanel(){
		this.setBackground(Color.cyan);
		this.setMinimumSize(new Dimension(0,285));
		this.setPreferredSize(new Dimension(525,(int)(910*0.3)));
		//Temp, wind, pressure, humidity, sun, sky
		iconLabel = new JLabel();
		iconLabel.setBounds(40,40,50,50);
		this.add(iconLabel);
		
		//Temp
		tempLabel=new JLabel();
		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">--&deg C</p></html>");
//		tempLabel.setFont(new Font("Serif",Font.BOLD,50));
//		System.out.println(this.getHeight());
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
//		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.add(tempLabel);
		
		//Wind
		winLabel=new JLabel();
		winLabel.setOpaque(true);
		winLabel.setBackground(Color.green);
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>m/s --</p></html>");
		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
		this.add(winLabel);
		//Pressure
		presLabel=new JLabel();
		presLabel.setOpaque(true);
		presLabel.setBackground(Color.green);
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>--</b>hPa</p></html>");
		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
		this.add(presLabel);
		//Humidity
		humLabel=new JLabel();
		humLabel.setOpaque(true);
		humLabel.setBackground(Color.green);
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">-- % humidity</p></html>");
		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
		this.add(humLabel);
		//Sun
		sunLabel=new JLabel();
		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">--------</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-70,(int)tempLabel.getPreferredSize().getHeight()+50,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		//icon
		
		
		//Refresh button
		JButton refresh_b=new JButton();
		refresh_b.setText("<html><p style=\"color:black; font-size:14px\">Refresh</p></html>");
		refresh_b.setBounds(0,0,(int)refresh_b.getPreferredSize().getWidth()+5,(int)refresh_b.getPreferredSize().getHeight()+5);
		refresh_b.setOpaque(false);
		refresh_b.setContentAreaFilled(false);
		refresh_b.addActionListener(this);
		this.add(refresh_b);
		
		
		//Preferences Button
		JButton pref_b=new JButton();
		pref_b.setText("<html><p style=\"color:black; font-size:14px\">Preferences</p></html>");
		pref_b.setBounds((int)(this.getPreferredSize().getWidth()-pref_b.getPreferredSize().getWidth())-20,0,(int)pref_b.getPreferredSize().getWidth()+5,(int)pref_b.getPreferredSize().getHeight()+5);
		pref_b.setOpaque(false);
		pref_b.setContentAreaFilled(false);
		pref_b.addActionListener(this);
		this.add(pref_b);
		
		
		//Refresh label
		refreshLabel=new JLabel();
//		sunLabel.setOpaque(true);
//		sunLabel.setBackground(Color.green);
		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:dd-mm-yy</p></html>");
		refreshLabel.setBounds((int)(this.getPreferredSize().getWidth()-refreshLabel.getPreferredSize().getWidth()-17),(int)(this.getPreferredSize().getHeight()-refreshLabel.getPreferredSize().getHeight()+5),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
		this.add(refreshLabel);
		
		
	}
	/**
	 * refresh method for icon
	 * @param icon the icon code to be shown
	 */
	public void setIcon(String icon){
		iconLabel.setIcon(new ImageIcon("src/main/resources/UI/Icon/"+icon+".png"));
	}


	/**
	 * refresh method for main temperature
	 * @param temp the temperature data to be shown
	 */
	public void setTempLabel(String temp) {
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">" + temp + "&deg C</p></html>");
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}


	/**
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	public void setWinLabel(String speed, String direction) {

		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + speed + "</b> m/s" + direction  + "</p></html>");

		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for air pressure label
	 * @param pressure the air pressure data in hPa to be shown
	 */
	public void setPresLabel(String pressure) {
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + pressure + "</b> hPa</p></html>");

		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for humidity
	 * @param humidity the humidity data in % to be shown
	 */
	public void setHumLabel(String humidity) {
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + humidity + " % humidity</p></html>");

		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * refresh method for main weather
	 * @param weather the weather data to be shown
	 */
	public void setSunLabel(String weather) {
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + weather + "</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-70,(int)tempLabel.getPreferredSize().getHeight()+50,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
	}
	
	
	/**
	 * refresh method for wind label
	 * @param speed the wind speed to be shown in m/s
	 * @param direction the wind direction to be shown
	 */
	public void setRefreshLabel(String date) {

		refreshLabel.setText("<html><p style=\"color:white; font-size:10px\">last updated:"+date+"</p></html>");
		refreshLabel.setBounds((int)(this.getPreferredSize().getWidth()-refreshLabel.getPreferredSize().getWidth()-20),(int)(this.getPreferredSize().getHeight()-refreshLabel.getPreferredSize().getHeight()),(int)refreshLabel.getPreferredSize().getWidth()+5,(int)refreshLabel.getPreferredSize().getHeight()+5);
	}


	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("<html><p style=\"color:black; font-size:14px\">Refresh</p></html>")){
			System.out.println("Refreshing!!");
			Main.refresh();
		}else if(e.getActionCommand().equals("<html><p style=\"color:black; font-size:14px\">Preferences</p></html>")){
			System.out.println("Let's get your preferences!");
			Main.choosePreferences();
		}
		
	}
	

	
	
	

}
