package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import weather.CurrentWeather;


public class TodayPanel extends JPanel{
	
	private JLabel tempLabel, winLabel, presLabel, humLabel, sunLabel;
	
	
	public TodayPanel(){
		this.setBackground(Color.cyan);
		this.setMinimumSize(new Dimension(0,285));
		this.setPreferredSize(new Dimension(525,(int)(910*0.3)));
		//Temp, wind, pressure, humidity, sun, sky
		
		
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
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-30,(int)tempLabel.getPreferredSize().getHeight()+40,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		
	}


	/**
	 * @param tempLabel the tempLabel to set
	 */
	public void setTempLabel(String temp) {
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">" + temp + "&deg C</p></html>");
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
	}


	/**
	 * @param winLabel the winLabel to set
	 */
	public void setWinLabel(String speed, String direction) {

		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + speed + "</b> m/s" + direction  + "</p></html>");

		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * @param presLabel the presLabel to set
	 */
	public void setPresLabel(String pressure) {
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>" + pressure + "</b> hPa</p></html>");

		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * @param humLabel the humLabel to set
	 */
	public void setHumLabel(String humidity) {
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + humidity + " % humidity</p></html>");

		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
	}


	/**
	 * @param sunLabel the sunLabel to set
	 */
	public void setSunLabel(String weather) {
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">" + weather + "</p></html>");

		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-30,(int)tempLabel.getPreferredSize().getHeight()+40,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
	}
	
	

	
	
	

}
